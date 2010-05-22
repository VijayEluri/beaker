package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("generic")
public class Generic
    extends MeepSupport
{
    private final Object detail;

    public Generic(final Object detail) {
        this.detail = detail;
    }

    public Object getDetail() {
        return detail;
    }
}