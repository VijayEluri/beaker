package org.sonatype.beaker.core.handler;

import org.sonatype.beaker.core.Meep;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class NopHandler
    implements Handler
{
    public void open() throws Exception {
        // empty
    }

    public void handle(Meep meep) throws Exception {
        // empty
    }

    public void close() throws Exception {
        // empty
    }
}