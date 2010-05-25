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
@XStreamAlias("stream-open")
public class Summary
{
    @XStreamAsAttribute
    private final Date date = new Date();

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "StreamOpen{" +
            "date=" + date +
            '}';
    }
}