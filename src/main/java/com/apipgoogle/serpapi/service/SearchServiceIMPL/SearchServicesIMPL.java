package com.apipgoogle.serpapi.service.SearchServiceIMPL;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.apipgoogle.serpapi.model.SearchResults;
import com.apipgoogle.serpapi.repository.SearchRepositoryResult;
import com.apipgoogle.serpapi.service.SearchServiceResults;

@Service
public class SearchServicesIMPL implements SearchServiceResults {
    
    
    @Autowired
    private SearchRepositoryResult repo;

    @GetMapping
    public ArrayList<SearchResults> ConsultResult() {
        return (ArrayList<SearchResults>) this.repo.findAll();
    }

    @Override
    public SearchResults CreateResult(SearchResults searchResults) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CreateResult'");
    }

    @Override
    public SearchResults UpdatedResult(SearchResults searchResults) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdatedResult'");
    }

    @Override
    public SearchResults getSearchResult(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSearchResult'");
    }

    @Override
    public void DeleteResult(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteResult'");
    }


}
