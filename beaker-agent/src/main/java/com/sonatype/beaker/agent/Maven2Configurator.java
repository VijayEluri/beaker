package com.sonatype.beaker.agent;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * Configure the {@link AspectPath} for Maven 2.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Maven2Configurator
{
    public static final String PLEXUS_CORE = "plexus.core";

    public static void configure(final Object obj) {
        assert obj != null;

        System.out.println("Configuring for Maven 2");

        try {
            Method getWorld = obj.getClass().getMethod("getWorld");
            Object world = getWorld.invoke(obj);

            Method getRealm = world.getClass().getMethod("getRealm", String.class);
            Object realm = getRealm.invoke(world, PLEXUS_CORE);

            Method addUrl = realm.getClass().getMethod("addConstituent", URL.class);

            for (URL url : Agent.getAspectPath()) {
                System.out.println("Adding URL: " + url);
                addUrl.invoke(realm, url);
            }
        }
        catch (Exception e) {
            System.err.println("Failed to configure: " + e);
        }
    }
}