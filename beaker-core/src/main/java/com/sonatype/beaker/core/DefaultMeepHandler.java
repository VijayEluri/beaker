package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.Meep;
import com.thoughtworks.xstream.XStream;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class DefaultMeepHandler
    implements MeepHandler
{
    public void handle(final Meep meep) throws Exception {
        assert meep != null;
        System.out.println("MEEP> " + meep);
    }

    public void stop() throws Exception {
        // nothing
    }
}