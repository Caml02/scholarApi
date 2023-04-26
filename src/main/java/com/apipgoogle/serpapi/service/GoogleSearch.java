package com.apipgoogle.serpapi.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class GoogleSearch {

    private static final String BASE_URL = "https://serpapi.com/search";
    private static final String apiKEY = System.getenv("API_KEY");

    public JsonObject search(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "?engine=google_scholar&q=" + query + "&api_key=" + apiKEY)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            return json;
        }
    }
}
