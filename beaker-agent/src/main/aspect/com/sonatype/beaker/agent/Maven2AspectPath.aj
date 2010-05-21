package com.sonatype.beaker.agent;

/**
 * Configure the {@link AspectPath} for Maven 2.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Maven2AspectPath
{
    after() returning:
        execution(void org.codehaus.classworlds.Launcher.configure(java.io.InputStream))
    {
        Maven2Configurator.configure(thisJoinPoint.getThis());
    }
}