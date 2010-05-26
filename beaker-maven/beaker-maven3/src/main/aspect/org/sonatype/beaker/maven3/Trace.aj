package org.sonatype.beaker.maven3;

import org.aspectj.lang.JoinPoint;
import static org.sonatype.beaker.maven.JoinPointTrace.entry;
import static org.sonatype.beaker.maven.JoinPointTrace.exit;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public aspect Trace
{
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
        entry(thisJoinPoint);
    }

    after(): myConstructor() {
        exit(thisJoinPoint);
    }

    before(): myMethod() {
        entry(thisJoinPoint);
    }

    after(): myMethod() {
        exit(thisJoinPoint);
    }
}
