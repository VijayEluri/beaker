package com.sonatype.beaker.agent;

import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.classworlds.realm.NoSuchRealmException;

import java.net.URL;

/**
 * Configure the {@link AspectPath} for Maven 3.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Maven3Configurator
{
    public static final String PLEXUS_CORE = "plexus.core";

    public static void configure(final ClassWorld world) {
        assert world != null;

        System.out.println("Configuring for Maven 3");

        try {
            ClassRealm realm = world.getRealm(PLEXUS_CORE);
            for (URL url : Agent.getAspectPath()) {
                System.out.println("Adding URL: " + url);
                realm.addURL(url);
            }
        }
        catch (NoSuchRealmException e) {
            System.err.println("Missing realm: " + PLEXUS_CORE);
        }
    }
}