package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Beaker;
import com.sonatype.beaker.lexicon.ArtifactResolved;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.DefaultArtifactResolver;
import org.apache.maven.artifact.repository.RepositoryRequest;
import org.apache.maven.wagon.events.TransferListener;

public privileged aspect ArtifactInspectorAspect
{
    public pointcut resolve(Artifact artifact):
        args(artifact,..) && execution(void DefaultArtifactResolver.resolve(Artifact, RepositoryRequest, TransferListener, boolean));

    after(Artifact artifact) returning: resolve(artifact) {
        ArtifactResolved meep = new ArtifactResolved();

        // TOOD: Use beanutils here...
        meep.setGroupId(artifact.getGroupId());
        meep.setArtifactId(artifact.getArtifactId());
        meep.setVersion(artifact.getVersion());
        
        Beaker.meep(meep);
    }
}