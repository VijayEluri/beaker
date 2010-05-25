package com.sonatype.beaker.core.lexicon;

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
public class Header
{
    @XStreamAsAttribute
    private final Date date = new Date();

    @XStreamAsAttribute
    private final long meeps;

    @XStreamAsAttribute
    private final long groups;

    public Header(final long meeps, final long groups) {
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

    @Override
    public String toString() {
        return "StreamClose{" +
            "date=" + date +
            ", meeps=" + meeps +
            ", groups=" + groups +
            '}';
    }
}