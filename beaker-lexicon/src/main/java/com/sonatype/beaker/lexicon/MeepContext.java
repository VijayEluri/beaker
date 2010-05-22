package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("meep-context")
public class MeepContext
{
    private long id;

    private Long groupId;

    private String thread;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(final Long groupId) {
        // groupId may be null
        this.groupId = groupId;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(final String thread) {
        // thread should not be null
        this.thread = thread;
    }

    public void setThread(final Thread thread) {
        assert thread != null;
        setThread(thread.getName());
    }
}