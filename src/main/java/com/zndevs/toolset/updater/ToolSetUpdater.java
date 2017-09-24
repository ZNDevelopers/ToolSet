package com.zndevs.toolset.updater;

import com.zndevs.toolset.tools.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * ToolSet Update class - checks and downloads updates
 */

public class ToolSetUpdater {

    private JSONArray serverJsonData;

    public List<CommandUpdateVersions> checkForUpdates() throws IOException, ParseException {
        List<CommandUpdateVersions> pluginMap = new ArrayList<>();

        // Get 'official' plugin data
        URLDownloader urlDownloader = new URLDownloader();
        urlDownloader.downloadUrl(ToolSetOptions.getToolSetApiPath("data/plugins.json"));

        String jsonDataString = urlDownloader.getResponse();
        JSONArray jsonData = (JSONArray) new JSONParser().parse(jsonDataString);
        this.serverJsonData = jsonData;

        for (int i = 0; i < jsonData.size(); i++) {
            JSONObject current = (JSONObject) jsonData.get(i);

            String internalName = current.get("internalName").toString();
            String pluginName = current.get("name").toString();
            JavaPlugin currentInstalledPlugin = ToolSetCommandTools.getPlugin(pluginName);
            if (currentInstalledPlugin != null) {
                String version = currentInstalledPlugin.getDescription().getVersion();
                String newVersion = current.get("version").toString();

                if (!version.equalsIgnoreCase(newVersion)) {
                    pluginMap.add(new CommandUpdateVersions(internalName, version, newVersion, i));
                }
            }
        }
        return pluginMap;
    }

    public boolean updatesRequired() throws IOException, ParseException {
        List<CommandUpdateVersions> updates = checkForUpdates();

        return !updates.isEmpty();
    }

    @Deprecated
    public byte[] downloadUpdate(CommandUpdateVersions current) throws IOException {
        JSONObject currentJson = (JSONObject) serverJsonData.get((int) current.getOtherData());
        String dataUrl = (String) currentJson.get("file");
        URLDownloader urlDownloader = new URLDownloader();
        URI uri;
        try {
            uri = new URI(
                    "http",
                    "zndevs.zoweb.me",
                    "/toolset/data/get-file.php",
                    "is_update=true" +
                            "&url=" + currentJson.get("file") +
                            "&author=" + currentJson.get("author") +
                            "&name=" + currentJson.get("name") +
                            "&version=" + currentJson.get("version"),
                    null
            );
            return urlDownloader.downloadUrlBytes(uri.toASCIIString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPluginName(CommandUpdateVersions current) {
        JSONObject currentJson = (JSONObject) serverJsonData.get((int) current.getOtherData());
        return (String) currentJson.get("name");
    }

    public String getPluginInternalName(CommandUpdateVersions current) {
        JSONObject currentJson = (JSONObject) serverJsonData.get((int) current.getOtherData());
        return (String) currentJson.get("internalName");
    }

    public JavaPlugin getPlugin(CommandUpdateVersions current) {
        return ToolSetCommandTools.getPlugin(getPluginName(current));
    }

}
