package com.zndevs.toolset;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * ToolSet Configuration class
 */

public class ToolSetConfiguration extends YamlConfiguration {

    private File configFile;
    private String fileName;

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     */
    public ToolSetConfiguration(File dataDirectory) {
        this.configFile = dataDirectory;
    }

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     * @param fileName      The name of the file to save as (doesn't include Data Directory folder)
     */
    public ToolSetConfiguration(File dataDirectory, String fileName) throws IOException, InvalidConfigurationException {
        this.configFile = new File(dataDirectory, fileName);
        this.fileName = fileName;
        load(this.configFile);
    }

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param premadeConfig The already made YamlConfiguration
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     */
    public ToolSetConfiguration(YamlConfiguration premadeConfig, File dataDirectory) {
        this.setDefaults(premadeConfig);
        this.configFile = dataDirectory;
    }

    /**
     * Gets the file name set by setFileName
     *
     * @return The file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the file name that the configuration will be saved in
     *
     * @param fileName The name of the file to save as (doesn't include Data Directory folder)
     */
    public void setFileName(String fileName) throws IOException, InvalidConfigurationException {
        this.fileName = fileName;
        this.configFile = new File(configFile, fileName);
        load(this.configFile);
    }

    /**
     * Gets the data path set by init
     *
     * @return The file name
     */
    public File getFilePath() {
        return this.configFile;
    }

    /**
     * Saves the configuration (warning: overrides!)
     *
     * @throws IOException If for some reason it can't save
     */
    public void save() throws IOException {
        this.save(this.configFile);
    }

    public void setDefault(String path, Object value) {
        if (get(path) == null) set(path, value);
    }
}
