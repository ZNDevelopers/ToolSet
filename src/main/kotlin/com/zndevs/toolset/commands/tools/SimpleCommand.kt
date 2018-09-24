package com.zndevs.toolset.commands.tools

import com.zndevs.toolset.ToolSetCommand
import com.zndevs.toolset.ToolSetConfiguration
import com.zndevs.toolset.tools.CommandResponse
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.permissions.Permission

class SimpleCommand(commandName: String, commandRunner: SimpleCommandLambda, commandPermission: Permission) : ToolSetCommand() {
    private val name: String = commandName
    private val runner: SimpleCommandLambda = commandRunner
    private val permission: Permission = commandPermission

    constructor(commandName: String, commandRunner: SimpleCommandLambda) : this(commandName, commandRunner, Permission("*"))

    override fun getSubCommands(): Array<SubCommand> {
        return emptyArray()
    }

    override fun getCommandName(): String {
        return name
    }

    override fun setConfigurationDefaults(defaults: ToolSetConfiguration): ToolSetConfiguration {
        return defaults
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val response = CommandResponse()
        val result = runner(sender, command, this, label, args,response)
        response.send(sender, Bukkit.getServer())
        return result
    }

    override fun getRequiredPermission(subCommand: String): Permission {
        return permission
    }
}