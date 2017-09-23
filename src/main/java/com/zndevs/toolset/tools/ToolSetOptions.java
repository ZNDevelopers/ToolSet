package com.zndevs.toolset.tools;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Tools to help with configuration files and other general options
 */

public class ToolSetOptions {

    /**
     * ToolSet prefix
     */
    public static final String prefix = "&8[&6&lTOOLSET&8] &f";
    /**
     * ToolSet error prefix
     */
    public static final String errorPrefix = "&8[&6&lTOOLSET&8] &4&lError! &c";
    /**
     * ToolSet No-Perms message
     */
    public static final String noPermissionsMessage = errorPrefix + "It seems like you aren't allowed to " +
            "run this command. If you think this is wrong, please send the server administrators a message!";
    /**
     * ToolSet Plugin
     */
    public static JavaPlugin plugin;

    /* DEBUG MODE OPTIONS. DO NOT CHANGE UNLESS DEBUGGING */
    /**
     * ToolSet API URL.
     * If you have a local copy of the ToolSet website, change this to its URL
     * This will probably only apply for ZNDevs because only they have a local
     * copy of the website.
     */
    public static String toolSetApiPath = "http://zndevs.zoweb.me/toolset/";
    /**
     * Get public API url
     */
    public static String getToolSetApiPath(String file) {
        return toolSetApiPath + file;
    }


    /**
     * Check if a command is enabled from its config file
     *
     * @param filePath The file path of the command config file
     * @return True if the command is enable
     */
    public static boolean isCommandEnabled(String filePath) {
        YamlConfiguration configuration = ToolSetOptions.getDataOptions(filePath);

        return configuration.getBoolean("enabled");
    }

    /**
     * Sets the plugin to be used by the ToolSetOptions class
     *
     * @param plugin The plugin to be used
     */
    public static void setPlugin(JavaPlugin plugin) {
        ToolSetOptions.plugin = plugin;
    }

    /**
     * Get the options for a command from config.yml
     *
     * @param commandName The command to get the options for
     * @deprecated Not used anymore
     */
    @Deprecated
    public static ConfigurationSection getCommandOptions(String commandName) {
        return ToolSetOptions.getDataOptions("config.yml").getConfigurationSection("command-options").getConfigurationSection(commandName);
    }

    /**
     * Get command options from the command file
     *
     * @param fileName The command file
     * @return The command options
     */
    public static YamlConfiguration getDataOptions(String fileName) {
        return ToolSetOptions.getOptions(ToolSetOptions.getDataFile(fileName));
    }

    /**
     * Get the File for a command config file
     *
     * @param fileName The config file name
     * @return Config file
     */
    public static File getDataFile(String fileName) {
        return new File(ToolSetOptions.plugin.getDataFolder(), fileName);
    }

    /**
     * Get the directory that data is saved in
     *
     * @return Config directory
     */
    public static File getDataDirectory() {
        return ToolSetOptions.plugin.getDataFolder();
    }

    /**
     * Get YamlConfiguration from a File
     *
     * @param file File to convert to YamlConfiguration
     * @return Configuration
     */
    public static YamlConfiguration getOptions(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Get help text from command
     *
     * @param commandName    The command name to be displayed
     * @param commandOptions The different ways the command can be run. For example: <code>{ "help &lt;page-number&gt;" or "&lt;player&gt;" }</code>
     * @return Command help string
     */
    public static String getHelpFormat(String commandName, String[] commandOptions) {
        StringBuilder help = new StringBuilder("&8[&6&lTOOLSET HELP&8] &f&o" + commandName + "&f command\n");

        for (String current : commandOptions) {
            help.append(" - ").append(current).append("\n");
        }

        help = new StringBuilder(help.substring(0, help.length() - 1));

        return CommandResponse.parseColours(help.toString());
    }

}
