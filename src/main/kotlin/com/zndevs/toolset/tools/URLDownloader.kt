package com.zndevs.toolset.tools

import com.sun.deploy.net.HttpDownload
import sun.net.www.http.HttpClient
import java.io.*
import java.net.URL

/**
 * URL Downloader
 */

class URLDownloader {

    var response = ""
        private set

    constructor(urlString: String) {
        response = downloadUrl(urlString)
    }

    constructor()

    fun downloadUrl(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection()

        val inputStream = connection.getInputStream()

        val response = inputStream.bufferedReader().use { it.readText() }
        return response
    }

    fun downloadUrlBytes(urlString: String): ByteArray {
        val url = URL(urlString)

        url.openStream().use {
            return it.readBytes()
        }
    }

}
