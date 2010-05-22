package com.sonatype.beaker.lexicon;

import java.util.Date;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class SessionOpen
    extends MeepSupport
{
    private final Date date = new Date();

    public Date getDate() {
        return date;
    }
}