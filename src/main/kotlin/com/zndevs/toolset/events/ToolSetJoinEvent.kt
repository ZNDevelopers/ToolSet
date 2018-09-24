package com.zndevs.toolset.events

import com.zndevs.toolset.tools.CommandResponse
import com.zndevs.toolset.tools.ToolSetOptions
import com.zndevs.toolset.updater.ToolSetUpdater
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.json.simple.parser.ParseException
import java.io.IOException

/**
 * Class com.zndevs.toolset.events.ToolSetJoinEvent created by zoweb
 * for ToolSet
 */

class ToolSetJoinEvent : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player

        if (player.hasPermission("toolset.core.admin.update.check")) {
            player.sendMessage(CommandResponse.parseColours(ToolSetOptions.prefix + " Checking for updates..."))
            val updater = ToolSetUpdater()
            try {
                val updatesRequired = updater.checkForUpdates()
                val updates = StringBuilder(ToolSetOptions.prefix + " &cAvailable updates&f:")
                for (currentVersion in updatesRequired) {
                    updates.append("\n                   &6*&f ")
                            .append(currentVersion.commandName)
                            .append(" (&6")
                            .append(currentVersion.oldVersion)
                            .append("&f) can be updated to &6")
                            .append(currentVersion.newVersion)
                }
                if (updatesRequired.size == 0) {
                    updates.append(" &aGood job - everything's up to date!")
                } else {
                    updates.append("\n                  &cTo download these updates, run the command `&7toolset-update&c`")
                }
                player.sendMessage(CommandResponse.parseColours(updates.toString()))
            } catch (err: IOException) {
                player.sendMessage(ToolSetOptions.errorPrefix + " An unknown error occurred while checking for updates:")
                err.printStackTrace()
            } catch (err: ParseException) {
                player.sendMessage(ToolSetOptions.errorPrefix + " An unknown error occurred while parsing the updater data:")
                err.printStackTrace()
            }

        }
    }
}
