package com.sonatype.beaker.maven.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.File;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("project-started")
public class ProjectStarted
{
    @XStreamAsAttribute
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ProjectStarted{" +
            "file=" + file +
            '}';
    }
}