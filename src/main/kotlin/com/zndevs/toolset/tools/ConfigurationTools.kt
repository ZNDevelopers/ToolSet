package com.zndevs.toolset.tools

import com.zndevs.toolset.ToolSetConfiguration
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin

import java.io.*
import java.net.URL

/**
 * Tools to help with reloading configuration and commands
 */

object ConfigurationTools {
    /**
     * Set a configuration value if it has not yet been set
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    fun setIfNotSet(section: ConfigurationSection, path: String, value: Any): ConfigurationSection {
        if (section.get(path) == null) {
            section.set(path, value)
        }
        return section
    }

    /**
     * Set a configuration value if it has not yet been set [YamlConfiguration version]
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    fun setIfNotSet(section: YamlConfiguration, path: String, value: Any): YamlConfiguration {
        if (section.get(path) == null) {
            section.set(path, value)
        }
        return section
    }

    /**
     * Set a configuration value if it has not yet been set [ToolSetConfiguration version]
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    fun setIfNotSet(section: ToolSetConfiguration, path: String, value: Any): ToolSetConfiguration {
        if (section.get(path) == null) {
            section.set(path, value)
        }
        return section
    }

    /**
     * Creates a data file under the plugins/ToolSet folder
     * @param plugin The plugin that contains the file
     * @param resourcePath The resource path in the Java file
     * @param dataFileName The name of the file that will be saved
     * @return True if file created, false otherwise
     */
    fun createToolSetDataFile(plugin: Plugin, resourcePath: String, dataFileName: String): Boolean {
        val dataFilePath = ToolSetOptions.getDataFile(dataFileName)
        val resourceStream = plugin.javaClass.getResourceAsStream(resourcePath)
        val resourceUrl = plugin.javaClass.getResource(resourcePath)

        if (resourceUrl == null) {
            plugin.logger.severe("Could not save data file as it does not exist")
            return false
        }

        // only override file if it doesn't exist
        if (!dataFilePath.isFile) {
            // create file

            // write InputStream to data file
            try {
                BufferedReader(InputStreamReader(resourceStream)).use { reader ->
                    dataFilePath.parentFile.mkdirs()


                    val writer = BufferedWriter(FileWriter(dataFilePath))

                    var line: String?
                    do {
                        line = reader.readLine()
                        writer.write("$line\r\n")
                    } while (line != null)
                    writer.close()

                    return true
                }
            } catch (err: IOException) {
                plugin.logger.severe("Could not save data file: ${err.message}")
                err.printStackTrace()
            }

        }

        return false
    }
}
