package com.bryonnicoson.plantpractice.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bryon on 2/22/18.
 */

public class NetworkDAO {

    public String fetch(String uri) throws IOException {
        // a string builder will accumulate a collection of strings
        StringBuilder sb = new StringBuilder();

        // open the connection to this URL
        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // read input stream and buffer
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            // the line being read
            String inputLine;

            // read until out of lines
            while ((inputLine = bin.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();
    }
}
