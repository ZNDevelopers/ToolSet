package com.zndevs.toolset.commands;

import com.zndevs.toolset.ToolSetCommand;
import com.zndevs.toolset.ToolSetConfiguration;
import com.zndevs.toolset.tools.CommandResponse;
import com.zndevs.toolset.tools.ToolSetCommandTools;
import com.zndevs.toolset.tools.ToolSetOptions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Command options command
 */

public class ToolSetCommandCommand implements ToolSetCommand {
    @Override
    public ToolSetConfiguration setConfigurationDefaults(ToolSetConfiguration section) {
        // No options
        return section;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        CommandResponse response = new CommandResponse();

        if (!sender.hasPermission("toolset.core.admin.commands")) {
            response.setPlayerMessage(ToolSetOptions.noPermissionsMessage);
        }

        String helpMessage = ToolSetOptions.getHelpFormat(command.getName(), new String[] {
                "toolset-command enable <command-name>",
                "toolset-command disable <command-name>",
                "toolset-command check <command-name>"
        });

        if (args.length != 2) {
            response.setPlayerMessage(helpMessage);
        } else {
            switch (args[0]) {
                case "disable":
                    try {
                        ToolSetCommandTools.disableCommand(args[1]);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        }

        response.send(sender);

        return true;
    }
}
