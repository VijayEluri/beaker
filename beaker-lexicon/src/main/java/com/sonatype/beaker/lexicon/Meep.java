package com.sonatype.beaker.lexicon;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public interface Meep
{
    Context getContext();

    class Context
    {
        private static final AtomicLong counter = new AtomicLong(0);
        
        private long id;

        private Long group;

        private String thread;

        public Context() {
            this.id = counter.incrementAndGet();
        }

        public long getId() {
            return id;
        }

        public long getGroup() {
            return group;
        }

        public void setGroup(final Long group) {
            // group may be null
            this.group = group;
        }

        public String getThread() {
            return thread;
        }

        public void setThread(final String thread) {
            // thread should not be null
            this.thread = thread;
        }
    }
}