package com.zndevs.toolset.commands

import com.zndevs.toolset.ToolSetCommand
import com.zndevs.toolset.ToolSetConfiguration
import com.zndevs.toolset.commands.tools.SubCommand
import com.zndevs.toolset.tools.CommandResponse
import com.zndevs.toolset.tools.ToolSetOptions
import com.zndevs.toolset.tools.URLDownloader
import com.zndevs.toolset.updater.ToolSetUpdater
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.InvalidDescriptionException
import org.bukkit.plugin.InvalidPluginException
import org.json.simple.parser.ParseException
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files

class ToolSetUpdateCommand : ToolSetCommand() {
    private val subCommands: Array<SubCommand> = arrayOf(
            SubCommand("check", arrayOf("chk", "c"), "Check for updates", fun(s, _, _, _, _, r): Boolean {
                checkForUpdates(s, r)
                return true
            }),
            SubCommand("update", arrayOf("upd", "u"), "Update if required", fun(s, _, _, _, _, r): Boolean {
                doUpdate(s, r)
                return true
            })
    )

    private fun checkForUpdates(sender: CommandSender, response: CommandResponse) {
        if (!sender.hasPermission("toolset.core.admin.update.check")) {
            response.playerMessage = ToolSetOptions.noPermissionsMessage
            return
        }

        val updater = ToolSetUpdater()
        try {
            val updatesRequired = updater.checkForUpdates()
            val updates = StringBuilder(ToolSetOptions.prefix).append("Available updates:")

            for (currentVersion in updatesRequired) {
                updates.append("\n")
                        .append(currentVersion.commandName)
                        .append(" (").append(currentVersion.oldVersion).append(")")
                        .append(" can be updated to ").append(currentVersion.newVersion)
            }

            if (updatesRequired.isEmpty()) updates.append(" &aToolSet is all up to date.")
            response.playerMessage = updates.toString()
        } catch (e: IOException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while checking for updates. Please refer to the console for more information."
            e.printStackTrace()
        } catch (e: ParseException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while parsing the updater data. Please refer to the console for more information."
        }
    }

    private fun doUpdate(sender: CommandSender, response: CommandResponse) {
        if (!sender.hasPermission("toolset.core.admin.update") && !sender.hasPermission("toolset.core.admin.update.update")) {
            response.playerMessage = ToolSetOptions.noPermissionsMessage
            return
        }

        val downloader = URLDownloader()
        val updater = ToolSetUpdater()

        try {
            // check for updates
            val updatesRequired = updater.updatesRequired()

            if (!updatesRequired) {
                response.playerMessage = ToolSetOptions.prefix + "No updates are required"
                return
            }
        } catch (e: IOException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while checking for updates. Please refer to the console for more information."
            e.printStackTrace()
        } catch (e: ParseException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while parsing the updater data. Please refer to the console for more information."
        }

        try {
            // get a list of required updates
            val requiredUpdates = updater.checkForUpdates()

            val updaterUrl = ToolSetOptions.getToolSetApiPath("files/ToolSetUpdater-1.0.0.jar")
            val updaterBytes = downloader.downloadUrlBytes(updaterUrl)

            // create temporary updater file
            val tempUpdaterPath = File(ToolSetOptions.dataDirectory.parentFile, "toolset.updater.jar")
            Files.write(tempUpdaterPath.toPath(), updaterBytes)

            // also create information file to say what to update
            val infoFile = File(ToolSetOptions.plugin?.dataFolder, "updates.toolset.dat")

            PrintWriter(infoFile).use {
                for (update in requiredUpdates) {
                    val plugin = updater.getPlugin(update)
                    if (plugin != null) {
                        val pluginName = updater.getPluginInternalName(update)
                        it.print(pluginName + "\n")
                    }
                }
            }

            val updaterPlugin = Bukkit.getPluginManager().loadPlugin(tempUpdaterPath)
            Bukkit.getPluginManager().enablePlugin(updaterPlugin)
        } catch (e: IOException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while downloading the updater. Please refer to the console for more information."
            e.printStackTrace()
        } catch (e: InvalidDescriptionException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while enabling the updater. Please refer to the console for more information."
            e.printStackTrace()
        } catch (e: InvalidPluginException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while enabling the updater. Please refer to the console for more information."
            e.printStackTrace()
        } catch (e: ParseException) {
            response.playerMessage = ToolSetOptions.errorPrefix + "An error occurred while getting the required updates. Please refer to the console for more information."
        }
    }

    override fun getSubCommands() = subCommands

    override fun getCommandName() = "ToolSet Updater"

    override fun setConfigurationDefaults(defaults: ToolSetConfiguration) = defaults
}