package com.zndevs.toolset.updater;

/**
 * Stores module versions, a name, and some data
 */

public class CommandUpdateVersions {

    private String commandName = "";
    private String oldVersion = "";
    private String newVersion = "";
    private Object data;

    public CommandUpdateVersions() {
    }

    public CommandUpdateVersions(String commandName, String oldVersion, String newVersion) {
        setCommandName(commandName);
        setOldVersion(oldVersion);
        setNewVersion(newVersion);
    }

    public CommandUpdateVersions(String commandName, String oldVersion, String newVersion, Object otherData) {
        setCommandName(commandName);
        setOldVersion(oldVersion);
        setNewVersion(newVersion);
        setOtherData(otherData);
    }

    public String getCommandName() {
        return commandName;
    }

    void setCommandName(String name) {
        commandName = name;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    void setOldVersion(String version) {
        oldVersion = version;
    }

    public String getNewVersion() {
        return newVersion;
    }

    void setNewVersion(String version) {
        newVersion = version;
    }

    public Object getOtherData() {
        return data;
    }

    void setOtherData(Object data) {
        this.data = data;
    }

}
