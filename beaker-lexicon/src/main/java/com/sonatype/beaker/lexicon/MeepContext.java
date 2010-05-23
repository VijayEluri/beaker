package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("meep")
public class MeepContext
    implements Meep
{
    @XStreamAsAttribute
    private long id;

    @XStreamAsAttribute
    private Long groupId;

    @XStreamAsAttribute
    private String thread;

    private final Meep detail;

    public MeepContext(final Meep detail) {
        assert detail != null;
        this.detail = detail;
    }

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
        // Save the thread name, unless its main, when thread is null, assume its on main
        if (thread != null && !thread.equals("main")) {
            this.thread = thread;
        }
    }

    public void setThread(final Thread thread) {
        assert thread != null;
        setThread(thread.getName());
    }

    public Meep getDetail() {
        return detail;
    }
}