package com.sonatype.beaker.agent;

import java.io.PrintStream;

public class Tracer
{
    /**
     * There are 3 trace levels (values of TRACELEVEL):
     * 0 - No messages are printed
     * 1 - Trace messages are printed, but there is no indentation
     *     according to the call stack
     * 2 - Trace messages are printed, and they are indented
     *     according to the call stack
     */
    public static int TRACELEVEL = 2;

    protected static PrintStream stream = System.err;

    protected static int callDepth = 0;

    public static void initStream(PrintStream s) {
        stream = s;
    }

    public static void traceEntry(String str) {
        if (TRACELEVEL == 0) return;
        if (TRACELEVEL == 2) callDepth++;
        printEntering(str);
    }

    public static void traceExit(String str) {
        if (TRACELEVEL == 0) return;
        printExiting(str);
        if (TRACELEVEL == 2) callDepth--;
    }

    private static void printEntering(String str) {
        printIndent();
        stream.println("--> " + str);
    }

    private static void printExiting(String str) {
        printIndent();
        stream.println("<-- " + str);
    }

    private static void printIndent() {
        for (int i = 0; i < callDepth; i++)
            stream.print("  ");
    }
}