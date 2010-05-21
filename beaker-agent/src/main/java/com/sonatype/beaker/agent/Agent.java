package com.sonatype.beaker.agent;

import java.lang.instrument.Instrumentation;

/**
 * Beaker JavaAgent.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Agent
{
    private static Options options;

    public static void premain(final String options, final Instrumentation instrumentation) {
        Agent.options = new Options(options);

        System.out.println("Beaker loaded; with options: " + Agent.options);
        
        org.aspectj.weaver.loadtime.Agent.premain(options, instrumentation);
    }

    public static Options getOptions() {
        if (options == null) {
            throw new IllegalStateException();
        }
        return options;
    }

    public static Instrumentation getInstrumentation() {
        return org.aspectj.weaver.loadtime.Agent.getInstrumentation();
    }
}