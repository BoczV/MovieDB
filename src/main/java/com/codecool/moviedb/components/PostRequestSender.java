package com.codecool.moviedb.components;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PostRequestSender {

    public void sendPostRequest(URL url, String rating) throws IOException, JSONException {
        HttpURLConnection con = initConnection(url);
        String jsonInputString = initJSONBodyWithOneAttribute("value", rating);
        sendRequest(con, jsonInputString);
        printRequestResponse(con);
    }

    private void sendRequest(HttpURLConnection con, String jsonInputString) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    private String initJSONBodyWithOneAttribute(String key, String value) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(key, value);
        return json.toString();
    }

    private HttpURLConnection initConnection(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        return con;
    }


    private void printRequestResponse(HttpURLConnection con) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
