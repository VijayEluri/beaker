package com.sonatype.beaker.agent;

import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.classworlds.realm.NoSuchRealmException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class AspectPath
    implements Iterable<URL>
{
    public static final String MAVEN_ASPECT_PATH = "maven.aspect.path";

    public Collection<URL> getElements() {
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

    public Iterator<URL> iterator() {
        return getElements().iterator();
    }
}