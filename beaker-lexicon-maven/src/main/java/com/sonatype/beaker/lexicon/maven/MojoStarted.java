package com.sonatype.beaker.lexicon.maven;

import com.sonatype.beaker.lexicon.MeepSupport;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("mojo-started")
public class MojoStarted
    extends MeepSupport
{
    @XStreamAsAttribute
    private String goal;

    @XStreamAsAttribute
    private String executionId;

    private PluginContext plugin;

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

    public PluginContext getPlugin() {
        if (plugin == null) {
            plugin = new PluginContext();
        }
        return plugin;
    }

    public void setPlugin(PluginContext plugin) {
        this.plugin = plugin;
    }
}