package com.sonatype.beaker.core.agent;

import java.lang.instrument.Instrumentation;

/**
 * Beaker JavaAgent.
 *
 * This is currently a thin-wrapper around the AspectJ load-time weaver.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Agent
{
    private static String options;

    public static void premain(final String options, final Instrumentation instrumentation) {
        Agent.options = options;
        org.aspectj.weaver.loadtime.Agent.premain(options, instrumentation);
    }

    public static String getOptions() {
        return options;
    }

    public static Instrumentation getInstrumentation() {
        return org.aspectj.weaver.loadtime.Agent.getInstrumentation();
    }
}