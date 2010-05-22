package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Date;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("stream-close")
public class StreamClose
    extends MeepSupport
{
    @XStreamAsAttribute
    private final Date date = new Date();

    @XStreamAsAttribute
    private final long meeps;

    @XStreamAsAttribute
    private final long groups;

    public StreamClose(final long meeps, final long groups) {
        this.meeps = meeps + 1; // include ourselves
        this.groups = groups;
    }

    public Date getDate() {
        return date;
    }

    public long getMeeps() {
        return meeps;
    }

    public long getGroups() {
        return groups;
    }
}