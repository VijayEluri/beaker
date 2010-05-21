package com.sonatype.beaker.agent;

import java.lang.instrument.Instrumentation;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Agent
{
    private static Options options;

    private static AspectPath aspectPath;

    public static void premain(final String options, final Instrumentation instrumentation) {
        Agent.options = new Options(options);
        Agent.aspectPath = new AspectPath();

        System.out.println("Beaker loaded");
        System.out.println("  Options: " + Agent.options);
        System.out.println("  Aspect path: " + System.getProperty(AspectPath.MAVEN_ASPECT_PATH));
        
        org.aspectj.weaver.loadtime.Agent.premain(options, instrumentation);
    }

    public static Options getOptions() {
        if (options == null) {
            throw new IllegalStateException();
        }
        return options;
    }

    public static AspectPath getAspectPath() {
        if (aspectPath == null) {
            throw new IllegalStateException();
        }
        return aspectPath;
    }

    public static Instrumentation getInstrumentation() {
        return org.aspectj.weaver.loadtime.Agent.getInstrumentation();
    }

    public static class Options
    {
        private final String spec;

        public Options(final String spec) {
            assert spec != null;
            this.spec = spec;
        }

        @Override
        public String toString() {
            return "Options{" +
                "spec='" + spec + '\'' +
                '}';
        }
    }
}