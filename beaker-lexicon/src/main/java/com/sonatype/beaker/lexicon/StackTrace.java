package com.sonatype.beaker.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("stack-trace")
public class StackTrace
    extends MeepSupport
{
    private final StackTraceElement[] elements;

    public StackTrace(StackTraceElement[] elements) {
        this.elements = elements;
    }

    public StackTrace(Throwable cause) {
        this(cause.getStackTrace());
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    public StackTrace() {
        this(new Throwable().getStackTrace());
    }

    public StackTraceElement[] getElements() {
        return elements;
    }
}