package com.sonatype.beaker.core;

import com.sonatype.beaker.core.handler.NopHandler;
import com.sonatype.beaker.lexicon.Meep;
import com.sonatype.beaker.lexicon.MeepContext;
import com.sonatype.beaker.lexicon.StreamClose;
import com.sonatype.beaker.lexicon.StreamOpen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

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

    private final AtomicLong meepCounter = new AtomicLong(0);

    private Handler handler;

    private Beaker() {
        startup();
    }

    private void startup() {
        log.info("Starting");

        consume(new StreamOpen());

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
        consume(new StreamClose(getMeepCount(), Group.getCount()));

        // Display a summary
        log.info("Meep count: {}", getMeepCount());
        log.info("Group count: {}", Group.getCount());
        
        // Stop the handler
        try {
            getHandler().stop();
        }
        catch (Exception e) {
            log.error("Failed to stop handler", e);
        }

        log.info("Stopped");
    }

    private Handler createHandler() {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new NopHandler();
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

    public long getMeepCount() {
        return meepCounter.get();
    }

    private void consume(final Meep meep) {
        assert meep != null;

        log.trace("Consuming: {}", meep);
        
        try {
            // Handle the meep
            getHandler().handle(context(meep));
        }
        catch (Exception e) {
            log.error("Failed to handle: {}", meep, e);
        }
    }

    private MeepContext context(final Meep meep) {
        assert meep != null;
        MeepContext ctx = new MeepContext(meep);
        ctx.setId(meepCounter.incrementAndGet());
        ctx.setGroupId(Group.currentId());
        ctx.setThread(Thread.currentThread());
        return ctx;
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

    public static void meep(final Meep meep) {
        assert meep != null;

        try {
            getInstance().consume(meep);
        }
        catch (Exception e) {
            log.error("Failed to consume: {}", meep, e);
        }
    }
}