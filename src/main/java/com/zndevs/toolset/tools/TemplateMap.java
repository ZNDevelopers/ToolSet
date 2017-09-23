package com.zndevs.toolset.tools;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Map class for use with StringTemplate
 */

public class TemplateMap {

    private final Map<String, String> itemMap = new HashMap<>();

    /**
     * Creates a TemplateMap from automatically generated options
     *
     * @param player Player for player item
     * @return Template
     */
    public static TemplateMap getDefaultOptions(Player player) {
        TemplateMap map = new TemplateMap();
        map.addItem("player", player.getDisplayName());
        map.addItem("playername", player.getDisplayName());
        return map;
    }

    /**
     * Adds an item to the TemplateMap
     *
     * @param itemName  The name of the item. Can use any character
     * @param itemValue The value of the item. Can use any character or any type
     */
    public void addItem(String itemName, Object itemValue) {
        itemMap.put(itemName, itemValue.toString());
    }

    /**
     * Removes an item from the TemplateMap
     *
     * @param itemName The name of the item. Can use any character
     */
    public void removeItem(String itemName) {
        itemMap.remove(itemName);
    }

    /**
     * Gets an item from the TemplateMap as a String
     *
     * @param itemName The name of the item to retrieve
     * @return TemplateMap item
     */
    public String getItem(String itemName) {
        return itemMap.get(itemName);
    }

}
