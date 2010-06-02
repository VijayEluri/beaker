package org.sonatype.beaker.maven;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.io.PrintStream;

/**
 * Support for trace aspects.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class JoinPointTrace
{
    public static int TRACELEVEL = 2;

    private static PrintStream stream = System.err;

    private static enum Verbosity
    {
        TERSE,
        NORMAL,
        VERBOSE;

        public static Verbosity get() {
            String value = System.getProperty("beaker.trace.verbosity");
            if (value == null) {
                return NORMAL;
            }
            else {
                return valueOf(value.toUpperCase());
            }
        }
    }

    private static Verbosity verbosity = Verbosity.get();

    private static int callDepth = 0;

    public static void initStream(final PrintStream out) {
        assert out != null;
        stream = out;
    }

    public static void entry(final JoinPoint point) {
        traceEntry(render(point));
    }

    public static void exit(final JoinPoint point) {
        traceExit(render(point));
    }

    private static String render(final JoinPoint point) {
        assert point != null;
        Signature sig = point.getSignature();
        switch (verbosity) {
            case TERSE:
                return sig.toShortString();
            case NORMAL:
                return sig.toString();
            case VERBOSE:
                return sig.toLongString();
        }
        throw new Error();
    }

    private static void traceEntry(final String str) {
        if (TRACELEVEL == 0) {
            return;
        }
        if (TRACELEVEL == 2) {
            callDepth++;
        }
        printEntering(str);
    }

    private static void traceExit(final String str) {
        if (TRACELEVEL == 0) {
            return;
        }
        printExiting(str);
        if (TRACELEVEL == 2) {
            callDepth--;
        }
    }

    private static void printEntering(final String str) {
        printIndent();
        stream.println("--> " + str);
    }

    private static void printExiting(final String str) {
        printIndent();
        stream.println("<-- " + str);
    }

    private static void printIndent() {
        for (int i = 0; i < callDepth; i++) {
            stream.print("  ");
        }
    }
}