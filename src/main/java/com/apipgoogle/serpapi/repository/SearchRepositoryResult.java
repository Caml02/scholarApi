package com.apipgoogle.serpapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.apipgoogle.serpapi.model.SearchResults;

@Repository
public interface SearchRepositoryResult extends CrudRepository<SearchResults, Integer> {

}