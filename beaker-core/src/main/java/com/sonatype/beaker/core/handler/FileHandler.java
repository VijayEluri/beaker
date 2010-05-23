package com.sonatype.beaker.core.handler;

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
    extends StreamHandler
{
    public static final String FILE = "beaker.handler.file";

    private static final Logger log = LoggerFactory.getLogger(FileHandler.class);

    @Override
    protected Writer getWriter() {
        try {
            File file = getFile();
            log.info("Output file: {}", file);
            return new BufferedWriter(new FileWriter(file));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile() throws IOException {
        String filename = System.getProperty(FILE);
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
}