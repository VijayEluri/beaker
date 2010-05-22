package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Handler;
import com.sonatype.beaker.lexicon.Meep;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private static final Logger log = LoggerFactory.getLogger(FileHandler.class);

    private final XStream xstream;

    private final Writer out;

    public FileHandler() throws Exception {
        this.xstream = new XStream();
        this.out = getWriter();
    }

    private Writer getWriter() throws IOException {
        File file = getFile();
        log.info("Output file: {}", file);
        return new BufferedWriter(new FileWriter(file));
    }

    private File getFile() throws IOException {
        String filename = System.getProperty(FILE_OUTPUT);
        File file;
        if (filename == null) {
            file = File.createTempFile("beaker", ".xml");
        }
        else {
            file = new File(filename);
        }
        file.getParentFile().mkdirs();
        return file;
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