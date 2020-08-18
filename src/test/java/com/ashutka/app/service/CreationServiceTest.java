package com.ashutka.app.service;


import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.dto.BookDto;
import com.ashutka.app.dto.VolumeInfoDto;
import com.ashutka.app.model.CreationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreationServiceTest {

    @InjectMocks
    private CreationService creationService;

    @Mock
    private AlbumsSearchService albumsSearchService;

    @Mock
    private BooksSearchService booksSearchService;

    @Test
    public void testSearchCreations() {

        when(albumsSearchService.searchAlbums("test")).thenReturn(prepareAlbums());
        when(booksSearchService.searchBooks("test")).thenReturn(prepareBooks());
        List<CreationModel> test = creationService.searchCreations("test");

        assertEquals(3, test.size());

    }

    private List<BookDto> prepareBooks() {
        return List.of(
                new BookDto(null),
                new BookDto(new VolumeInfoDto("Brothers LionHeart", List.of("A. Lindgren")))
        );
    }

    private List<AlbumDto> prepareAlbums() {
        return List.of(
                new AlbumDto("Adele", "album1"),
                new AlbumDto("Sting", "album2")

        );
    }
}
