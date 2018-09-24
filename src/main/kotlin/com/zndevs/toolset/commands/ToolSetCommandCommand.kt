package com.zndevs.toolset.commands

import com.zndevs.toolset.ToolSetCommand
import com.zndevs.toolset.ToolSetConfiguration
import com.zndevs.toolset.commands.tools.SubCommand
import com.zndevs.toolset.tools.ToolSetCommandTools
import com.zndevs.toolset.tools.ToolSetOptions
import org.bukkit.permissions.Permission

class ToolSetCommandCommand : ToolSetCommand() {
    override fun setConfigurationDefaults(defaults: ToolSetConfiguration): ToolSetConfiguration {
        return defaults
    }

    private val subCommands: Array<SubCommand> = arrayOf(
            SubCommand("enable", arrayOf("on", "e"), "<command-name>", "Enable a command.", fun(_, _, _, _, a, r): Boolean {
                if (a.size != 1) {
                    r.playerMessage = ToolSetOptions.errorPrefix + "Invalid arguments"
                    return false
                }

                val executor = ToolSetCommandTools.commandExecutors[a[0]]
                val plugin = ToolSetCommandTools.commandPlugins[a[0]]

                if (executor == null || plugin == null) {
                    r.playerMessage = ToolSetOptions.errorPrefix + "Command not found"
                    return false
                }

                ToolSetCommandTools.enableCommand(plugin, a[0], executor)
                r.playerMessage = ToolSetOptions.prefix + "Enabled " + a[0]
                return true
            }),
            SubCommand("disable", arrayOf("off", "d"), "<command-name>", "Disable a command", fun(_, _, _, _, a, r): Boolean {
                if (a.size != 1) {
                    r.playerMessage = ToolSetOptions.errorPrefix + "Invalid arguments"
                    return false
                }

                try {
                    if (ToolSetCommandTools.disableCommand(a[0])) {
                        r.playerMessage = ToolSetOptions.prefix + "Disabled " + a[0]
                    } else {
                        r.playerMessage = ToolSetOptions.errorPrefix + "Could not disable " + a[0]
                    }
                    return true
                } catch (err: Exception) {
                    when (err) {
                        is NoSuchFieldException,
                        is IllegalAccessException -> {
                            err.printStackTrace()
                            return false
                        }
                        else -> throw err
                    }
                }
            }),
            SubCommand("check", arrayOf("chk", "if", "c"), "<command-name>", "Check if a command is enabled", fun(_, _, _, _, a, r): Boolean {
                if (a.size != 1) {
                    r.playerMessage = ToolSetOptions.errorPrefix + "Invalid arguments"
                    return false
                }

                if (ToolSetCommandTools.commandExecutors[a[0]]?.enabled == true) {
                    r.playerMessage = ToolSetOptions.prefix + "&o" + a[0] + "&f is &aenabled&f."
                } else if (ToolSetCommandTools.commandExecutors.containsKey(a[0])) {
                    r.playerMessage = ToolSetOptions.prefix + "&o" + a[0] + "&f is &cdisabled&f."
                } else {
                    r.playerMessage = ToolSetOptions.errorPrefix + "Could not find that command."
                }

                return true
            })
    )

    override fun getSubCommands(): Array<SubCommand> = subCommands
    override fun getCommandName(): String = "ToolSet Command Controller"
    override fun getRequiredPermission(subCommand: String): Permission = Permission("toolset.core.admin.commands")
}