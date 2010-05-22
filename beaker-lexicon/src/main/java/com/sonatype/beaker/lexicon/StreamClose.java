package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
    private final long meepCount;

    private final long groupCount;

    private final Date date = new Date();

    public StreamClose(final long meepCount, final long groupCount) {
        this.meepCount = meepCount;
        this.groupCount = groupCount;
    }

    public long getMeepCount() {
        return meepCount;
    }

    public long getGroupCount() {
        return groupCount;
    }

    public Date getDate() {
        return date;
    }
}