package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.GroupClose;
import com.sonatype.beaker.lexicon.GroupOpen;
import com.sonatype.beaker.lexicon.MeepSupport;

import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Group
{
    private static final AtomicLong counter = new AtomicLong(0);

    private final long id;

    private final String name;

    public Group(final String name) {
        this.id = counter.incrementAndGet();
        assert name != null;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group open() {
        Beaker.meep(new GroupOpen(getId(), getName()));
        return this;
    }

    public Group close() {
        Beaker.meep(new GroupClose(getId(), getName()));
        return this;
    }

    //
    // Groups
    //

    // FIXME: This needs to be a thread-local (inheritable)
    private static final Stack<Long> groups = new Stack<Long>();

    public Stack<Long> groups() {
        return groups;
    }

    public static Long current() {
        if (groups.isEmpty()) {
            return null;
        }
        else {
            return groups.peek();
        }
    }

    public static Long pop() {
        return groups.pop();
    }

    public static void push(Long group) {
        groups.push(group);
    }
}