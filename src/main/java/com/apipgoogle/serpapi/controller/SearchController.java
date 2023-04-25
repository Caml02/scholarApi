package com.apipgoogle.serpapi.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apipgoogle.serpapi.model.SearchResults;
import com.apipgoogle.serpapi.repository.SearchRepositoryResult;
import com.apipgoogle.serpapi.service.GoogleSearch;
import com.apipgoogle.serpapi.service.SearchServiceIMPL.SearchServicesIMPL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


@RestController
@RequestMapping("/search")
public class SearchController {
    
    /**@Autowired
    private GoogleSearch googleSearch;
    @Autowired
    private SearchRepositoryResult searchResultsRepository;**/
    private final GoogleSearch googleSearch;
    private final SearchRepositoryResult searchResultsRepository;

    public SearchController(GoogleSearch googleSearch, SearchRepositoryResult searchResultsRepository) {
        this.googleSearch = googleSearch;
        this.searchResultsRepository = searchResultsRepository;
    }

    @GetMapping /**(path = "/{query}", produces = { "application/json" })**/
    public List<SearchResults> search(@RequestParam("query") String query) throws IOException {
        JsonObject response = googleSearch.search(query);
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

    @Autowired
    private SearchServicesIMPL impl;

    @GetMapping("/ConsultResult")
    @RequestMapping(value = "ConsultResult", method = RequestMethod.GET)
    public ResponseEntity<?> ConsultResult() {
        List<SearchResults> listSearchResult=this.impl.ConsultResult();
        return ResponseEntity.ok(listSearchResult);
    }


    @PostMapping
    @RequestMapping(value = "CreateResult", method = RequestMethod.POST)
    public ResponseEntity<?> CreateResult(@RequestBody SearchResults searchResults) {
        SearchResults ResultCreated=this.impl.CreateResult(searchResults);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultCreated);
    }

    @PostMapping
    @RequestMapping(value = "UpdatedResult", method = RequestMethod.POST)
    public ResponseEntity<?> UpdatedResult(@RequestBody SearchResults searchResults) {
        SearchResults ResultUpdated=this.impl.UpdatedResult(searchResults);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultUpdated);
    }

    @GetMapping
    @RequestMapping(value = "getSearchResult/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSearchResult(@PathVariable int id) {
        SearchResults ResultFound=this.impl.getSearchResult(id);
        return ResponseEntity.ok(ResultFound);
    }

        @DeleteMapping(value = "DeleteResult/{id}")
    public ResponseEntity<?> DeleteResult(@PathVariable int id) {
        this.impl.DeleteResult(id);
        return ResponseEntity.ok().build();
    }

}   




