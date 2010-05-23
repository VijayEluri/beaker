package com.sonatype.beaker.lexicon.maven;

import com.sonatype.beaker.lexicon.MeepSupport;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("project-started")
public class ProjectStarted
    extends MeepSupport
{
    @XStreamAsAttribute
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}