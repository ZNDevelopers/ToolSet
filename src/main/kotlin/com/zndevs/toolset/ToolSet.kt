package com.zndevs.toolset

import com.zndevs.toolset.commands.ToolSetCommandCommand
import com.zndevs.toolset.commands.ToolSetMainCommand
import com.zndevs.toolset.events.ToolSetJoinEvent
import com.zndevs.toolset.tools.CommandResponse
import com.zndevs.toolset.tools.ConfigurationTools
import com.zndevs.toolset.tools.ToolSetCommandTools
import com.zndevs.toolset.tools.ToolSetOptions
import com.zndevs.toolset.updater.ToolSetUpdater
import mu.KotlinLogging
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.parser.ParseException
import java.io.File
import java.io.IOException
import java.util.*

/**
 * ToolSet Plugin
 */

private val log = KotlinLogging.logger {}
class ToolSet : JavaPlugin() {

    override fun onEnable() {
        // ToolSet has been enabled! Now for some checks
        //logger.info("*** ToolSet by ZNDevelopers ***")
        log.info { "*** ToolSet by ZNDevelopers ***" }

        // Set API path to TOOLSET_API environment variable, or default http://zndevs.zoweb.me/toolset/

        ToolSetOptions.toolSetApiPath = System.getenv("TOOLSET_API")
        if (ToolSetOptions.toolSetApiPath == null) ToolSetOptions.toolSetApiPath = "http://zndevs.zoweb.me/toolset/"
        log.debug { "Toolset API path set to ${ToolSetOptions.toolSetApiPath}" }

        ToolSetOptions.plugin = this

        // Add to list of plugins
        ToolSetCommandTools.registerIfEnabled(this, "toolset", ToolSetMainCommand())
        ToolSetCommandTools.addPlugin("ToolSet Core", this)

        // Add event listeners
        server.pluginManager.registerEvents(ToolSetJoinEvent(), this)

        try {
            // Create the 'how to use config files' file
            val fileCreated = ConfigurationTools.createToolSetDataFile(this, "/config-file-help", "How To - Using ToolSet Configuration Files.txt")
            if (fileCreated) {
                // this might be the first time using ToolSet!
                // We'll say "hi"
                server.consoleSender.sendMessage(CommandResponse.parseColours("" +
                        "&f\n" +
                        "   &b/------------------------------------------------------------------------------------------------------\\\n" +
                        "   &b|&f Welcome to &6[&c&lToolSet&6]&f!                                                                                &b|&f\n" +
                        "   &b|&f We're &f&overy happy&f to have you here!                                                                   &b|&f\n" +
                        "   &b|&f We've created a file in the &7&l/plugins/ToolSet/&f&l &fdirectory                                              &b|&f\n" +
                        "   &b|&f (&7&lHow To - Using ToolSet Configuration Files.txt&f), that'll show                                       &b|&f\n" +
                        "   &b|&f you how you can use all the ToolSet configuration files you'll find there.                           &b|&f\n" +
                        "   &b|&f Remember, if you find any issues, tell us at &7&lgithub.com/zndevelopers/toolset-issues/issues&f!          &b|&f\n" +
                        "   &b|&f You can also check out our page on Spigot at &7&lhttps://www.spigotmc.org/resources/toolset.38810/&f.      &b|&f\n" +
                        "   &b\\------------------------------------------------------------------------------------------------------/"))
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }

        // Enable all commands. This will almost always be none unless something magical happens (because commands only
        // get loaded _after_ this plugin)
        //getLogger().info("Enabling commands...");
        //ConfigurationTools.reloadCommands();

        // Check for updates
        log.info("Checking for updates...")
        val updater = ToolSetUpdater()
        try {
            val updatesRequired = updater.checkForUpdates()
            val updates = StringBuilder("&cAvailable updates&f:")
            for (currentVersion in updatesRequired) {
                updates.append("\n                   &6*&f ")
                        .append(currentVersion.commandName)
                        .append(" (&6")
                        .append(currentVersion.oldVersion)
                        .append("&f) can be updated to &6")
                        .append(currentVersion.newVersion)
            }
            if (updatesRequired.isEmpty()) {
                updates.append(" &aGood job - everything's up to date!")
            } else {
                updates.append("\n                  &cTo download these updates, run the command `&7toolset-update&c`")
            }
            server.consoleSender.sendMessage(CommandResponse.parseColours(updates.toString()))
        } catch (e: IOException) {
            log.warn("An unknown error occurred while checking for updates:")
            e.printStackTrace()
        } catch (e: ParseException) {
            log.warn("An unknown error occurred while parsing the updater data:")
            e.printStackTrace()
        }

        log.info("ToolSet has been enabled!")

    }

    override fun onDisable() {
        for (deleteOnExitFile in deleteOnExitFiles) {
            try {
                if (deleteOnExitFile.exists() && !deleteOnExitFile.isDirectory) {
                    if (deleteOnExitFile.delete()) {
                        log.info(" * Deleted " + deleteOnExitFile.name)
                    } else {
                        throw IOException("An unknown error occured")
                    }
                } else {
                    throw IOException("File does not exist")
                }
            } catch (err: Exception) {
                log.warn(err) { "Could not delete ${deleteOnExitFile.name}" }
                err.printStackTrace()
            }

        }

        log.info("Disabling all commands...")
        ToolSetCommandTools.unloadAllCommands()

        log.info("ToolSet has been disabled.")
    }

    companion object {

        private val deleteOnExitFiles = ArrayList<File>()

        /**
         * Adds a file to the list of files to delete on server stop
         *
         * @param file The file to add
         */
        fun addToDeleteOnExit(file: File) {
            deleteOnExitFiles.add(file)
        }
    }

}