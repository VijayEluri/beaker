package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("stream-open")
public class StreamOpen
    extends MeepSupport
{
    private final Date date = new Date();

    public Date getDate() {
        return date;
    }
}