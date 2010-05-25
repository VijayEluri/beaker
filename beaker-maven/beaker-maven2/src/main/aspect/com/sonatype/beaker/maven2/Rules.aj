package com.sonatype.beaker.maven2;

import groovy.lang.Closure;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rules for when to meep; details of what is meeped is handled by the {@link RuleDelegate}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public privileged aspect Rules
{
    private static final Logger log = LoggerFactory.getLogger(Rules.class);

    private final RuleDelegate delegate = new RuleDelegate();

    /**
     * Handle rule action.
     *
     * Provides a safety-net to prevent rule actions from introducing exceptions into the instrumented application's flow.
     * This is a double-check for sanity, since the delegate is also casting its own net.
     */
    private void handle(final Closure action, final JoinPoint point) {
        try {
            delegate.handle(action, point);
        }
        catch (Throwable t) {
            log.error("Failed to handle: {}", point, t);
        }
    }

    //
    // TODO:
    //
}