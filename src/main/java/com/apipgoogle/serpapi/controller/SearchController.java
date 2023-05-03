package com.apipgoogle.serpapi.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apipgoogle.serpapi.model.SearchResults;
import com.apipgoogle.serpapi.repository.SearchRepositoryResult;
import com.apipgoogle.serpapi.service.GoogleSearch;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


@RestController
@RequestMapping("/search")
public class SearchController {
    
    private final GoogleSearch googleSearch;
    private final SearchRepositoryResult searchResultsRepository;

    public SearchController(GoogleSearch googleSearch, SearchRepositoryResult searchResultsRepository)
     {
        this.googleSearch = googleSearch;
        this.searchResultsRepository = searchResultsRepository;
    }

    @GetMapping 
    public List<SearchResults> search(@RequestParam(required=false,name="query") String query) throws IOException {
        JsonObject response = googleSearch.search(query);
        System.out.println(query);
        JsonArray results = response.getAsJsonArray("organic_results");
        List<SearchResults> searchResults = new ArrayList<>();
        for (JsonElement result : results) {
            SearchResults searchResult = new SearchResults();
            searchResult.setTitle(result.getAsJsonObject().get("title").getAsString());
            searchResult.setUrl(result.getAsJsonObject().get("link").getAsString());
            searchResult.setDescription(result.getAsJsonObject().get("snippet").getAsString());
            searchResult.setEngine("Google");
            searchResult.setCreatedAt(LocalDateTime.now());
            searchResult.setUpdatedAt(LocalDateTime.now());
            searchResults.add(searchResult);
        }        
        searchResultsRepository.saveAll(searchResults);
        return searchResults;
    }
}
 


   




