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
@XStreamAlias("session-started")
public class SessionStarted
    extends MeepSupport
{
    @XStreamAsAttribute
    private File localRepositoryPath;

    @XStreamAsAttribute
    private boolean offline;

    @XStreamAsAttribute
    private boolean interactiveMode;

    @XStreamAsAttribute
    private File userSettingsFile;

    @XStreamAsAttribute
    private File globalSettingsFile;

    @XStreamAsAttribute
    private File userToolchainsFile;

    @XStreamAsAttribute
    private String baseDirectory;

    private List<String> goals;

    @XStreamAsAttribute
    private boolean useReactor;

    @XStreamAsAttribute
    private boolean recursive;

    @XStreamAsAttribute
    private File pom;

    @XStreamAsAttribute
    private String reactorFailureBehavior;

    private List<String> selectedProjects;

    @XStreamAsAttribute
    private String resumeFrom;

    @XStreamAsAttribute
    private String makeBehavior;

    private Properties systemProperties;

    private Properties userProperties;

    @XStreamAsAttribute
    private Date startTime;

    @XStreamAsAttribute
    private boolean showErrors;

    private List<String> activeProfiles;

    private List<String> inactiveProfiles;

    @XStreamAsAttribute
    private String globalChecksumPolicy;

    @XStreamAsAttribute
    private boolean updateSnapshots;

//    private List<ArtifactRepository> remoteRepositories;

//    private List<ArtifactRepository> pluginArtifactRepositories;

    @XStreamAsAttribute
    private String threadCount;

    @XStreamAsAttribute
    private boolean perCoreThreadCount;

    public File getLocalRepositoryPath() {
        return localRepositoryPath;
    }

    public void setLocalRepositoryPath(File localRepositoryPath) {
        this.localRepositoryPath = localRepositoryPath;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    public boolean isInteractiveMode() {
        return interactiveMode;
    }

    public void setInteractiveMode(boolean interactiveMode) {
        this.interactiveMode = interactiveMode;
    }

    public File getUserSettingsFile() {
        return userSettingsFile;
    }

    public void setUserSettingsFile(File userSettingsFile) {
        this.userSettingsFile = userSettingsFile;
    }

    public File getGlobalSettingsFile() {
        return globalSettingsFile;
    }

    public void setGlobalSettingsFile(File globalSettingsFile) {
        this.globalSettingsFile = globalSettingsFile;
    }

    public File getUserToolchainsFile() {
        return userToolchainsFile;
    }

    public void setUserToolchainsFile(File userToolchainsFile) {
        this.userToolchainsFile = userToolchainsFile;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    public boolean isUseReactor() {
        return useReactor;
    }

    public void setUseReactor(boolean useReactor) {
        this.useReactor = useReactor;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public File getPom() {
        return pom;
    }

    public void setPom(File pom) {
        this.pom = pom;
    }

    public String getReactorFailureBehavior() {
        return reactorFailureBehavior;
    }

    public void setReactorFailureBehavior(String reactorFailureBehavior) {
        this.reactorFailureBehavior = reactorFailureBehavior;
    }

    public List<String> getSelectedProjects() {
        return selectedProjects;
    }

    public void setSelectedProjects(List<String> selectedProjects) {
        this.selectedProjects = selectedProjects;
    }

    public String getResumeFrom() {
        return resumeFrom;
    }

    public void setResumeFrom(String resumeFrom) {
        this.resumeFrom = resumeFrom;
    }

    public String getMakeBehavior() {
        return makeBehavior;
    }

    public void setMakeBehavior(String makeBehavior) {
        this.makeBehavior = makeBehavior;
    }

    public Properties getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Properties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Properties getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(Properties userProperties) {
        this.userProperties = userProperties;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public boolean isShowErrors() {
        return showErrors;
    }

    public void setShowErrors(boolean showErrors) {
        this.showErrors = showErrors;
    }

    public List<String> getActiveProfiles() {
        return activeProfiles;
    }

    public void setActiveProfiles(List<String> activeProfiles) {
        this.activeProfiles = activeProfiles;
    }

    public List<String> getInactiveProfiles() {
        return inactiveProfiles;
    }

    public void setInactiveProfiles(List<String> inactiveProfiles) {
        this.inactiveProfiles = inactiveProfiles;
    }

    public String getGlobalChecksumPolicy() {
        return globalChecksumPolicy;
    }

    public void setGlobalChecksumPolicy(String globalChecksumPolicy) {
        this.globalChecksumPolicy = globalChecksumPolicy;
    }

    public boolean isUpdateSnapshots() {
        return updateSnapshots;
    }

    public void setUpdateSnapshots(boolean updateSnapshots) {
        this.updateSnapshots = updateSnapshots;
    }

    public String getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(String threadCount) {
        this.threadCount = threadCount;
    }

    public boolean isPerCoreThreadCount() {
        return perCoreThreadCount;
    }

    public void setPerCoreThreadCount(boolean perCoreThreadCount) {
        this.perCoreThreadCount = perCoreThreadCount;
    }
}