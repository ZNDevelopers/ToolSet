package com.zndevs.toolset.commands;

import com.zndevs.toolset.ToolSetCommand;
import com.zndevs.toolset.ToolSetConfiguration;
import com.zndevs.toolset.tools.CommandResponse;
import com.zndevs.toolset.tools.ToolSetOptions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base ToolSet Command
 */

public class ToolSetMainCommand implements ToolSetCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandResponse response = new CommandResponse();

        if (args.length > 0) {
            List<String> argsList = new ArrayList<>(Arrays.asList(args));
            argsList.subList(0, 1).clear();
            String[] argsCut = argsList.toArray(new String[argsList.size()]);

            switch (args[0]) {
                case "update":
                case "u":
                    new ToolSetUpdateCommand().onCommand(sender, command, label, argsCut);
                    break;
                case "reload":
                case "r":
                    new ToolSetReloadCommand().onCommand(sender, command, label, argsCut);
                    break;
                default:
                    response.setPlayerMessage(ToolSetOptions.getHelpFormat("ToolSet", new String[]{
                            "update, u",
                            "reload, r"
                    }));
                    break;
            }
        } else {
            response.setPlayerMessage(ToolSetOptions.getHelpFormat("ToolSet", new String[]{
                    "update, u",
                    "reload, r"
            }));
        }

        response.send(sender);

        return true;
    }

    @Override
    public ToolSetConfiguration setConfigurationDefaults(ToolSetConfiguration defaults) {
        // do nothing
        return defaults;
    }
}
