package com.zndevs.toolset.tools;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Tools to help with the creation of files with default text
 */

public class FileCreation {

    /**
     * Creates a data file from a plugin resource
     *
     * @param plugin       The plugin containing the resource
     * @param resourcePath The resource filename
     * @param dataName     The data file name
     */
    public static void resourceToDataFile(Plugin plugin, String resourcePath, String dataName) {
        try {
            File dataFile = ToolSetOptions.getDataFile(dataName);
            dataFile.getParentFile().mkdirs();

            if (dataFile.isFile()) {
                plugin.getLogger().warning(String.format("Did not create data file \"%s\" as it already exists", dataName));
            } else {
                plugin.getLogger().info(String.format("Creating data file \"%s\" from resource \"%s\"", dataName, resourcePath));
            }

            // Get resource as a stream
            InputStream resourceStream = plugin.getClass().getClassLoader().getResourceAsStream(resourcePath);

            // Write InputStream to data file
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line + "\r\n");
                }
                writer.close();
            } catch (IOException err) {
                if (plugin.getLogger() != null) {
                    plugin.getLogger().severe("Could not save file (IO Exception)! " + (err.getMessage() == null ? "" : err.getMessage()));
                }
                err.printStackTrace();
            }
        } catch (NullPointerException err) {
            if (plugin != null && plugin.getLogger() != null) {
                plugin.getLogger().severe("Could not save file (Null Pointer Exception)! " + (err.getMessage() == null ? "" : err.getMessage()));
            }
            err.printStackTrace();
        }
    }

    /**
     * Creates a file
     *
     * @param file     The file to create
     * @param contents The contents of the file to create
     */
    public static void create(File file, String contents) throws IOException {
        List<String> lines = Arrays.asList(contents.split("\n"));
        Path filePath = file.toPath();
        Files.write(filePath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
    }

    /**
     * Creates a file
     *
     * @param file     The file to create
     * @param contents The contents of the file to create
     */
    public static void create(File file, byte[] contents) throws IOException {
        Path filePath = file.toPath();
        Files.write(filePath, contents, StandardOpenOption.CREATE_NEW);
    }

}
