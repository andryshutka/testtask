package com.ashutka.app.service;

import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.dto.BookDto;
import com.ashutka.app.model.CreationModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreationService {

    private final AlbumsSearchService albumsSearchService;
    private final BooksSearchService booksSearchService;

    @Autowired
    public CreationService(AlbumsSearchService albumsSearchService, BooksSearchService booksSearchService) {
        this.albumsSearchService = albumsSearchService;
        this.booksSearchService = booksSearchService;
    }

    public List<CreationModel> searchCreations(String searchCriteria) {
        List<AlbumDto> albums = albumsSearchService.searchAlbums(searchCriteria);
        List<BookDto> books = booksSearchService.searchBooks(searchCriteria);

        List<CreationModel> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(albums)) {
            result.addAll(albums.stream()
                    .map(CreationModel::fromAlbum)
                    .collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(books)) {
            result.addAll(books.stream()
                    .filter(dto -> dto.getVolumeInfo() != null)
                    .map(CreationModel::fromBook)
                    .collect(Collectors.toList()));
        }

        return result.stream()
                .sorted(Comparator.comparing(s -> StringUtils.lowerCase(s.getTitle())))
                .collect(Collectors.toList());
    }
}
