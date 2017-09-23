package com.zndevs.toolset.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Tools to help with responses from commands
 */

public class CommandResponse {

    private String sendToPlayer;
    private boolean enablePlayer;
    private String sendToEveryone;
    private boolean enableBroadcast;
    private String sendToConsole;
    private boolean enableConsole;

    /**
     * Parses colours in messages using the '&amp;' symbol
     *
     * @param message Message to parse colours in, using '&amp;' symbol
     * @return Message with colour
     */
    public static String parseColours(String message) {
        if (message == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Broadcasts a message to every online player
     *
     * @param server  Server to broadcast messages on
     * @param message Message to broadcast
     */
    public static void broadcastMessage(Server server, String message) {
        for (Player player : server.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    /**
     * Broadcasts a message to every online player except for the one parsed into the third argument
     *
     * @param server  Server to broadcast messages on
     * @param message Message to broadcast
     * @param player  Player to omit from broadcasting
     */
    public static void broadcastMessage(Server server, String message, Player player) {
        for (Player pl : server.getOnlinePlayers()) {
            if (!(pl.getUniqueId() == player.getUniqueId())) {
                pl.sendMessage(message);
            }
        }
    }

    /**
     * Gets a message from the configuration
     *
     * @param messageConfig     Configuration section to get message from
     * @param messageConfigPath Configuration path that message exists in
     * @param defaultMessage    Message to display if configuration path doesn't exist
     * @return Message
     */
    public static String getMessage(ConfigurationSection messageConfig, String messageConfigPath, String defaultMessage) {
        String message = messageConfig.getString(messageConfigPath);
        if (message == null) {
            return defaultMessage;
        } else {
            return message;
        }
    }

    /**
     * Get the message to be sent to the player
     *
     * @return Message
     */
    public String getPlayerMessage() {
        return parseColours(sendToPlayer);
    }

    /**
     * Set the message sent to the executing player
     *
     * @param message The message to be sent
     * @return Message
     */
    public void setPlayerMessage(String message) {
        enablePlayer = message != null;
        sendToPlayer = message;
    }

    /**
     * Check if the message to send exists
     *
     * @return Enabled
     */
    public boolean usePlayerMessage() {
        return enablePlayer;
    }

    /**
     * Get the message to be broadcasted
     *
     * @return Message
     */
    public String getBroadcastMessage() {
        return parseColours(sendToEveryone);
    }

    /**
     * Set the message to be broadcasted
     *
     * @param message The message to be broadcasted
     * @return Message
     */
    public void setBroadcastMessage(String message) {
        enableBroadcast = message != null;
        sendToEveryone = message;
    }

    /**
     * Check if the message to be broadcasted exists
     *
     * @return Enabled
     */
    public boolean useBroadcastMessage() {
        return enableBroadcast;
    }

    /**
     * Get the message to be sent to the console
     *
     * @return Message
     */
    public String getConsoleMessage() {
        return parseColours(sendToConsole);
    }

    /**
     * Set the message to be sent to the console
     *
     * @param message The message to be sent to the console
     * @return Message
     */
    public void setConsoleMessage(String message) {
        enableConsole = message != null;
        sendToConsole = message;
    }

    /**
     * Check if the message to be sent to the console exists
     *
     * @return Enabled
     */
    public boolean useConsoleMessage() {
        return enableConsole;
    }

    /**
     * Sends all messages if they are required
     *
     * @param player The player that ran the command
     * @param server The server the player is on
     */
    public void send(CommandSender player, Server server) {
        if (usePlayerMessage()) {
            player.sendMessage(getPlayerMessage());
        }
        if (useConsoleMessage()) {
            server.getConsoleSender().sendMessage(getConsoleMessage());
        }
        if (useBroadcastMessage()) {
            if (player instanceof Player) {
                broadcastMessage(server, getBroadcastMessage(), (Player) player);
            } else {
                broadcastMessage(server, getBroadcastMessage());
            }
        }
    }

    /**
     * Sends all the messages if they're required.
     * Automatic server selection
     *
     * @param player The player that ran the command
     */
    public void send(CommandSender player) {
        send(player, Bukkit.getServer());
    }
}
