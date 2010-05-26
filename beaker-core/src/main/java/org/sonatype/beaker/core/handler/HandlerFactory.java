package org.sonatype.beaker.core.handler;

import org.sonatype.beaker.core.Beaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class HandlerFactory
{
    public static final String BEAKER_HANDLER = "beaker.handler";

    private static final Logger log = LoggerFactory.getLogger(Beaker.class);

    public static Handler create() {
        String classname = System.getProperty(BEAKER_HANDLER);
        if (classname == null) {
            return new NopHandler();
        }

        if (classname.equals("file")) {
            return new FileHandler();
        }

        if (classname.equals("stream")) {
            return new StreamHandler();
        }

        try {
            Class type = HandlerFactory.class.getClassLoader().loadClass(classname);
            return (Handler) type.newInstance();
        }
        catch (Exception e) {
            log.error("Failed to create handler; using NOP", e);
            return new NopHandler();
        }
    }
}