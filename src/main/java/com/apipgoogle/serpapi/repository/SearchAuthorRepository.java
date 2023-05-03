package com.apipgoogle.serpapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apipgoogle.serpapi.model.SearchAuthorModel;

@Repository
public interface SearchAuthorRepository extends JpaRepository<SearchAuthorModel, Integer> {

}

