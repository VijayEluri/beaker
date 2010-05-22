package com.sonatype.beaker.lexicon;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Generic
    extends MeepSupport
{
    private final String detail;

    public Generic(final String detail) {
        this.detail = detail;
    }

    public Generic(final Object detail) {
        this(String.valueOf(detail));
    }

    public String getDetail() {
        return detail;
    }
}