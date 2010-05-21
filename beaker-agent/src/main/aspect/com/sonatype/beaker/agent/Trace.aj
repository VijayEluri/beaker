package com.sonatype.beaker.agent;

public aspect Trace
{
    pointcut myClass():
        within(org.apache.maven.artifact.*..*);

    pointcut myConstructor(): myClass() && execution(new(..));

    pointcut myMethod(): myClass() && execution(* *(..));

    before(): myConstructor() {
        Tracer.traceEntry("" + thisJoinPointStaticPart.getSignature());
    }

    after(): myConstructor() {
        Tracer.traceExit("" + thisJoinPointStaticPart.getSignature());
    }

    before(): myMethod() {
        Tracer.traceEntry("" + thisJoinPointStaticPart.getSignature());
    }

    after(): myMethod() {
        Tracer.traceExit("" + thisJoinPointStaticPart.getSignature());
    }
}