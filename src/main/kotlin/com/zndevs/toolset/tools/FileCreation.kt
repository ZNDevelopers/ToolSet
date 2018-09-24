package com.zndevs.toolset.tools

import org.bukkit.plugin.Plugin

import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.Arrays

/**
 * Tools to help with the creation of files with default text
 */

object FileCreation {

    /**
     * Creates a data file from a plugin resource
     *
     * @param plugin       The plugin containing the resource
     * @param resourcePath The resource filename
     * @param dataName     The data file name
     */
    fun resourceToDataFile(plugin: Plugin?, resourcePath: String, dataName: String) {
        try {
            val dataFile = ToolSetOptions.getDataFile(dataName)
            dataFile.getParentFile().mkdirs()

            if (dataFile.isFile()) {
                plugin!!.logger.warning(String.format("Did not create data file \"%s\" as it already exists", dataName))
            } else {
                plugin!!.logger.info(String.format("Creating data file \"%s\" from resource \"%s\"", dataName, resourcePath))
            }

            // Get resource as a stream
            val resourceStream = plugin.javaClass.classLoader.getResourceAsStream(resourcePath)

            // Write InputStream to data file
            try {
                BufferedReader(InputStreamReader(resourceStream)).use { reader ->
                    val writer = BufferedWriter(FileWriter(dataFile))

                    var line: String?
                    do {
                        line = reader.readLine()
                        writer.write("$line\r\n")
                    } while (line != null)
                    writer.close()
                }
            } catch (err: IOException) {
                if (plugin.logger != null) {
                    plugin.logger.severe("Could not save file (IO Exception)! " + if (err.message == null) "" else err.message)
                }
                err.printStackTrace()
            }

        } catch (err: NullPointerException) {
            if (plugin != null && plugin.logger != null) {
                plugin.logger.severe("Could not save file (Null Pointer Exception)! " + if (err.message == null) "" else err.message)
            }
            err.printStackTrace()
        }

    }

    /**
     * Creates a file
     *
     * @param file     The file to create
     * @param contents The contents of the file to create
     */
    @Throws(IOException::class)
    fun create(file: File, contents: String) {
        val lines = Arrays.asList(*contents.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
        val filePath = file.toPath()
        Files.write(filePath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW)
    }

    /**
     * Creates a file
     *
     * @param file     The file to create
     * @param contents The contents of the file to create
     */
    @Throws(IOException::class)
    fun create(file: File, contents: ByteArray) {
        val filePath = file.toPath()
        Files.write(filePath, contents, StandardOpenOption.CREATE_NEW)
    }

}
