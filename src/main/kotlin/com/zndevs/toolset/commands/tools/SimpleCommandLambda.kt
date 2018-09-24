package com.zndevs.toolset.commands.tools

import com.zndevs.toolset.tools.CommandResponse
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

/**
 * <h3>Arguments:</h3>
 *
 *  1. CommandSender sender
 *  2. Command command
 *  3. SimpleCommand oCaller
 *  4. String label
 *  5. String[] args
 *  6. CommandResponse response
 *
 *
 * <h3>Returns: `Boolean`</h3>
 */
typealias SimpleCommandLambda = (sender: CommandSender, command: Command, oCaller: SimpleCommand, label: String, args: Array<String>, response: CommandResponse) -> Boolean