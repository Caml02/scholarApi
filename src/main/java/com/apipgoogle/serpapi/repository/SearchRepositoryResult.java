package com.apipgoogle.serpapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apipgoogle.serpapi.model.SearchResults;

@Repository
public interface SearchRepositoryResult extends JpaRepository<SearchResults, Integer> {

}