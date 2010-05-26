package org.sonatype.beaker.maven.lexicon;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("mojo-started")
public class MojoStarted
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

    @Override
    public String toString() {
        return "MojoStarted{" +
            "goal='" + goal + '\'' +
            ", executionId='" + executionId + '\'' +
            ", plugin=" + plugin +
            '}';
    }
}