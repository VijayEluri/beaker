package com.sonatype.beaker.lexicon;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class GroupPush
    extends MeepSupport
{
    private final long id;

    private final String name;

    public GroupPush(final long id, final String name) {
        this.id = id;
        assert name != null;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}