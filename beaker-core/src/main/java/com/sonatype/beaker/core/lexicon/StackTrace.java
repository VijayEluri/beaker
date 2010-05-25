package com.sonatype.beaker.core.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Arrays;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("stack-trace")
public class StackTrace
{
    private final StackTraceElement[] elements;

    public StackTrace(final StackTraceElement[] elements) {
        this.elements = elements;
    }

    public StackTrace(final Throwable cause) {
        this(cause.getStackTrace());
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    public StackTrace() {
        this(new Throwable().getStackTrace());
    }

    public StackTraceElement[] getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "StackTrace{" +
            "elements=" + (elements == null ? null : Arrays.asList(elements)) +
            '}';
    }
}