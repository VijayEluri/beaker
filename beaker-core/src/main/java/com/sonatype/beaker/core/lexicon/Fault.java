package com.sonatype.beaker.core.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("fault")
public class Fault
{
    @XStreamAsAttribute
    private final String message;

    private final Throwable cause;

    public Fault(final String message, final Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public Fault(final String message) {
        this(message, null);
    }

    public Fault(final Throwable cause) {
        this(null, cause);
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "Fault{" +
            "message='" + message + '\'' +
            ", cause=" + cause +
            '}';
    }
}