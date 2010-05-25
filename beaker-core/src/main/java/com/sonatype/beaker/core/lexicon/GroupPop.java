package com.sonatype.beaker.core.lexicon;

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
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GroupPop{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}