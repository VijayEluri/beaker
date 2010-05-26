package com.sonatype.beaker.maven3;

import org.aspectj.lang.JoinPoint;
import java.io.PrintStream;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Trace
{
    public static int TRACELEVEL = 2;

    protected static PrintStream stream = System.err;

    protected static int callDepth = 0;

    public static void initStream(PrintStream s) {
        stream = s;
    }

    protected static void traceEntry(JoinPoint point) {
        traceEntry("" + point.getSignature().toLongString());
    }

    protected static void traceEntry(String str) {
        if (TRACELEVEL == 0) return;
        if (TRACELEVEL == 2) callDepth++;
        printEntering(str);
    }

    protected static void traceExit(JoinPoint point) {
        traceExit("" + point.getSignature().toLongString());
    }

    protected static void traceExit(String str) {
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
        for (int i = 0; i < callDepth; i++) {
            stream.print("  ");
        }
    }

    pointcut myClass():
        within(org.apache.maven.*..*) &&
        !execution(* get*()) &&
        !execution(boolean is*()) &&
        !execution(void set*(*)) &&
        !within(*..*.*Request) &&
        !within(*..*.*Result) &&
        !within(*..*.*Cache) &&
        !within(*..*.*Logger) &&
        !within(org.apache.maven.lifecycle.internal.DefaultExecutionEventCatapult) &&
        !within(org.apache.maven.cli.MavenCli) &&
        !within(org.apache.maven.execution.DefaultMavenExecutionRequestPopulator) &&
        !within(org.apache.maven.plugin.PluginParameterExpressionEvaluator) &&
        !within(org.apache.maven.project.ReactorModelPool) &&
        !within(org.apache.maven.lifecycle.internal.LifecyclePluginAnalyzerImpl) &&
        !within(org.apache.maven.project.MavenProject) &&
        !within(org.apache.maven.project.DefaultProjectBuilder) &&
        !within(org.apache.maven.project.ProjectSorter) &&
        !within(org.apache.maven.execution.MavenSession) &&
        !within(org.apache.maven.cli.MavenLoggerManager) &&
        !within(org.apache.maven.lifecycle.mapping.Lifecycle) &&
        !within(org.apache.maven.model.*) &&
        !within(org.apache.maven.model.*..*) &&
        !within(org.apache.maven.artifact.*) &&
        !within(org.apache.maven.artifact.*..*) &&
        !within(org.apache.maven.repository.*) &&
        !within(org.apache.maven.repository.*..*) &&
        !within(org.apache.maven.project.artifact.*) &&
        !within(org.apache.maven.plugin.descriptor.*) &&
        !within(org.apache.maven.plugin.descriptor.*..*) &&
        !within(org.apache.maven.settings.*) &&
        !within(org.apache.maven.settings.*..*);

    pointcut myConstructor():
        myClass() && execution(new(..));

    pointcut myMethod():
        myClass() && execution(* *(..));

    before(): myConstructor() {
        traceEntry(thisJoinPoint);
    }

    after(): myConstructor() {
        traceExit(thisJoinPoint);
    }

    before(): myMethod() {
        traceEntry(thisJoinPoint);
    }

    after(): myMethod() {
        traceExit(thisJoinPoint);
    }
}
