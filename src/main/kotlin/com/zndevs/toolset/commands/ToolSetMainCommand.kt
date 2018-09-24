package com.zndevs.toolset.commands

import com.zndevs.toolset.ToolSetCommand
import com.zndevs.toolset.ToolSetConfiguration
import com.zndevs.toolset.commands.tools.SimpleCommand
import com.zndevs.toolset.commands.tools.SubCommand
import com.zndevs.toolset.tools.ToolSetCommandTools
import org.bukkit.permissions.Permission
import org.bukkit.plugin.java.JavaPlugin

class ToolSetMainCommand : ToolSetCommand() {
    private var plugin: JavaPlugin? = null

    private val subCommands: Array<SubCommand> = arrayOf(
            SubCommand("command", arrayOf("c"), "Update or check the status of commands", ToolSetCommandCommand()),
            SubCommand("update", arrayOf("u", "upd"), "Update ToolSet modules or check if updates are required", ToolSetUpdateCommand()),
            SubCommand("reload", "Reload ToolSet and its modules", SimpleCommand("ToolSet Reloader", fun(_, _, _, _, _, r): Boolean {
                r.playerMessage = ToolSetCommandTools.reloadCommands(null)
                return true
            }, Permission("toolset.core.admin.reload")))
    )

    override fun getSubCommands(): Array<SubCommand> = subCommands
    override fun getCommandName(): String = "ToolSet"
    override fun setConfigurationDefaults(defaults: ToolSetConfiguration): ToolSetConfiguration = defaults
    override fun setPlugin(plugin: JavaPlugin) {
        this.plugin = plugin
    }
}