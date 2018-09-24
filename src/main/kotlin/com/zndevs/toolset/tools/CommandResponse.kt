package com.zndevs.toolset.tools

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player

/**
 * Tools to help with responses from commands
 */

class CommandResponse {
    private var sendToPlayer: String? = null
    private var enablePlayer: Boolean = false
    private var sendToEveryone: String? = null
    private var enableBroadcast: Boolean = false
    private var sendToConsole: String? = null
    private var enableConsole: Boolean = false

    /**
     * The message sent to the executing player
     */
    var playerMessage: String?
        get() = parseColours(sendToPlayer)
        set(message) {
            enablePlayer = message != null
            sendToPlayer = message
        }

    /**
     * The message to be broadcasted
     */
    var broadcastMessage: String?
        get() = parseColours(sendToEveryone)
        set(message) {
            enableBroadcast = message != null
            sendToEveryone = message
        }

    /**
     * The message to be sent to the console
     */
    var consoleMessage: String?
        get() = parseColours(sendToConsole)
        set(message) {
            enableConsole = message != null
            sendToConsole = message
        }

    /**
     * Check if the message to send exists
     *
     * @return Enabled
     */
    fun usePlayerMessage(): Boolean {
        return enablePlayer && playerMessage != null
    }

    /**
     * Check if the message to be broadcasted exists
     *
     * @return Enabled
     */
    fun useBroadcastMessage(): Boolean {
        return enableBroadcast && broadcastMessage != null
    }

    /**
     * Check if the message to be sent to the console exists
     *
     * @return Enabled
     */
    fun useConsoleMessage(): Boolean {
        return enableConsole && consoleMessage != null
    }

    /**
     * Sends all messages if they are required
     *
     * @param player The player that ran the command
     * @param server The server the player is on
     */
    @JvmOverloads
    fun send(player: CommandSender, server: Server = Bukkit.getServer()) {
        if (usePlayerMessage()) {
            player.sendMessage(playerMessage)
        }
        if (useConsoleMessage()) {
            server.consoleSender.sendMessage(consoleMessage)
        }
        if (useBroadcastMessage()) {
            if (player is Player) {
                broadcastMessage(server, broadcastMessage, player)
            } else {
                broadcastMessage(server, broadcastMessage)
            }
        }
    }

    companion object {

        /**
         * Parses colours in messages using the '&amp;' symbol
         *
         * @param message Message to parse colours in, using '&amp;' symbol
         * @return Message with colour
         */
        fun parseColours(message: String?): String? {
            return if (message == null) {
                null
            } else ChatColor.translateAlternateColorCodes('&', message)
        }

        /**
         * Broadcasts a message to every online player
         *
         * @param server  Server to broadcast messages on
         * @param message Message to broadcast
         */
        fun broadcastMessage(server: Server, message: String?) {
            for (player in server.onlinePlayers) {
                player.sendMessage(message)
            }
        }

        /**
         * Broadcasts a message to every online player except for the one parsed into the third argument
         *
         * @param server  Server to broadcast messages on
         * @param message Message to broadcast
         * @param player  Player to omit from broadcasting
         */
        fun broadcastMessage(server: Server, message: String?, player: Player) {
            for (pl in server.onlinePlayers) {
                if (!(pl.uniqueId === player.uniqueId)) {
                    pl.sendMessage(message)
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
        fun getMessage(messageConfig: ConfigurationSection, messageConfigPath: String, defaultMessage: String): String {
            val message = messageConfig.getString(messageConfigPath)
            return message ?: defaultMessage
        }
    }
}
/**
 * Sends all the messages if they're required.
 * Automatic server selection
 *
 * @param player The player that ran the command
 */
