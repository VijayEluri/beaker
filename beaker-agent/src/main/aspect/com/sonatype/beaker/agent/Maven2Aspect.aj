package com.sonatype.beaker.agent;

/**
 * Provides hooks for Maven 2.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Maven2Aspect
{
    /**
     * Configure the ClassWorlds Launcher; adds jars from the aspect path.
     */
    after() returning:
        execution(void org.codehaus.classworlds.Launcher.configure(java.io.InputStream))
    {
        new Configurator.Maven2().configure(thisJoinPoint.getThis());
    }
}