package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Group;

/**
 * Rules for when to meep; details of what is meeped is handled by the {@link RuleDelegate}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public privileged aspect Rules
{
    private final RuleDelegate delegate = new RuleDelegate();

    /**
     * Capture when an artifact has been resolved.
     */
    after() returning:
        execution(void org.apache.maven.artifact.resolver.DefaultArtifactResolver.resolve(
            org.apache.maven.artifact.Artifact,
            org.apache.maven.artifact.repository.RepositoryRequest,
            org.apache.maven.wagon.events.TransferListener,
            boolean))
    {
        delegate.artifactResolved(thisJoinPoint);
    }

    /**
     * Capture when a goal has been started.
     */
    Object around():
        execution(void org.apache.maven.plugin.BuildPluginManager.executeMojo(
            org.apache.maven.execution.MavenSession,
            org.apache.maven.plugin.MojoExecution))
    {
        final Group group = new Group("execute-mojo").open();

        try {
            delegate.goalStarted(thisJoinPoint);
            return proceed();
        }
        finally {
            group.close();
        }
    }

    /**
     * Capture when an ExecutionEvent is about to be fired.
     */
    before():
        execution(void org.apache.maven.lifecycle.internal.DefaultExecutionEventCatapult.fire(
            org.apache.maven.execution.ExecutionEvent.Type,
            org.apache.maven.execution.MavenSession,
            org.apache.maven.plugin.MojoExecution))
    {
        delegate.executionEventFired(thisJoinPoint);
    }
}