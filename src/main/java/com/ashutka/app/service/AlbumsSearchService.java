package com.ashutka.app.service;

import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.exception.ApiException;
import com.ashutka.app.exception.AppleApiException;
import com.ashutka.app.response.AlbumsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Searches albums using iTumes API
 * For more info - refer <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/">iTunes API</a>}
 */
@Service
public class AlbumsSearchService {

    private static final String ENTITY_SEARCH = "album";

    @Value("${apple.albums.search.endpoint}")
    private String searchAlbumsUrl;

    @Value("${api.result.maxsize}")
    private Integer maxResult;

    private final RestTemplate restTemplate;

    @Autowired
    public AlbumsSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AlbumDto> searchAlbums(String searchQuery) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(searchAlbumsUrl)
                .queryParam("term", searchQuery)
                .queryParam("limit", String.valueOf(maxResult))
                .queryParam("entity", ENTITY_SEARCH);
        AlbumsResponse response = restTemplate.getForObject(builder.toUriString(), AlbumsResponse.class);
        if (response !=  null) {
            return response.getAlbums();
        }
        throw new AppleApiException();
    }
}
