package org.sonatype.beaker.core.marshal;

import org.sonatype.beaker.core.Meep;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Marshaller
    extends MarshalSupport
{
    private final ObjectOutputStream out;

    public Marshaller(final OutputStream out) throws IOException {
        assert out != null;
        this.out = xstream.createObjectOutputStream(out, MEEP_STREAM);
    }

    public void marshal(final Meep meep) throws IOException {
        marshal(meep, out);
    }

    public void close() throws IOException {
        out.close();
    }
}