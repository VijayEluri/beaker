package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.GroupPop;
import com.sonatype.beaker.lexicon.GroupPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Group
{
    private static final Logger log = LoggerFactory.getLogger(Group.class);

    private static final AtomicLong counter = new AtomicLong(0);

    private final long id;

    private final String name;

    public Group(final String name) {
        this.id = counter.incrementAndGet();
        // name is optional
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group open() {
        Beaker.meep(new GroupPush(id, name));
        push(this);
        return this;
    }

    public Group close() {
        Beaker.meep(new GroupPop(id, name));
        Group last = pop();

        // Complain if there is a stack mismatch
        if (!last.equals(this)) {
            log.error("Unmatched group closure; expected: {}; found: {}", this, last);
        }

        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Group that = (Group) obj;
        return id == that.id && !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    //
    // Group Stack
    //

    // FIXME: This needs to be a thread-local (inheritable)
    private static final Stack<Group> groups = new Stack<Group>();

    public static Stack<Group> groups() {
        return groups;
    }

    public static Group current() {
        if (groups.isEmpty()) {
            return null;
        }
        return groups.peek();
    }

    public static Long currentId() {
        if (groups.isEmpty()) {
            return null;
        }
        return groups.peek().getId();
    }

    public static long getCount() {
        return counter.get();
    }

    public static Group pop() {
        return groups.pop();
    }

    public static void push(final Group group) {
        groups.push(group);
    }
}