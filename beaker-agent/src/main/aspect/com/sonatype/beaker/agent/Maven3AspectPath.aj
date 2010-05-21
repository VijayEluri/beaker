package com.sonatype.beaker.agent;

/**
 * Configure the {@link AspectPath} for Maven 3.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Maven3AspectPath
{
    after() returning:
        execution(void org.codehaus.plexus.classworlds.launcher.Launcher.configure(java.io.InputStream))
    {
        Maven3Configurator.configure(thisJoinPoint.getThis());
    }
}