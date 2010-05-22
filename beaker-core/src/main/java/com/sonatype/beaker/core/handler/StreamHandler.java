package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Handler;
import com.sonatype.beaker.lexicon.Meep;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class StreamHandler
    implements Handler
{
    private static final Logger log = LoggerFactory.getLogger(StreamHandler.class);

    private final Writer out;

    private final XStream xstream;

    public StreamHandler() {
        this.xstream = new XStream();
        this.xstream.autodetectAnnotations(true);
        this.out = getWriter();
    }

    protected Writer getWriter() {
        return new OutputStreamWriter(System.out);
    }

    public synchronized void handle(final Meep meep) throws Exception {
        assert meep != null;
        xstream.toXML(meep, out);
        out.append("\n");
        out.flush();
    }

    public synchronized void stop() throws Exception {
        out.flush();
    }
}