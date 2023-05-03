package com.apipgoogle.serpapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apipgoogle.serpapi.model.SearchAuthorModel;
import com.apipgoogle.serpapi.repository.SearchAuthorRepository;
import com.apipgoogle.serpapi.service.SearchAuthor;
import com.google.gson.JsonObject;


@RestController
@RequestMapping("/author")
public class SearchAuthorController {
    
    private final SearchAuthor searchAuthor;
    private final SearchAuthorRepository searchAuthorRepository;

    public SearchAuthorController(SearchAuthor searchAuthor, SearchAuthorRepository searchAuthorRepository) {
        this.searchAuthor = searchAuthor;
        this.searchAuthorRepository = searchAuthorRepository;
    }

    @GetMapping
    public List<SearchAuthorModel> searchByAuthor(@RequestParam(required=false,name="authorId") String authorId) throws IOException {
        JsonObject response = searchAuthor.searchByAuthor(authorId);
        System.out.println(authorId);
        JsonObject author = response.getAsJsonObject("author");
        SearchAuthorModel searchAuthorModel = new SearchAuthorModel();
        searchAuthorModel.setName(author.get("name").getAsString());
        searchAuthorModel.setAffiliations(author.get("affiliations").getAsString());
        searchAuthorModel.setEmail(author.get("email").getAsString());
        searchAuthorRepository.save(searchAuthorModel);
        List<SearchAuthorModel> resultList = new ArrayList<>();

        resultList.add(searchAuthorModel);
        return resultList;
    }
}
 
