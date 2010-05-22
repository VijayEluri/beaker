package com.sonatype.beaker.core;

import com.sonatype.beaker.core.handler.DefaultHandler;
import com.sonatype.beaker.lexicon.Fault;
import com.sonatype.beaker.lexicon.Meep;
import com.sonatype.beaker.lexicon.MeepContext;
import com.sonatype.beaker.lexicon.StreamClose;
import com.sonatype.beaker.lexicon.StreamOpen;
import org.apache.commons.beanutils.PropertyUtils;
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

    private final Handler handler = createHandler();

    private Beaker() {
        startup();
    }

    private void startup() {
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
        consume(new StreamClose(getMeepCount(), Group.getCount()));

        try {
            getHandler().stop();
        }
        catch (Exception e) {
            log.error("Failed to stop handler", e);
        }
    }

    private Handler createHandler() {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new DefaultHandler();
        }

        try {
            Class type = getClass().getClassLoader().loadClass(classname);
            return (Handler) type.newInstance();
        }
        catch (Exception e) {
            log.error("Failed to create handler; using default", e);
            return new DefaultHandler();
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public long getMeepCount() {
        return meepCounter.get();
    }

    private void consume(final Meep meep) {
        assert meep != null;

        // Install the meep context
        installContext(meep);

        try {
            // Handle the meep
            getHandler().handle(meep);
        }
        catch (Exception e) {
            log.error("Failed to handle: {}", meep, e);
        }
    }

    private void installContext(final Meep meep) {
        assert meep != null;
        MeepContext ctx = meep.getContext();
        ctx.setId(meepCounter.incrementAndGet());
        ctx.setGroupId(Group.currentId());
        ctx.setThread(Thread.currentThread());
    }

    //
    // Factory Access
    //

    private static Beaker instance;

    public static synchronized Beaker getInstance() {
        if (instance == null) {
            try {
                instance = new Beaker();
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