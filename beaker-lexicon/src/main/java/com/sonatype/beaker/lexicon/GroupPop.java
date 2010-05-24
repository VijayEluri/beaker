package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("group-pop")
public class GroupPop
{
    @XStreamAsAttribute
    private final long id;

    @XStreamAsAttribute
    private final String name;

    public GroupPop(final long id, final String name) {
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