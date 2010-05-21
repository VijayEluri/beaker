package com.sonatype.beaker.agent;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * Support for Maven aspect path configuration.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public abstract class Configurator
{
    public void configure(final Object launcher) {
        assert launcher != null;

        try {
            Object world = getWorld(launcher);
            Object realm = getRealm(world);
            Method addUrl = getAddUrlMethod(realm);

            for (URL url : Agent.getOptions().getAspectPath()) {
                System.out.println("Adding URL: " + url);
                addUrl.invoke(realm, url);
            }
        }
        catch (Exception e) {
            System.err.println("Failed to configure: " + e);
        }
    }

    protected Object getWorld(final Object launcher) throws Exception {
        assert launcher != null;
        Method getWorld = launcher.getClass().getMethod("getWorld");
        return getWorld.invoke(launcher);
    }

    protected Object getRealm(final Object world) throws Exception {
        assert world != null;
        Method getRealm = world.getClass().getMethod("getRealm", String.class);
        return getRealm.invoke(world, "plexus.core");
    }
    
    protected Method getAddUrlMethod(final Object realm) throws Exception {
        assert realm != null;
        return realm.getClass().getMethod("addURL", URL.class);
    }

    /**
     * Configures Maven 3.
     */
    public static class Maven3
        extends Configurator
    {
        @Override
        public void configure(final Object launcher) {
            System.out.println("Configuring for Maven 3");
            super.configure(launcher);
        }
    }

    /**
     * Configures Maven 2.
     */
    public static class Maven2
        extends Configurator
    {
        @Override
        public void configure(final Object launcher) {
            System.out.println("Configuring for Maven 2");
            super.configure(launcher);
        }

        @Override
        protected Method getAddUrlMethod(Object realm) throws Exception {
            return realm.getClass().getMethod("addConstituent", URL.class);
        }
    }
}