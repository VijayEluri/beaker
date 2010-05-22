package com.sonatype.beaker.maven3;

import com.sonatype.beaker.core.Beaker;
import com.sonatype.beaker.core.MeepBuilder;
import com.sonatype.beaker.lexicon.Generic;
import com.sonatype.beaker.lexicon.maven.ArtifactResolved;
import com.sonatype.beaker.lexicon.maven.MojoExecute;
import com.sonatype.beaker.lexicon.maven.PluginContext;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the details of meeping for rules.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@SuppressWarnings({"UnusedDeclaration"})
public class RuleDelegate
{
    private static final Logger log = LoggerFactory.getLogger(RuleDelegate.class);

    private void onError(final JoinPoint point, final Throwable cause) {
        log.error("Failed to handle: {}", point, cause);
    }

    public void artifactResolved(final JoinPoint point) {
        assert point != null;
        try {
            Object artifact = point.getArgs()[0];
            MeepBuilder.meep(new ArtifactResolved(), artifact);
        }
        catch (Throwable t) {
            onError(point, t);
        }
    }

    public void goalStarted(final JoinPoint point) {
        assert point != null;
        try {
            Object execution = point.getArgs()[1];
            MeepBuilder.meep(new MojoExecute(), execution);
            DynaBean bean = new WrapDynaBean(execution);
            MeepBuilder.meep(new PluginContext(), bean.get("plugin"));
        }
        catch (Throwable t) {
            onError(point, t);
        }
    }

    public void executionEventFired(final JoinPoint point) {
        assert point != null;
        try {
            Object type = point.getArgs()[0];
            Object session = point.getArgs()[1];
            Object execution = point.getArgs()[2];

            // TODO: Handle more muck...

            Beaker.meep(new Generic(type));
        }
        catch (Throwable t) {
            onError(point, t);
        }
    }
}