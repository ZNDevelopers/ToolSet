package com.zndevs.toolset

import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration

import java.io.File
import java.io.IOException

/**
 * ToolSet Configuration class
 */

class ToolSetConfiguration : YamlConfiguration {
    private var _fileName: String? = null

    /**
     * Gets the data path set by init
     *
     * @return The file name
     */
    var filePath: File? = null
        private set

    /**
     * File name of the configuration
     */
    var fileName: String?
        get() = _fileName
        set(value) {
            _fileName = value
            filePath = File(filePath, fileName)
            load(this.filePath!!)
        }

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     */
    constructor(dataDirectory: File) {
        this.filePath = dataDirectory
    }

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     * @param fileName      The name of the file to save as (doesn't include Data Directory folder)
     */
    @Throws(IOException::class, InvalidConfigurationException::class)
    constructor(dataDirectory: File, fileName: String) {
        this.filePath = File(dataDirectory, fileName)
        this.fileName = fileName
        load(this.filePath!!)
    }

    /**
     * Creates a new instance of the ToolSetConfiguration class
     *
     * @param premadeConfig The already made YamlConfiguration
     * @param dataDirectory The directory that ToolSet Data files will be stored in
     */
    constructor(premadeConfig: YamlConfiguration, dataDirectory: File) {
        this.setDefaults(premadeConfig)
        this.filePath = dataDirectory
    }

    /**
     * Saves the configuration (warning: overrides!)
     *
     * @throws IOException If for some reason it can't save
     */
    @Throws(IOException::class)
    fun save() {
        this.save(this.filePath!!)
    }

    fun setDefault(path: String, value: Any) {
        if (get(path) == null) set(path, value)
    }
}
