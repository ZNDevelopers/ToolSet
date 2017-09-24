package com.zndevs.toolset.events;

import com.zndevs.toolset.tools.CommandResponse;
import com.zndevs.toolset.tools.ToolSetOptions;
import com.zndevs.toolset.updater.CommandUpdateVersions;
import com.zndevs.toolset.updater.ToolSetUpdater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Class com.zndevs.toolset.events.ToolSetJoinEvent created by zoweb
 * for ToolSet
 */

public class ToolSetJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.hasPermission("toolset.core.admin.update.check")) {
            player.sendMessage(CommandResponse.parseColours(ToolSetOptions.prefix + " Checking for updates..."));
            ToolSetUpdater updater = new ToolSetUpdater();
            try {
                List<CommandUpdateVersions> updatesRequired = updater.checkForUpdates();
                StringBuilder updates = new StringBuilder(ToolSetOptions.prefix + " &cAvailable updates&f:");
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
                player.sendMessage(CommandResponse.parseColours(updates.toString()));
            } catch (IOException err) {
                player.sendMessage(ToolSetOptions.errorPrefix + " An unknown error occurred while checking for updates:");
                err.printStackTrace();
            } catch (ParseException err) {
                player.sendMessage(ToolSetOptions.errorPrefix + " An unknown error occurred while parsing the updater data:");
                err.printStackTrace();
            }
        }
    }
}
