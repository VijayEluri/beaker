package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("group-push")
public class GroupPush
{
    @XStreamAsAttribute
    private final long id;

    @XStreamAsAttribute
    private final String name;

    public GroupPush(final long id, final String name) {
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
        return "GroupPush{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}