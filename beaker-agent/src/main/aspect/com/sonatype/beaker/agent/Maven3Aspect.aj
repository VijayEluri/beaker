package com.sonatype.beaker.agent;

/**
 * Provides hooks for Maven 3.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Maven3Aspect
{
    /**
     * Configure the ClassWorlds Launcher; adds jars from the aspect path.
     */
    after() returning:
        execution(void org.codehaus.plexus.classworlds.launcher.Launcher.configure(java.io.InputStream))
    {
        new Configurator.Maven3().configure(thisJoinPoint.getThis());
    }
}