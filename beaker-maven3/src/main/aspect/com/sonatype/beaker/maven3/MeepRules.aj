package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Beaker;
import com.sonatype.beaker.lexicon.*;
import org.apache.commons.beanutils.*;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public privileged aspect MeepRules
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
//        Object artifact = thisJoinPoint.getArgs()[0];
//        Beaker.meep(new ArtifactResolved(), artifact);
    }

    /**
     * Capture when a goal has been started.
     */
    before():
        execution(void org.apache.maven.plugin.BuildPluginManager.executeMojo(
            org.apache.maven.execution.MavenSession,
            org.apache.maven.plugin.MojoExecution))
    {
        Object execution = thisJoinPoint.getArgs()[1];
        DynaBean bean = new WrapDynaBean(execution);
        Beaker.meep(new PluginContext(), bean.get("plugin"));
        Beaker.meep(new MojoExecute(), execution);
    }
}