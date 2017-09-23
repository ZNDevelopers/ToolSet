package com.zndevs.toolset.tools;

import com.zndevs.toolset.ToolSetConfiguration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

/**
 * Tools to help with reloading configuration and commands
 */

public class ConfigurationTools {
    /**
     * Set a configuration value if it has not yet been set
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    public static ConfigurationSection setIfNotSet(ConfigurationSection section, String path, Object value) {
        if (section.get(path) == null) {
            section.set(path, value);
        }
        return section;
    }

    /**
     * Set a configuration value if it has not yet been set [YamlConfiguration version]
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    public static YamlConfiguration setIfNotSet(YamlConfiguration section, String path, Object value) {
        if (section.get(path) == null) {
            section.set(path, value);
        }
        return section;
    }

    /**
     * Set a configuration value if it has not yet been set [ToolSetConfiguration version]
     *
     * @param section The configuration section containing the value
     * @param path    The path to the value
     * @param value   The value to set the path to
     * @return The configuration section containing the value
     */
    public static ToolSetConfiguration setIfNotSet(ToolSetConfiguration section, String path, Object value) {
        if (section.get(path) == null) {
            section.set(path, value);
        }
        return section;
    }

    /**
     * Creates a data file under the plugins/ToolSet folder
     * @param plugin The plugin that contains the file
     * @param resourcePath The resource path in the Java file
     * @param dataFileName The name of the file that will be saved
     * @return True if file created, false otherwise
     */
    public static boolean createToolSetDataFile(Plugin plugin, String resourcePath, String dataFileName) {
        File dataFilePath = ToolSetOptions.getDataFile(dataFileName);
        InputStream resourceStream = plugin.getClass().getResourceAsStream(resourcePath);
        URL resourceUrl = plugin.getClass().getResource(resourcePath);
        File resourceFile = new File(resourceUrl == null ? "" : resourceUrl.getFile());

        if (resourceUrl == null) {
            plugin.getLogger().severe("Could not save data file as it does not exist");
            return false;
        }

        // only override file if it doesn't exist
        if (!dataFilePath.isFile()) {
            // create file

            // write InputStream to data file
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                dataFilePath.getParentFile().mkdirs();


                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFilePath));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line + "\r\n");
                }
                writer.close();

                return true;
            } catch (IOException err) {
                plugin.getLogger().severe("Could not save data file: " + err.getMessage());
                err.printStackTrace();
            }
        }

        return false;
    }
}
