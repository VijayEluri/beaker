package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Beaker;
import com.sonatype.beaker.lexicon.ArtifactResolved;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public privileged aspect ArtifactInspectorAspect
{
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
        Beaker.meep(new ArtifactResolved(), thisJoinPoint.getArgs()[0]);
    }
}