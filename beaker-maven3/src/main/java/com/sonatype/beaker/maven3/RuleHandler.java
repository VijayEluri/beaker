package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Beaker;
import com.sonatype.beaker.core.MeepBuilder;
import com.sonatype.beaker.lexicon.maven.ArtifactResolved;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class RuleHandler
{
    public static void artifactResolved(Object artifact) {
        assert artifact != null;
        MeepBuilder.meep(new ArtifactResolved(), artifact);
    }
}