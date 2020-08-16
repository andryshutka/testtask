package com.ashutka.app.model;

import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.dto.BookDto;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
public class CreationModel {

    private String title;
    private String author;
    private CreationType type;

    public static CreationModel fromAlbum(AlbumDto albumDto) {
        return CreationModel.builder()
                .title(albumDto.getCollectionName())
                .author(albumDto.getArtistName())
                .type(CreationType.ALBUM)
                .build();
    }

    public static CreationModel fromBook(BookDto bookDto) {
        return CreationModel.builder()
                .title(bookDto.getVolumeInfo().getTitle())
                .author(bookDto.getVolumeInfo().getAuthors() != null
                        ? bookDto.getVolumeInfo().getAuthors().stream()
                            .filter(Objects::nonNull)
                            .collect(Collectors.joining(", "))
                        : StringUtils.EMPTY)
                .type(CreationType.BOOK)
                .build();
    }
}
