package com.sonatype.beaker.core;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("meep")
public class Meep
{
    private static final AtomicLong counter = new AtomicLong(0);

    public static long getCount() {
        return counter.get();
    }

    private final Object detail;

    @XStreamAsAttribute
    private final long id;

    @XStreamAsAttribute
    private final Long group;

    @XStreamAsAttribute
    private final String thread;

    public Meep(final Object detail) {
        assert detail != null;
        this.detail = detail;
        this.id = counter.incrementAndGet();
        this.group = Group.currentId();
        this.thread = Thread.currentThread().getName();
    }

    public long getId() {
        return id;
    }

    public Long getGroup() {
        return group;
    }

    public String getThread() {
        return thread;
    }

    public Object getDetail() {
        return detail;
    }

    public Class getDetailType() {
        return getDetail().getClass();
    }

    @Override
    public String toString() {
        return "Meep{" +
            "id=" + id +
            ", group=" + group +
            ", thread='" + thread + '\'' +
            ", detail=" + detail +
            '}';
    }
}