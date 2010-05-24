package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Meep that wraps any {@link Object}.
 *
 * This should only be used for internal testing, debugging, as consumption of this meep may fail due to missing classes.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("generic")
public class Generic
{
    private final Object detail;

    public Generic(final Object detail) {
        this.detail = detail;
    }

    public Object getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "Generic{" +
            "detail=" + detail +
            '}';
    }
}