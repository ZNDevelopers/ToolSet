package com.zndevs.toolset

import com.zndevs.toolset.commands.tools.SubCommand
import com.zndevs.toolset.tools.CommandResponse
import com.zndevs.toolset.tools.Node
import com.zndevs.toolset.tools.ToolSetOptions
import mu.KotlinLogging
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.event.Listener
import org.bukkit.permissions.Permission
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

private val log = KotlinLogging.logger {}
abstract class ToolSetCommand : CommandExecutor, Listener, TabCompleter {
    private companion object {
        fun generateHelpRecursive(node: Node<String>, subCommand: SubCommand) {
            val builder = StringBuilder()

            if (subCommand.description != null) {
                // the command has a description, add a precursor to the builder
                builder.append("&l")
            }

            builder.append(subCommand.longName)

            if (subCommand.shortNames.isNotEmpty()) {
                builder.append(", ").append(subCommand.shortNames.joinToString(", "))
            }

            if (subCommand.inlineArgs != null) {
                builder.append(" &f&o").append(subCommand.inlineArgs)
            }

            if (subCommand.description != null) {
                builder.append("&7: ").append(subCommand.description).append("&f")
            }

            node.value = builder.toString()

            for (command in subCommand.command.getSubCommands()) {
                val subNode = node.createChild()
                generateHelpRecursive(subNode, command)
            }
        }
    }

    var enabled = true

    private fun generateHelp(): Iterable<Node<String>> {
        val subCommands = getSubCommands()

        val nodes = ArrayList<Node<String>>()

        for (subCommand in subCommands) {
            val node = Node<String>()
            generateHelpRecursive(node, subCommand)
            nodes.add(node)
        }

        return nodes
    }

    /**
     * Returns a command's subcommands.
     * Note: run last in your command executor!
     *
     * @param sender Person running the command
     * @param command Command's name as a string
     * @param label Unknown what this is
     * @param args Arguments passed into the command
     * @return True if the command succeeded in running, false otherwise
     */
    fun run(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val help = generateHelp()
        val commandName = getCommandName()

        if (args.isEmpty()) {
            // no arguments, no sub command will work
            sender.sendMessage(ToolSetOptions.getHelpFormat(commandName, help))
            return false
        }

        val subCommands = getSubCommands()
        for (subCommand in subCommands) {
            if (args[0] == subCommand.longName || subCommand.shortNames.indexOf(args[0]) > -1) {
                return subCommand.command.onCommand(sender, command, label, args.copyOfRange(1, args.size))
            }
        }

        // invalid option
        sender.sendMessage(ToolSetOptions.getHelpFormat(commandName, help))
        return false
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (!enabled) {
            sender.sendMessage(CommandResponse.parseColours(ToolSetOptions.errorPrefix + "This command has been disabled by an administrator"))
        }

        return execute(sender, command, label, args)
    }

    fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean = run(sender, command, label, args)

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<String>): List<String> {
        val tabCompletions = ArrayList<String>()
        for (subCommand in getSubCommands()) {
            for (arg in args) {
                var containsWord = false
                var foundWord = ""
                for (shortName in subCommand.shortNames) {
                    if (shortName.indexOf(arg) == 0) {
                        containsWord = true
                        foundWord = shortName
                        break
                    }
                }
                if (subCommand.longName.indexOf(arg) == 0) {
                    containsWord = true
                    foundWord = subCommand.longName
                }

                if (containsWord) tabCompletions.add(foundWord)
            }
        }
        return tabCompletions
    }

    /**
     * ***IMPORTANT**: Do not recreate subcommands when this is run!*
     *
     * It gets run multiple times. Instead store either an array of `SubCommand`s and directly return it, or store each
     * `SubCommand` separately and return them in an array (first option is recommended).
     *
     * Return a list of subcommands that can be accessed with `/<command> <subcommand>`. Will also be added to the help
     * list.
     *
     * @return Subcommands in an array
     */
    abstract fun getSubCommands(): Array<SubCommand>

    /**
     * Gets the name of the command
     */
    abstract fun getCommandName(): String

    /**
     * Gets the permission required to run the specified subcommand
     * @param subCommand Command to check permissions against
     */
    open fun getRequiredPermission(subCommand: String): Permission = Permission("*")

    /**
     * Sets the default configuration values for this command
     */
    abstract fun setConfigurationDefaults(defaults: ToolSetConfiguration): ToolSetConfiguration

    /**
     * Sets the plugin using this command.
     */
    open fun setPlugin(plugin: JavaPlugin) {}
}