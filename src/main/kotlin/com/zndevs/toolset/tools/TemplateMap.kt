package com.zndevs.toolset.tools

import org.bukkit.entity.Player

import java.util.HashMap

/**
 * Map class for use with StringTemplate
 */

class TemplateMap {

    private val itemMap = HashMap<String, String>()

    /**
     * Adds an item to the TemplateMap
     *
     * @param itemName  The name of the item. Can use any character
     * @param itemValue The value of the item. Can use any character or any type
     */
    fun addItem(itemName: String, itemValue: Any) {
        itemMap[itemName] = itemValue.toString()
    }

    /**
     * Removes an item from the TemplateMap
     *
     * @param itemName The name of the item. Can use any character
     */
    fun removeItem(itemName: String) {
        itemMap.remove(itemName)
    }

    /**
     * Gets an item from the TemplateMap as a String
     *
     * @param itemName The name of the item to retrieve
     * @return TemplateMap item
     */
    fun getItem(itemName: String): String? {
        return itemMap[itemName]
    }

    companion object {

        /**
         * Creates a TemplateMap from automatically generated options
         *
         * @param player Player for player item
         * @return Template
         */
        fun getDefaultOptions(player: Player): TemplateMap {
            val map = TemplateMap()
            map.addItem("player", player.displayName)
            map.addItem("playername", player.displayName)
            return map
        }
    }

}
