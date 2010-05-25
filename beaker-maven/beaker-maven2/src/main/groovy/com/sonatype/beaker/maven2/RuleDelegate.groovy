package com.sonatype.beaker.maven2

import org.aspectj.lang.JoinPoint
import com.sonatype.beaker.core.Beaker
import com.sonatype.beaker.core.lexicon.*
import com.sonatype.beaker.maven.lexicon.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
    // TODO:
    //
}