package com.apipgoogle.serpapi.service;

import java.io.IOException;

import org.springframework.stereotype.Service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Value;


@Service
public class SearchAuthor {

    private static final String BASE_URL = "https://serpapi.com/search";
    @Value("${serpapi.API_KEY}")
    private String apiKEY;

    public JsonObject searchByAuthor(String authorId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "?engine=google_scholar_author&author_id=" + authorId + "&api_key=" + apiKEY)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            return json;
        }
    }
    
}
