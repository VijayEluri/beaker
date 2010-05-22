package com.sonatype.beaker.core;

import com.sonatype.beaker.core.handler.DefaultHandler;
import com.sonatype.beaker.lexicon.Fault;
import com.sonatype.beaker.lexicon.GroupClose;
import com.sonatype.beaker.lexicon.GroupOpen;
import com.sonatype.beaker.lexicon.Meep;
import com.sonatype.beaker.lexicon.SessionClose;
import com.sonatype.beaker.lexicon.SessionOpen;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Stack;

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

    public static synchronized Beaker getInstance() {
        if (instance == null) {
            try {
                instance = new Beaker();
            }
            catch (Exception e) {
                System.err.println("Failed to initialize: " + e);
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void meep(final Meep meep) {
        assert meep != null;

        try {
            getInstance().consume(meep);
        }
        catch (Exception e) {
            System.err.println("Failed to consume: " + meep + "; because of: " + e);
            e.printStackTrace();
        }
    }

    private final Handler handler;

    private Beaker() throws Exception {
        this.handler = createHandler();
        System.out.println("Using handler: " + handler);

        consume(new SessionOpen());

        Runtime.getRuntime().addShutdownHook(new Thread("beaker-shutdown")
        {
            @Override
            public void run() {
                consume(new SessionClose());
                
                try {
                    getHandler().stop();
                }
                catch (Exception e) {
                    System.err.println("Failed to stop handler: " + e);
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler createHandler() throws Exception {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new DefaultHandler();
        }

        Class type = getClass().getClassLoader().loadClass(classname);
        return (Handler) type.newInstance();
    }

    public Handler getHandler() {
        return handler;
    }

    private void consume(final Meep meep) {
        assert meep != null;

        // Install the meep context
        Meep.Context ctx = meep.getContext();
        ctx.setGroup(Group.current());
        ctx.setThread(Thread.currentThread().getName());

        try {
            // Handle the meep
            getHandler().handle(meep);
        }
        catch (Exception e) {
            System.err.println("Failed to handle: " + meep + "; because of: " + e);
            e.printStackTrace();
        }

        // Handle group open/close after we meep
        if (meep instanceof GroupOpen) {
            Group.push(((GroupOpen)meep).getId());
        }
        else if (meep instanceof GroupClose) {
            long expected = ((GroupClose)meep).getId();
            long found = Group.pop();

            // Complain if there is a stack mismatch
            if (found != expected) {
                System.err.println("Unmatched group closure; expected: " + expected + "; found: " + found);
            }
        }
    }

    //
    // Helpers
    //

    // FIXME: Move to MeepBuilder

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
}