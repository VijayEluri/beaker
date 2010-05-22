package com.sonatype.beaker.lexicon.maven;

import com.sonatype.beaker.lexicon.MeepSupport;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class MojoExecute
    extends MeepSupport
{
    private String goal;

    private String executionId;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }
}