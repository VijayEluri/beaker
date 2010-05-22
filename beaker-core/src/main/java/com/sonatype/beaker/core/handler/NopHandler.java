package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Handler;
import com.sonatype.beaker.lexicon.Meep;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class NopHandler
    implements Handler
{
    public void handle(final Meep meep) throws Exception {
        // noting
    }

    public void stop() throws Exception {
        // nothing
    }
}