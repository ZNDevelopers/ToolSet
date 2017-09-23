package com.zndevs.toolset.commands;

import com.zndevs.toolset.ToolSetCommand;
import com.zndevs.toolset.ToolSetConfiguration;
import com.zndevs.toolset.tools.CommandResponse;
import com.zndevs.toolset.tools.ToolSetCommandTools;
import com.zndevs.toolset.tools.ToolSetOptions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * ToolSet Reload command - Reloads all ToolSet modules
 */

public class ToolSetReloadCommand implements ToolSetCommand {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("toolset.core.admin.reload")) {
            String response = ToolSetCommandTools.reloadCommands(null);
            sender.sendMessage(response);
            sender.sendMessage(CommandResponse.parseColours(ToolSetOptions.prefix + "Reloaded"));
            return true;
        } else {
            sender.sendMessage(CommandResponse.parseColours(ToolSetOptions.noPermissionsMessage));
        }
        return false;
    }

    @Override
    public ToolSetConfiguration setConfigurationDefaults(ToolSetConfiguration defaults) {
        return defaults;
    }

}
