package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.Fault;
import com.sonatype.beaker.lexicon.Meep;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Beaker
{
    public static final String BEAKER_HANDLER = "beaker.handler";

    private static Beaker instance;

    public static Beaker getInstance() {
        if (instance == null) {
            try {
                instance = new Beaker();
            }
            catch (Exception e) {
                System.err.println("Failed to initialize: " + e);
            }
        }
        return instance;
    }

    public static void meep(final Meep meep) {
        try {
            getInstance().getHandler().handle(meep);
        }
        catch (Exception e) {
            System.err.println("Failed to handle: " + e);
        }
    }
    
    public static void meep(final Meep meep, final Object source) {
        assert meep != null;
        assert source != null;

        try {
            PropertyUtils.copyProperties(meep, source);
            meep(meep);
        }
        catch (Exception e) {
            meep(new Fault(e));
        }
    }

    private final MeepHandler handler;

    private Beaker() throws Exception {
        this.handler = createHandler();
        System.out.println("Using handler: " + handler);

        Runtime.getRuntime().addShutdownHook(new Thread("beaker-shutdown")
        {
            @Override
            public void run() {
                try {
                    getHandler().stop();
                }
                catch (Exception e) {
                    System.err.println("Failed to stop handler: " + e);
                }
            }
        });
    }

    public MeepHandler createHandler() throws Exception {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new DefaultMeepHandler();
        }

        Class type = getClass().getClassLoader().loadClass(classname);
        return (MeepHandler) type.newInstance();
    }

    public MeepHandler getHandler() {
        return handler;
    }
}