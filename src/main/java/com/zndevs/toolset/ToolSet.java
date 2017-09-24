package com.zndevs.toolset;

import com.zndevs.toolset.commands.ToolSetCommandCommand;
import com.zndevs.toolset.commands.ToolSetMainCommand;
import com.zndevs.toolset.commands.ToolSetReloadCommand;
import com.zndevs.toolset.commands.ToolSetUpdateCommand;
import com.zndevs.toolset.events.ToolSetJoinEvent;
import com.zndevs.toolset.tools.*;
import com.zndevs.toolset.updater.CommandUpdateVersions;
import com.zndevs.toolset.updater.ToolSetUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ToolSet Plugin
 */

public class ToolSet extends JavaPlugin {

    private static List<File> deleteOnExitFiles = new ArrayList<>();

    /**
     * Adds a file to the list of files to delete on server stop
     *
     * @param file The file to add
     */
    public static void addToDeleteOnExit(File file) {
        deleteOnExitFiles.add(file);
    }

    @Override
    public void onEnable() {
        // ToolSet has been enabled! Now for some checks
        getLogger().info("*** ToolSet by ZNDevelopers ***");

        // Because we're debugging, we'll set this to Localhost
        ToolSetOptions.toolSetApiPath = "http://192.168.1.29/web/toolset/";

        ToolSetOptions.setPlugin(this);

        // Add to list of plugins
        ToolSetCommandTools.registerIfEnabled(this, "toolset-reload",  new ToolSetReloadCommand());
        ToolSetCommandTools.registerIfEnabled(this, "toolset-update",  new ToolSetUpdateCommand());
        ToolSetCommandTools.registerIfEnabled(this, "toolset-command", new ToolSetCommandCommand());
        ToolSetCommandTools.registerIfEnabled(this, "toolset",         new ToolSetMainCommand());
        ToolSetCommandTools.addPlugin("ToolSet Core", this);

        // Add event listeners
        getServer().getPluginManager().registerEvents(new ToolSetJoinEvent(), this);

        try {
            // Create the 'how to use config files' file
            boolean fileCreated = ConfigurationTools.createToolSetDataFile(this, "/config-file-help", "How To - Using ToolSet Configuration Files.txt");
            if (fileCreated) {
                // this might be the first time using ToolSet!
                // We'll say "hi"
                getServer().getConsoleSender().sendMessage(CommandResponse.parseColours("" +
                        "&f\n" +
                        "   &b/------------------------------------------------------------------------------------------------------\\\n" +
                        "   &b|&f Welcome to &6[&c&lToolSet&6]&f!                                                                                &b|&f\n" +
                        "   &b|&f We're &f&overy happy&f to have you here!                                                                   &b|&f\n" +
                        "   &b|&f We've created a file in the &7&l/plugins/ToolSet/&f&l &fdirectory                                              &b|&f\n" +
                        "   &b|&f (&7&lHow To - Using ToolSet Configuration Files.txt&f), that'll show                                       &b|&f\n" +
                        "   &b|&f you how you can use all the ToolSet configuration files you'll find there.                           &b|&f\n" +
                        "   &b|&f Remember, if you find any issues, tell us at &7&lgithub.com/zndevelopers/toolset-issues/issues&f!          &b|&f\n" +
                        "   &b|&f You can also check out our page on Spigot at &7&lhttps://www.spigotmc.org/resources/toolset.38810/&f.      &b|&f\n" +
                        "   &b\\------------------------------------------------------------------------------------------------------/"));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        // Enable all commands. This will almost always be none unless something magical happens (because commands only
        // get loaded _after_ this plugin)
        //getLogger().info("Enabling commands...");
        //ConfigurationTools.reloadCommands();

        // Check for updates
        getLogger().info("Checking for updates...");
        ToolSetUpdater updater = new ToolSetUpdater();
        try {
            List<CommandUpdateVersions> updatesRequired = updater.checkForUpdates();
            StringBuilder updates = new StringBuilder("&cAvailable updates&f:");
            for (CommandUpdateVersions currentVersion : updatesRequired) {
                updates.append("\n                   &6*&f ")
                        .append(currentVersion.getCommandName())
                        .append(" (&6")
                        .append(currentVersion.getOldVersion())
                        .append("&f) can be updated to &6")
                        .append(currentVersion.getNewVersion());
            }
            if (updatesRequired.size() == 0) {
                updates.append(" &aGood job - everything's up to date!");
            } else {
                updates.append("\n                  &cTo download these updates, run the command `&7toolset-update&c`");
            }
            getServer().getConsoleSender().sendMessage(CommandResponse.parseColours(updates.toString()));
        } catch (IOException e) {
            getLogger().warning("An unknown error occurred while checking for updates:");
            e.printStackTrace();
        } catch (ParseException e) {
            getLogger().warning("An unknown error occurred while parsing the updater data:");
            e.printStackTrace();
        }

        getLogger().info("ToolSet has been enabled!");

    }

    @Override
    public void onDisable() {
        for (File deleteOnExitFile : deleteOnExitFiles) {
            try {
                if (deleteOnExitFile.exists() && !deleteOnExitFile.isDirectory()) {
                    if (deleteOnExitFile.delete()) {
                        getLogger().info(" * Deleted " + deleteOnExitFile.getName());
                    } else {
                        throw new IOException("An unknown error occured");
                    }
                } else {
                    throw new IOException("File does not exist");
                }
            } catch (Exception err) {
                getLogger().warning("Could not delete " + deleteOnExitFile.getName() + ": " + err.getMessage());
                err.printStackTrace();
            }
        }

        getLogger().info("Disabling all commands...");
        ToolSetCommandTools.unloadAllCommands();

        getLogger().info("ToolSet has been disabled.");
    }

}