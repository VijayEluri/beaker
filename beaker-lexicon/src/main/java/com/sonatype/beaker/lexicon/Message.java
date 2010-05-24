package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("message")
public class Message
{
    private final String detail;

    public Message(final String detail) {
        this.detail = detail;
    }

    public Message(final Object detail) {
        this(String.valueOf(detail));
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "Message{" +
            "detail='" + detail + '\'' +
            '}';
    }
}