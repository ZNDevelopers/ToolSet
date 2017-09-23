package com.zndevs.toolset.tools;

import com.zndevs.toolset.ToolSetCommand;
import com.zndevs.toolset.ToolSetConfiguration;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Tools to help with commands
 */

public class ToolSetCommandTools {
    public static final Map<String, JavaPlugin> commandPlugins = new HashMap<>();
    public static final Map<String, ToolSetCommand> commandExecutors = new HashMap<>();
    public static final Map<String, JavaPlugin> toolSetPlugins = new HashMap<>();
    
    /**
            * Disables a plugin's command
            *
            * @param commandPlugin The command's plugin
            * @param command       The command name
     */
    public static void disableCommand(JavaPlugin commandPlugin, String command) throws NoSuchFieldException, IllegalAccessException {
        final Field field = commandPlugin.getServer().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);
        CommandMap map = (CommandMap) field.get(commandPlugin.getServer());
        commandPlugin.getCommand(command).unregister(map);
    }

    /**
     * Register a plugin command if it is set to be enabled in its config
     *
     * @param commandPlugin The command's plugin
     * @param command       The command to register
     * @param executor      The command executor
     * @return True if the command was enabled
     */
    public static boolean registerIfEnabled(JavaPlugin commandPlugin, String command, ToolSetCommand executor) {
        JavaPlugin plugin = ToolSetOptions.plugin;

        commandPlugin.getLogger().info("Loading command " + command);

        // Create config file for this command
        ToolSetConfiguration pluginConfig = new ToolSetConfiguration(ToolSetOptions.getDataDirectory());
        pluginConfig = executor.setConfigurationDefaults(pluginConfig);
        pluginConfig.setDefault("enabled", true);
        String fileName = pluginConfig.getFileName();
        if (fileName != null) {
            try {
                File filePath = pluginConfig.getFilePath();
                filePath.getParentFile().mkdirs();
                pluginConfig.save();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create config file for command " + command + "!");
                e.printStackTrace();
                return false;
            }
        }

        if (pluginConfig.getBoolean("enabled")) {
            plugin.getLogger().info("Disabling command \"" + command + "\"");
            try {
                disableCommand(commandPlugin, command);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            plugin.getLogger().info("Enabling command \"" + command + "\"");
            commandPlugin.getCommand(command).setExecutor(executor);

            commandExecutors.put(command, executor);
            commandPlugins.put(command, commandPlugin);

            return true;
        } else {
            plugin.getLogger().info("Disabling command \"" + command + "\"");
            try {
                disableCommand(commandPlugin, command);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                plugin.getLogger().severe("Could not disable command \"" + command + "\": " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Reload commands from a plugin
     */
    public static void reloadCommands() {
        for (Map.Entry<String, ToolSetCommand> entry : commandExecutors.entrySet()) {
            String key = entry.getKey();
            ToolSetCommand command = commandExecutors.get(key);
            JavaPlugin plugin = commandPlugins.get(key);

            registerIfEnabled(plugin, key, command);
        }
    }

    /**
     * Reload commands from a plugin and send a message to the executor
     *
     * @param setToAnythingToGetReturnValue Set this to anything to specify using a return value
     * @return All commands that were reloaded
     */
    public static String reloadCommands(Object setToAnythingToGetReturnValue) {
        StringBuilder returnMessage = new StringBuilder(ToolSetOptions.prefix + "Reloading all commands...\n");
        for (Map.Entry<String, ToolSetCommand> entry : commandExecutors.entrySet()) {
            String key = entry.getKey();
            ToolSetCommand command = commandExecutors.get(key);
            JavaPlugin plugin = commandPlugins.get(key);

            boolean result = registerIfEnabled(plugin, key, command);

            returnMessage.append(ToolSetOptions.prefix).append(result ? "Enabled" : "Disabled").append(" command &l").append(key).append("&f.\n");
        }
        returnMessage = new StringBuilder(CommandResponse.parseColours(returnMessage.toString()));
        return returnMessage.substring(0, returnMessage.length() - 1);
    }

    /**
     * Unloads all current commands
     */
    public static void unloadAllCommands() {
        for (Map.Entry<String, ToolSetCommand> entry : commandExecutors.entrySet()) {
            String key = entry.getKey();
            JavaPlugin plugin = commandPlugins.get(key);

            try {
                disableCommand(plugin, key);
                plugin.getLogger().info("Disabled \"" + key + "\"");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                plugin.getLogger().severe("Could not disable command \"" + key + "\": " + e.getMessage());
            }
        }
    }

    /**
     * Adds a plugin to the list of currently running plugins
     *
     * @param pluginName The name of the plugin. Must be the same as the name on the downloader site for auto-updating to work
     * @param plugin     An instance of the plugin
     */
    public static void addPlugin(String pluginName, JavaPlugin plugin) {
        toolSetPlugins.put(pluginName, plugin);
    }

    /**
     * Gets a plugin from the list of currently running plugins
     *
     * @param pluginName The name of the plugin
     * @return An instance of the plugin
     */
    public static JavaPlugin getPlugin(String pluginName) {
        try {
            return toolSetPlugins.get(pluginName);
        } catch (NullPointerException err) {
            return null;
        }
    }
}
