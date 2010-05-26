package org.sonatype.beaker.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.beaker.core.Meep;
import org.sonatype.beaker.core.marshal.Marshaller;

import java.io.IOException;
import java.io.OutputStream;

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

    private OutputStream out;

    private Marshaller marshaller;

    public StreamHandler() {
    }

    protected OutputStream openStream() throws IOException {
        return System.out;
    }

    public void open() throws Exception {
        out = openStream();
        marshaller = new Marshaller(out);
    }

    public void handle(final Meep meep) throws Exception {
        assert marshaller != null;
        marshaller.marshal(meep);

        if (out == System.out || out == System.err) {
            out.write("\n".getBytes());
            out.flush();
        }
    }

    public void close() throws Exception {
        assert marshaller != null;
        marshaller.close();
    }
}