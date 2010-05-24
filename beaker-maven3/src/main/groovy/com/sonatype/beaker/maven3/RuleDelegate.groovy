package com.sonatype.beaker.maven3

import org.aspectj.lang.JoinPoint
import com.sonatype.beaker.core.Beaker
import com.sonatype.beaker.lexicon.Generic
import com.sonatype.beaker.lexicon.maven.PluginContext
import com.sonatype.beaker.lexicon.maven.MojoExecute
import com.sonatype.beaker.lexicon.maven.ArtifactResolved
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.sonatype.beaker.lexicon.maven.SessionStarted
import com.sonatype.beaker.lexicon.maven.MojoStarted
import com.sonatype.beaker.lexicon.maven.ProjectStarted
import com.sonatype.beaker.lexicon.Message

/**
 * Provides the actions to execute for defined rules.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
class RuleDelegate
{
    private static final Logger log = LoggerFactory.getLogger(RuleDelegate.class);

    //
    // TODO: Need to inform Beaker what lexicon we are using
    //
    
    /**
     * Copy properties from source to target.
     */
    private def copy(def source, def target) {
        target.metaClass.properties.each {
            if (source.metaClass.hasProperty(source, it.name) && it.name != 'metaClass' && it.name != 'class') {
                it.setProperty(target, source.metaClass.getProperty(source, it.name))
            }
        }

        return target
    }

    /**
     * Emmit a meep; de-typed here to prevent IDE turds.
     */
    private def meep(def meep) {
        Beaker.meep(meep)
    }

    /**
     * Main entry-point for rules to execute an action.
     *
     * Provides a safety-net to prevent rule actions from introducing exceptions into the instrumented application's flow.
     */
    public def handle(final Closure action, final JoinPoint point) {
        try {
            assert action != null && point != null
            action.call(point)
        }
        catch (Throwable t) {
            log.error("Failed to handle: {}", point, t)
        }
    }

    //
    // Actions; these need to be typed as "public Closure" specifically so that AspectJ can find the fields.
    // Each action takes a single argument; which is an AspectJ JoinPoint instance.
    //

    public Closure artifactResolved = { point ->
        def artifact = point.args[0]
        meep(copy(artifact, new ArtifactResolved()))
    }

    public Closure goalStarted = { point ->
        def execution = point.args[1]
        meep(copy(execution, new MojoExecute()))
        meep(copy(execution.plugin, new PluginContext()))
    }

    public Closure executionEventFired = { point ->
        def type = point.args[0] as String
        def session = point.args[1]
        def execution = point.args[2]

        /*
        Types:
           ProjectDiscoveryStarted,
           SessionStarted,
           SessionEnded,
           ProjectSkipped,
           ProjectStarted,
           ProjectSucceeded,
           ProjectFailed,
           MojoSkipped,
           MojoStarted,
           MojoSucceeded,
           MojoFailed,
           ForkStarted,
           ForkSucceeded,
           ForkFailed,
           ForkedProjectStarted,
           ForkedProjectSucceeded,
           ForkedProjectFailed;
        */

        // FIXME: *Started -> push, *{Succeeded,Failed} -> pop, *Skipped -> ?

        switch (type) {
            case "SessionStarted":
                Beaker.push(type)
                meep(copy(session.request, new SessionStarted()))
                break

            case "ProjectStarted":
                Beaker.push(type)
                meep(copy(session.currentProject, new ProjectStarted()))
                break

            case "MojoStarted":
                Beaker.push(type)

                def meep = new MojoStarted()
                meep.goal = execution.goal
                meep.executionId = execution.executionId

                def plugin = execution.plugin
                meep.plugin.groupId = plugin.groupId
                meep.plugin.artifactId = plugin.artifactId
                meep.plugin.version = plugin.version
                Beaker.meep(meep)
                break

            case "SessionEnded":
            case "ProjectSucceeded":
            case "ProjectFailed":
            case "MojoSucceeded":
            case "MojoFailed":
                Beaker.meep(new Message(type))
                Beaker.pop()
                break

            default:
                Beaker.meep(new Message(type))
                break
        }
    }
}