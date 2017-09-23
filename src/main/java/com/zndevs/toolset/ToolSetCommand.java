package com.zndevs.toolset;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * ToolSet Command Interface
 */

public interface ToolSetCommand extends CommandExecutor {

    // Default ToolSetCommand command
    @Override
    boolean onCommand(CommandSender sender,
                      Command command,
                      String label,
                      String[] args);

    // Extended methods
    ToolSetConfiguration setConfigurationDefaults(ToolSetConfiguration defaults);
}
