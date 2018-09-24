package com.zndevs.toolset.tools

import com.zndevs.toolset.ToolSetCommand
import com.zndevs.toolset.ToolSetConfiguration
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin

import java.io.File
import java.io.IOException
import java.lang.reflect.Field
import java.util.HashMap

/**
 * Tools to help with commands
 */

object ToolSetCommandTools {
    val commandPlugins: MutableMap<String, JavaPlugin> = HashMap()
    val commandExecutors: MutableMap<String, ToolSetCommand> = HashMap()
    val toolSetPlugins: MutableMap<String, JavaPlugin> = HashMap()
    val toolSetPluginsInternal: MutableMap<String, JavaPlugin> = HashMap()

    /**
     * Disables a plugin's command
     *
     * @param command The command name
     * @return True if successfully disabled command, false otherwise
     */
    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    fun disableCommand(command: String): Boolean {
        val field = Bukkit.getPluginManager().javaClass.getDeclaredField("commandMap")
        field.isAccessible = true
        val map = field.get(Bukkit.getPluginManager()) as CommandMap
        return map.getCommand(command).unregister(map)
        //Bukkit.getPluginCommand(command).unregister(map);
    }

    fun disableCommandSoft(commandName: String) {
        val command = (commandExecutors as Map<String, ToolSetCommand>).getOrDefault(commandName, null)
        command?.enabled = false
    }

    fun enableCommand(plugin: JavaPlugin, command: String, executor: ToolSetCommand) {
        plugin.getCommand(command).executor = executor

        commandExecutors[command] = executor
        commandPlugins[command] = plugin

        plugin.server.pluginManager.registerEvents(executor, plugin)

        executor.setPlugin(plugin)
        executor.enabled = true
    }

    /**
     * Register a plugin command if it is set to be enabled in its config
     *
     * @param commandPlugin The command's plugin
     * @param command       The command to register
     * @param executor      The command executor
     * @return True if the command was enabled
     */
    fun registerIfEnabled(commandPlugin: JavaPlugin?, command: String, executor: ToolSetCommand): Boolean {
        val plugin = ToolSetOptions.plugin

        commandPlugin?.logger?.info("Loading command $command")

        // Create config file for this command
        var pluginConfig = ToolSetConfiguration(ToolSetOptions.dataDirectory)
        pluginConfig = executor.setConfigurationDefaults(pluginConfig)
        pluginConfig.setDefault("enabled", true)
        val fileName = pluginConfig.fileName
        if (fileName != null) {
            try {
                val filePath = pluginConfig.filePath
                filePath?.parentFile?.mkdirs()
                pluginConfig.save()
            } catch (e: IOException) {
                plugin?.logger?.severe("Could not create config file for command $command!")
                e.printStackTrace()
                return false
            }

        }

        if (pluginConfig.getBoolean("enabled")) {
            plugin?.logger?.info("Disabling command \"$command\"")
            try {
                disableCommand(command)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            plugin?.logger?.info("Enabling command \"$command\"")

            if (plugin != null) {
                enableCommand(plugin, command, executor)
            }

            return true
        } else {
            plugin?.logger?.info("Disabling command \"$command\"")
            try {
                disableCommand(command)
            } catch (e: IllegalAccessException) {
                plugin?.logger?.severe("Could not disable command \"" + command + "\": " + e.message)
            } catch (e: NoSuchFieldException) {
                plugin?.logger?.severe("Could not disable command \"" + command + "\": " + e.message)
            }

        }
        return false
    }

    /**
     * Reload commands from a plugin
     */
    fun reloadCommands() {
        for ((key) in commandExecutors) {
            val command = commandExecutors[key]
            val plugin = commandPlugins[key]

            if (command != null) {
                registerIfEnabled(plugin, key, command)
            }
        }
    }

    /**
     * Reload commands from a plugin and send a message to the executor
     *
     * @param setToAnythingToGetReturnValue Set this to anything to specify using a return value
     * @return All commands that were reloaded
     */
    fun reloadCommands(setToAnythingToGetReturnValue: Any?): String {
        var returnMessage = StringBuilder(ToolSetOptions.prefix + "Reloading all commands...\n")
        for ((key) in commandExecutors) {
            val command = commandExecutors[key]
            val plugin = commandPlugins[key]

            val result = registerIfEnabled(plugin, key, command!!)

            returnMessage.append(ToolSetOptions.prefix).append(if (result) "Enabled" else "Disabled").append(" command &l").append(key).append("&f.\n")
        }
        returnMessage = StringBuilder(CommandResponse.parseColours(returnMessage.toString()))
        return returnMessage.substring(0, returnMessage.length - 1)
    }

    /**
     * Unloads all current commands
     */
    fun unloadAllCommands() {
        for ((key) in commandExecutors) {
            val plugin = commandPlugins[key]

            try {
                disableCommand(key)
                plugin?.logger?.info("Disabled \"$key\"")
            } catch (e: IllegalAccessException) {
                plugin?.logger?.severe("Could not disable command \"$key\": ${e.message}")
            } catch (e: NoSuchFieldException) {
                plugin?.logger?.severe("Could not disable command \"$key\": ${e.message}")
            }

        }
    }

    /**
     * Adds a plugin to the list of currently running plugins
     *
     * @param pluginName The name of the plugin. Must be the same as the name on the downloader site for auto-updating to work
     * @param plugin     An instance of the plugin
     */
    fun addPlugin(pluginName: String, plugin: JavaPlugin) {
        toolSetPlugins[pluginName] = plugin
        toolSetPluginsInternal[plugin.description.name] = plugin
    }

    /**
     * Gets a plugin from the list of currently running plugins
     *
     * @param pluginName The name of the plugin
     * @return An instance of the plugin
     */
    fun getPlugin(pluginName: String): JavaPlugin? {
        return try {
            toolSetPlugins[pluginName]
        } catch (err: NullPointerException) {
            null
        }

    }
}
