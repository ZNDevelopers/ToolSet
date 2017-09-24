package com.zndevs.toolset.tools;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL Downloader
 */

public class URLDownloader {

    private String data = "";

    public URLDownloader(String urlString) throws IOException {
        data = downloadUrl(urlString);
    }

    public URLDownloader() {
    }

    public String downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder totalContents = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            totalContents.append(inputLine).append("\n");
        }
        reader.close();

        return data = totalContents.toString();
    }

    public String getResponse() {
        return this.data;
    }

    public byte[] downloadUrlBytes(String urlString) throws IOException {
        InputStream is = null;
        URL url = new URL(urlString);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = url.openStream();
            byte[] chunk = new byte[4096];
            int n;

            while ((n = is.read(chunk)) > 0) {
                baos.write(chunk, 0, n);
            }
        } finally {
            if (is != null) is.close();
        }

        return baos.toByteArray();
    }

}
