package com.sonatype.beaker.agent;

import org.codehaus.plexus.classworlds.launcher.Launcher;

/**
 * Configure the {@link AspectPath} for Maven 3.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Maven3AspectPath
{
    after(Launcher launcher) returning:
        this(launcher) &&
        execution(void Launcher.configure(java.io.InputStream))
    {
        Maven3Configurator.configure(launcher.getWorld());
    }
}