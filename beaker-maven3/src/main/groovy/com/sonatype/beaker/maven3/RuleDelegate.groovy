package com.sonatype.beaker.maven3

import org.aspectj.lang.JoinPoint
import com.sonatype.beaker.core.Beaker
import com.sonatype.beaker.lexicon.Generic
import com.sonatype.beaker.lexicon.maven.PluginContext
import com.sonatype.beaker.core.MeepBuilder
import com.sonatype.beaker.lexicon.maven.MojoExecute
import com.sonatype.beaker.lexicon.maven.ArtifactResolved
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
class RuleDelegate
{
    private static final Logger log = LoggerFactory.getLogger(RuleDelegate.class);

    private def onError(final JoinPoint point, final Throwable cause) {
        log.error("Failed to handle: {}", point, cause)
        // cause.printStackTrace()
    }

    def handle(Closure action, JoinPoint point) {
        try {
            assert action != null
            assert point != null
            action.call(point)
        }
        catch (Throwable t) {
            onError(point, t)
        }
    }

    public Closure artifactResolved = { JoinPoint point ->
        def artifact = point.args[0]
        MeepBuilder.meep(new ArtifactResolved(), artifact)
    }

    public Closure goalStarted = { JoinPoint point ->
        def execution = point.args[1]
        MeepBuilder.meep(new MojoExecute(), execution)
        MeepBuilder.meep(new PluginContext(), execution.plugin)
    }

    public Closure executionEventFired = { JoinPoint point ->
        def type = point.args[0]
        def session = point.args[1]
        def execution = point.args[2]

        Beaker.meep(new Generic(type))

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

        switch ("$type") {
            case "SessionStarted":
                Beaker.meep(new Generic(session.request.pom))
                break
        }
    }
}