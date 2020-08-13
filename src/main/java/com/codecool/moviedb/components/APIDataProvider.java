package com.codecool.moviedb.components;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class APIDataProvider {

    private String getAllLines() {
        try {
            InputStream inputStream = new FileInputStream("./src/main/resources/sensitiveData.txt");
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuilder stringBuffer = new StringBuilder();
            String resultString;
            while ((resultString = reader.readLine()) != null) {
                stringBuffer.append(resultString).append(",");
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getPreciseKey(KeyType keyType) {
        String data = getAllLines();
        String[] keys = data.split(",");
        switch (keyType.toString()) {
            case "API_KEY":
                return keys[0];
            case "GUEST_SESSION":
                return keys[1];
            default:
                throw new IllegalStateException();
        }
    }
}
