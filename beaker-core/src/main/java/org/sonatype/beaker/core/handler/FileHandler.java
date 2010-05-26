package org.sonatype.beaker.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

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
    protected OutputStream openStream() throws IOException {
        File file = getFile();
        log.info("Output file: {}", file);
        if (file.getName().endsWith(".gz")) {
            return new GZIPOutputStream(new FileOutputStream(file));
        }
        else {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }

    private File getFile() throws IOException {
        String filename = System.getProperty(FILE);
        File file;
        if (filename == null) {
            file = File.createTempFile("beaker-", ".xmeep");
        }
        else {
            file = new File(filename);
        }
        file.getParentFile().mkdirs();
        return file;
    }
}