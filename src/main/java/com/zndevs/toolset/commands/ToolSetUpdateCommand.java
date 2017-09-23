package com.zndevs.toolset.commands;

import com.zndevs.toolset.ToolSetCommand;
import com.zndevs.toolset.ToolSetConfiguration;
import com.zndevs.toolset.tools.CommandResponse;
import com.zndevs.toolset.tools.ToolSetOptions;
import com.zndevs.toolset.tools.URLDownloader;
import com.zndevs.toolset.updater.CommandUpdateVersions;
import com.zndevs.toolset.updater.ToolSetUpdater;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * ToolSet Update command - Updates all official ToolSet modules
 */

public class ToolSetUpdateCommand implements ToolSetCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        CommandResponse response = new CommandResponse();

        if (args.length == 0) {
            response.setPlayerMessage(ToolSetOptions.getHelpFormat("ToolSet Updater", new String[]{
                    "check, chk, c",
                    "update, upd, u"
            }));
        } else {
            String arg = args[0];
            switch (arg) {
                case "check":
                case "chk":
                case "c":
                    response = checkForUpdates(sender, response);
                    break;
                case "update":
                case "upd":
                case "u":
                    response = doUpdate(sender, response);
                    break;
                default:
                    response.setPlayerMessage(ToolSetOptions.getHelpFormat("ToolSet Updater", new String[]{
                            "check, chk, c",
                            "update, upd, u"
                    }));
                    break;
            }
        }

        if (response.usePlayerMessage()) {
            sender.sendMessage(response.getPlayerMessage());
        }
        return false;
    }

    @Override
    public ToolSetConfiguration setConfigurationDefaults(ToolSetConfiguration defaults) {
        return defaults;
    }

    private CommandResponse checkForUpdates(CommandSender sender, CommandResponse response) {
        if (sender.hasPermission("toolset.core.admin.update.check")) {
            ToolSetUpdater updater = new ToolSetUpdater();
            try {
                List<CommandUpdateVersions> updatesRequired = updater.checkForUpdates();
                StringBuilder updates = new StringBuilder(ToolSetOptions.prefix + "Available updates:");
                for (CommandUpdateVersions currentVersion : updatesRequired) {
                    updates.append("\n")
                            .append(currentVersion.getCommandName())
                            .append("(v")
                            .append(currentVersion.getOldVersion())
                            .append(") can be updated to v")
                            .append(currentVersion.getNewVersion());
                }
                response.setPlayerMessage(updates.toString());
            } catch (IOException e) {
                response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while checking for updates. Please check the console for more information.");
                e.printStackTrace();
            } catch (ParseException e) {
                response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while parsing the updater data. Please check the console for more information.");
                e.printStackTrace();
            }
        } else {
            response.setPlayerMessage(ToolSetOptions.noPermissionsMessage);
        }

        return response;
    }

    private CommandResponse doUpdate(CommandSender sender, CommandResponse response) {
        ToolSetUpdater updater = new ToolSetUpdater();

        try {
            boolean areUpdatesRequired = updater.updatesRequired();

            if (!areUpdatesRequired) {
                response.setPlayerMessage(ToolSetOptions.prefix + "No updates required");
                return response;
            }
        } catch (IOException | ParseException e) {
            response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while checking for updates. Please check the console for more information.");
            e.printStackTrace();
        }

        if (sender.hasPermission("toolset.core.admin.update") || sender.hasPermission("toolset.core.admin.update.update")) {
            URLDownloader downloader = new URLDownloader();
            try {
                // Get updates required
                List<CommandUpdateVersions> updatesRequired = updater.checkForUpdates();
                StringBuilder updates = new StringBuilder(ToolSetOptions.prefix + "Available updates:");
                for (CommandUpdateVersions currentVersion : updatesRequired) {

                }
                response.setPlayerMessage(updates.toString());

                String updaterUrl = ToolSetOptions.getToolSetApiPath("files/ToolSetUpdater-1.0.0.jar");
                System.out.println("Downloading " + updaterUrl);
                byte[] updaterBytes = downloader.downloadUrlBytes(updaterUrl);

                // Create temporary file
                Path tempFilePath = new File(ToolSetOptions.getDataDirectory().getParentFile(), "toolset.updater.jar").toPath();
                Files.write(tempFilePath, updaterBytes);

                // Also create the information file
                File infoFile = new File(
                        ToolSetOptions.plugin.getDataFolder().getParent(),
                        "updates.toolset.dat"
                );
                try (PrintWriter output = new PrintWriter(infoFile)) {
                    for (CommandUpdateVersions update : updatesRequired) {
                        JavaPlugin plugin = updater.getPlugin(update);
                        if (plugin != null) {
                            String pluginName = updater.getPluginInternalName(update);
                            output.print(pluginName + "\n");
                        }
                    }
                }

                Plugin updaterPlugin = Bukkit.getPluginManager().loadPlugin(tempFilePath.toFile());
                Bukkit.getPluginManager().enablePlugin(updaterPlugin);
            } catch (IOException e) {
                response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while downloading the updater. Please check the console for more information.");
                e.printStackTrace();
            } catch (InvalidDescriptionException | InvalidPluginException e) {
                response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while enabling the updater. Please check the console for more information.");
                e.printStackTrace();
            } catch (ParseException e) {
                response.setPlayerMessage(ToolSetOptions.errorPrefix + "An unknown error occurred while getting the required updates. Please check the console for more information.");
                e.printStackTrace();
            }
        } else {
            response.setPlayerMessage(ToolSetOptions.noPermissionsMessage);
        }

        return response;
    }
}
