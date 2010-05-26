package org.sonatype.beaker.core;

import org.sonatype.beaker.core.handler.Handler;
import org.sonatype.beaker.core.handler.HandlerFactory;
import org.sonatype.beaker.core.lexicon.Header;
import org.sonatype.beaker.core.lexicon.Summary;
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
    private static final Logger log = LoggerFactory.getLogger(Beaker.class);

    private Handler handler;

    private Beaker() {
        startup();
    }

    private void startup() {
        log.info("Starting");

        handle(new Summary());

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
        handle(new Header(Meep.getCount(), Group.getCount()));

        try {
            getHandler().close();
        }
        catch (Exception e) {
            log.error("Failed to close handler", e);
        }

        // Display a summary
        log.info("Meep count: {}", Meep.getCount());
        log.info("Group count: {}", Group.getCount());

        log.info("Stopped");
    }

    public Handler getHandler() {
        if (handler == null) {
            handler = HandlerFactory.create();
            try {
                handler.open();
            }
            catch (Exception e) {
                log.error("Failed to open handler", e);
            }
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