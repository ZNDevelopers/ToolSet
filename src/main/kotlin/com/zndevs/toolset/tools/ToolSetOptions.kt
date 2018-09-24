package com.zndevs.toolset.tools

import org.apache.commons.lang.StringUtils
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

import java.io.File

/**
 * Tools to help with configuration files and other general options
 */

object ToolSetOptions {

    /**
     * ToolSet prefix
     */
    const val prefix = "&8[&6&lTOOLSET&8] &f"
    /**
     * ToolSet error prefix
     */
    const val errorPrefix = "&8[&6&lTOOLSET&8] &4&lError! &c"
    const val helpPrefix = "&8[&7&lTOOLSET HELP&8] &f&o"
    /**
     * ToolSet No-Perms message
     */
    val noPermissionsMessage = errorPrefix + "It seems like you aren't allowed to " +
            "run this command. If you think this is wrong, please send the server administrators a message!"
    /**
     * ***IMPORTANT:** DO NOT CHANGE*
     *
     * ToolSet plugin instance
     */
    var plugin: JavaPlugin? = null

    /* DEBUG MODE OPTIONS. DO NOT CHANGE UNLESS DEBUGGING */
    /**
     * ToolSet API URL.
     * If you have a local copy of the ToolSet website for debugging, set the TOOLSET_API environment variable.
     */
    var toolSetApiPath: String? = null

    /**
     * Get the directory that data is saved in
     *
     * @return Config directory
     */
    val dataDirectory: File
        get() = ToolSetOptions.plugin!!.dataFolder

    /**
     * Get public API url
     */
    fun getToolSetApiPath(file: String): String {
        return toolSetApiPath!! + file
    }
    /* END DEBUG MODE OPTIONS. */


    /**
     * Check if a command is enabled from its config file
     *
     * @param filePath The file path of the command config file
     * @return True if the command is enable
     */
    fun isCommandEnabled(filePath: String): Boolean {
        val configuration = ToolSetOptions.getDataOptions(filePath)

        return configuration.getBoolean("enabled")
    }

    /**
     * Get the options for a command from config.yml
     *
     * @param commandName The command to get the options for
     */
    @Deprecated("Not used anymore", ReplaceWith("ToolSetOptions.getDataOptions(\"config.yml\").getConfigurationSection(\"command-options\").getConfigurationSection(commandName)"))
    fun getCommandOptions(commandName: String): ConfigurationSection {
        return ToolSetOptions.getDataOptions("config.yml").getConfigurationSection("command-options").getConfigurationSection(commandName)
    }

    /**
     * Get command options from the command file
     *
     * @param fileName The command file
     * @return The command options
     */
    fun getDataOptions(fileName: String): YamlConfiguration {
        return ToolSetOptions.getOptions(ToolSetOptions.getDataFile(fileName))
    }

    /**
     * Get the File for a command config file
     *
     * @param fileName The config file name
     * @return Config file
     */
    fun getDataFile(fileName: String): File {
        return File(dataDirectory, fileName)
    }

    /**
     * Get YamlConfiguration from a File
     *
     * @param file File to convert to YamlConfiguration
     * @return Configuration
     */
    fun getOptions(file: File): YamlConfiguration {
        return YamlConfiguration.loadConfiguration(file)
    }

    /**
     * Get help text from command
     *
     * @param commandName    The command name to be displayed
     * @param commandOptions The different ways the command can be run. For example: `{ "help <page-number>" or "<player>" }`
     * @return Command help string
     */
    fun getHelpFormat(commandName: String, commandOptions: Array<String>): String? {
        var help = StringBuilder("&8[&6&lTOOLSET HELP&8] &f&o$commandName&f command\n")

        for (current in commandOptions) {
            help.append(" - ").append(current).append("\n")
        }

        help = StringBuilder(help.substring(0, help.length - 1))

        return CommandResponse.parseColours(help.toString())
    }

    /**
     * Get help text from a command using Nodes
     *
     * @param commandName    The command name to be displayed
     * @param commandOptions The different ways the command can be run
     * @return Command help string
     */
    fun getHelpFormat(commandName: String, commandOptions: Iterable<Node<String>>): String? {
        val builder = StringBuilder(helpPrefix).append(commandName).append("&f command\n")

        for (commandOption in commandOptions) {
            helpFormatRecursiveGenerator(commandOption, builder, 0)
        }

        return CommandResponse.parseColours(builder.toString())
    }

    private fun helpFormatRecursiveGenerator(currentNode: Node<String>, builder: StringBuilder, indent: Int) {
        var idnt = indent
        builder.append(StringUtils.leftPad("", idnt)).append(" - ").append(currentNode.value).append("\n")
        idnt += 2
        for (stringNode in currentNode.getChildren()) {
            helpFormatRecursiveGenerator(stringNode, builder, idnt)
        }
    }
}
