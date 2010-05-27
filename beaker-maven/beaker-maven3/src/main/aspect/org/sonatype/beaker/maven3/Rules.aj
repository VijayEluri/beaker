package org.sonatype.beaker.maven3;

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

    /**
     * Capture when an ExecutionEvent is about to be fired.
     *
     * @supports 3.0-beta-1
     */
    before():
        execution(void org.apache.maven.lifecycle.internal.DefaultExecutionEventCatapult.fire(
            org.apache.maven.execution.ExecutionEvent.Type,
            org.apache.maven.execution.MavenSession,
            org.apache.maven.plugin.MojoExecution))
    {
        handle(delegate.executionEventFired, thisJoinPoint);
    }

    /**
     * Capture when an artifact has been resolved.
     *
     * @supports 3.0-beta-1, 3.0-alpha-7
     */
    after() returning:
        execution(void org.apache.maven.artifact.resolver.DefaultArtifactResolver.resolve(
            org.apache.maven.artifact.Artifact,
            org.apache.maven.artifact.repository.RepositoryRequest,
            org.apache.maven.wagon.events.TransferListener,
            boolean))
    {
        handle(delegate.artifactResolved, thisJoinPoint);
    }
}