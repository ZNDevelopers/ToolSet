package com.zndevs.toolset.updater

import com.zndevs.toolset.tools.*
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException

import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.util.ArrayList

/**
 * ToolSet Update class - checks and downloads updates
 */

class ToolSetUpdater {

    private var serverJsonData: JSONArray? = null

    fun checkForUpdates(): List<CommandUpdateVersions> {
        val pluginMap = ArrayList<CommandUpdateVersions>()

        // Get 'official' plugin data
        val pluginDatas = URLDownloader().downloadUrl(ToolSetOptions.getToolSetApiPath("data/plugins.json"))

        val jsonData = JSONParser().parse(pluginDatas) as JSONArray
        this.serverJsonData = jsonData

        for (i in jsonData.indices) {
            val current = jsonData[i] as JSONObject

            val internalName = current["internalName"].toString()
            val pluginName = current["name"].toString()
            val currentInstalledPlugin = ToolSetCommandTools.getPlugin(pluginName)
            if (currentInstalledPlugin != null) {
                val version = currentInstalledPlugin.description.version
                val newVersion = current["version"].toString()

                if (!version.equals(newVersion, ignoreCase = true)) {
                    pluginMap.add(CommandUpdateVersions(internalName, version, newVersion, i))
                }
            }
        }
        return pluginMap
    }

    fun updatesRequired(): Boolean {
        val updates = checkForUpdates()

        return !updates.isEmpty()
    }

    @Deprecated("")
    fun downloadUpdate(current: CommandUpdateVersions): ByteArray? {
        val currentJson = serverJsonData!![current.otherData as Int] as JSONObject
        val dataUrl = currentJson["file"] as String
        val urlDownloader = URLDownloader()
        val uri: URI
        try {
            uri = URI(
                    "http",
                    "zndevs.zoweb.me",
                    "/toolset/data/get-file.php",
                    "is_update=true" +
                            "&url=" + currentJson["file"] +
                            "&author=" + currentJson["author"] +
                            "&name=" + currentJson["name"] +
                            "&version=" + currentJson["version"], null
            )
            return urlDownloader.downloadUrlBytes(uri.toASCIIString())

        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        return null
    }

    fun getPluginName(current: CommandUpdateVersions): String {
        val currentJson = serverJsonData!![current.otherData as Int] as JSONObject
        return currentJson["name"] as String
    }

    fun getPluginInternalName(current: CommandUpdateVersions): String {
        val currentJson = serverJsonData!![current.otherData as Int] as JSONObject
        return currentJson["internalName"] as String
    }

    fun getPlugin(current: CommandUpdateVersions): JavaPlugin? {
        return ToolSetCommandTools.getPlugin(getPluginName(current))
    }

}
