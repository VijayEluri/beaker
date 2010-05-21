package com.sonatype.beaker.agent;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Agent configuration options.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Options
{
    public static final String MAVEN_ASPECT_PATH = "maven.aspect.path";

    private final String spec;

    public Options(final String spec) {
        assert spec != null;
        this.spec = spec;
    }

    public Collection<URL> getAspectPath() {
        String path = System.getProperty(MAVEN_ASPECT_PATH);
        if (path == null) {
            return Collections.emptyList();
        }

        String[] items = path.split(File.pathSeparator);
        List<URL> elements = new ArrayList<URL>(items.length);
        for (String item : items) {
            try {
                URL url;

                File file = new File(item);
                if (file.isFile()) {
                    url = file.toURI().toURL();
                }
                else {
                    url = new URL(item);
                }

                elements.add(url);
            }
            catch (MalformedURLException e) {
                System.err.println("Invalid URL: " + item);
            }
        }

        return elements;
    }

    @Override
    public String toString() {
        return "Options{" +
            "spec='" + spec + '\'' +
            '}';
    }
}