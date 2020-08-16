package com.ashutka.app.service;

import com.ashutka.app.dto.BookDto;
import com.ashutka.app.exception.ApiException;
import com.ashutka.app.exception.GoogleApiException;
import com.ashutka.app.response.BooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Searches Books using Google API
 * For more info - refer <a href="https://developers.google.com/books/docs/v1/reference/volumes/list">Google API</a>}
 */
@Service
public class BooksSearchService {

    @Value("${google.books.search.endpoint}")
    private String searchBooksUrl;

    @Value("${api.result.maxsize}")
    private Integer maxResult;

    private final RestTemplate restTemplate;

    @Autowired
    public BooksSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookDto> searchBooks(String searchQuery) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(searchBooksUrl)
                .queryParam("q", searchQuery)
                .queryParam("maxResults", String.valueOf(maxResult));
        BooksResponse response = restTemplate.getForObject(builder.toUriString(), BooksResponse.class);
        if (response !=  null) {
            return response.getBooks();
        }
        throw new GoogleApiException();
    }
}
