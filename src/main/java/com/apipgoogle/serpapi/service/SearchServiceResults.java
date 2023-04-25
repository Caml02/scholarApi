package com.apipgoogle.serpapi.service;

import java.util.List;

import com.apipgoogle.serpapi.model.SearchResults;

public interface SearchServiceResults {
    
    public List<SearchResults> ConsultResult();

    public SearchResults CreateResult(SearchResults searchResults);

    public SearchResults UpdatedResult(SearchResults searchResults);

    public SearchResults getSearchResult(int id);

    public void DeleteResult(int id);

}
