package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Handler;
import com.sonatype.beaker.core.Meep;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

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

    private void writeHeader() throws IOException {
        out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        out.flush();
    }

    protected Writer getWriter() {
        return new OutputStreamWriter(System.out);
    }

    public synchronized void handle(final Meep meep) throws Exception {
        assert meep != null;

        //
        // TODO: Add checksum (for entire stream, not each meep)
        //

        String xml = xstream.toXML(meep);

        synchronized (out) {
            out.append(xml).append("\n");
            out.flush();
        }
    }

    public synchronized void stop() throws Exception {
        out.flush();
    }
}