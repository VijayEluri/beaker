package com.sonatype.beaker.core;

import com.sonatype.beaker.core.handler.FileHandler;
import com.sonatype.beaker.core.handler.NopHandler;
import com.sonatype.beaker.core.lexicon.StreamClose;
import com.sonatype.beaker.core.lexicon.StreamOpen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Beaker
{
    public static final String BEAKER_HANDLER = "beaker.handler";

    private static final Logger log = LoggerFactory.getLogger(Beaker.class);

    private Handler handler;

    private Beaker() {
        startup();
    }

    private void startup() {
        log.info("Starting");

        handle(new StreamOpen());

        Runtime.getRuntime().addShutdownHook(new Thread("beaker-shutdown")
        {
            @Override
            public void run() {
                shutdown();
            }
        });
    }

    private void shutdown() {
        log.info("Stopping");

        // Final meep
        handle(new StreamClose(Meep.getCount(), Group.getCount()));

        // Stop the handler
        try {
            getHandler().stop();
        }
        catch (Exception e) {
            log.error("Failed to stop handler", e);
        }

        // Display a summary
        log.info("Meep count: {}", Meep.getCount());
        log.info("Group count: {}", Group.getCount());

        log.info("Stopped");
    }

    private Handler createHandler() {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new NopHandler();
        }

        if (classname.equals("file")) {
            return new FileHandler();
        }

        try {
            Class type = getClass().getClassLoader().loadClass(classname);
            return (Handler) type.newInstance();
        }
        catch (Exception e) {
            log.error("Failed to create handler; using NOP", e);
            return new NopHandler();
        }
    }

    public Handler getHandler() {
        if (handler == null) {
            handler = createHandler();
        }
        return handler;
    }

    private void handle(final Object detail) {
        assert detail != null;

        log.trace("Handling: {}", detail);
        
        try {
            getHandler().handle(new Meep(detail));
        }
        catch (Exception e) {
            log.error("Failed to handle: {}", detail, e);
        }
    }

    //
    // Factory Access
    //

    private static Beaker instance;

    public static synchronized Beaker getInstance() {
        if (instance == null) {
            try {
                instance = new Beaker();
                log.info("Ready");
            }
            catch (Exception e) {
                log.error("Failed to initialize", e);
            }
        }
        return instance;
    }

    //
    // Main API
    //

    public static void meep(final Object detail) {
        getInstance().handle(detail);
    }

    public static void push() {
        push(null);
    }

    public static void push(final String name) {
        new Group(name).open();
    }

    public static void pop() {
        Group.current().close();
    }
}