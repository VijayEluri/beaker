package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Handler;
import com.sonatype.beaker.lexicon.Meep;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class FileHandler
    implements Handler
{
    public static final String FILE_OUTPUT = "beaker.handler.file.output";

    private final XStream xstream;

    private final Writer out;

    public FileHandler() throws Exception {
        this.xstream = new XStream();

        String filename = System.getProperty(FILE_OUTPUT);
        File file;
        if (filename == null) {
            file = File.createTempFile("beaker", ".xml");
        }
        else {
            file = new File(filename);
        }
        file.getParentFile().mkdirs();

        System.out.println("Writing to file: " + file);

        out = new BufferedWriter(new FileWriter(file));
    }

    public void handle(final Meep meep) throws Exception {
        assert meep != null;
        xstream.toXML(meep, out);
        out.append("\n");
        out.flush();
    }

    public void stop() throws Exception {
        out.flush();
    }
}