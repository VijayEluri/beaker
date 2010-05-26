package org.sonatype.beaker.maven;

import org.aspectj.lang.JoinPoint;

import java.io.PrintStream;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class JoinPointTrace
{
    public static int TRACELEVEL = 2;

    protected static PrintStream stream = System.err;

    protected static int callDepth = 0;

    public static void initStream(PrintStream s) {
        stream = s;
    }

    public static void entry(final JoinPoint point) {
        traceEntry("" + point.getSignature().toLongString());
    }

    public static void exit(final JoinPoint point) {
        traceExit("" + point.getSignature().toLongString());
    }

    protected static void traceEntry(String str) {
        if (TRACELEVEL == 0) return;
        if (TRACELEVEL == 2) callDepth++;
        printEntering(str);
    }

    protected static void traceExit(final String str) {
        if (TRACELEVEL == 0) return;
        printExiting(str);
        if (TRACELEVEL == 2) callDepth--;
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