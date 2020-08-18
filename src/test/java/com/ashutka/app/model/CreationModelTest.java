package com.ashutka.app.model;

import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.dto.BookDto;
import com.ashutka.app.dto.VolumeInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreationModelTest {

    @Test
    public void testNullBookConverter() {
        BookDto bookDto = prepareNullBookDto();
        assertNotNull(CreationModel.fromBook(bookDto));
        assertEquals(StringUtils.EMPTY, CreationModel.fromBook(bookDto).getAuthor());
    }

    @Test
    public void testNonNullBookConverter() {
        BookDto bookDto = prepareNonNullBookDto();
        assertNotNull(CreationModel.fromBook(bookDto));
        assertEquals("title", CreationModel.fromBook(bookDto).getTitle());
        assertEquals(StringUtils.EMPTY, CreationModel.fromBook(bookDto).getAuthor());
    }

    @Test
    public void testBookConverterWithAuthor() {
        BookDto bookDto = prepareBookDtoAuthors();
        assertNotNull(CreationModel.fromBook(bookDto));
        assertEquals("title", CreationModel.fromBook(bookDto).getTitle());
        assertEquals("Author1, Author2", CreationModel.fromBook(bookDto).getAuthor());
    }

    @Test
    public void testAlbumConverter() {
        AlbumDto albumDto = prepareAlbumDto();

        assertEquals("Wrong", albumDto.getCollectionName());
        assertEquals("Untilted Artist", albumDto.getArtistName());
    }

    private AlbumDto prepareAlbumDto() {
        return new AlbumDto("Untilted Artist",  "Wrong");
    }

    private BookDto prepareNonNullBookDto() {
        return new BookDto(new VolumeInfoDto("title", null));
    }

    private BookDto prepareBookDtoAuthors() {
        return new BookDto(new VolumeInfoDto("title", List.of("Author1", "Author2")));
    }

    private BookDto prepareNullBookDto() {
        return new BookDto(new VolumeInfoDto(null, null));
    }
}
