package com.ashutka.app.actuator;

import com.ashutka.app.dto.AlbumDto;
import com.ashutka.app.dto.BookDto;
import com.ashutka.app.service.AlbumsSearchService;
import com.ashutka.app.service.BooksSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Health check of google and itunes API
 */
@Component
public class HealthIndicator extends AbstractHealthIndicator {

    private static final String REQ_STRING = "test";

    private final BooksSearchService booksSearchService;
    private final AlbumsSearchService albumsSearchService;

    @Autowired
    public HealthIndicator(BooksSearchService booksSearchService, AlbumsSearchService albumsSearchService) {
        this.booksSearchService = booksSearchService;
        this.albumsSearchService = albumsSearchService;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) {

        List<BookDto> bookDtos = booksSearchService.searchBooks(REQ_STRING);
        List<AlbumDto> albumsDtos = albumsSearchService.searchAlbums(REQ_STRING);

        if (!CollectionUtils.isEmpty(bookDtos) && !CollectionUtils.isEmpty(albumsDtos)) {
            builder.up()
                    .withDetail("itunes.api", "Alive")
                    .withDetail("google.api", "Alive");
        } else if (CollectionUtils.isEmpty(bookDtos)) {
            builder.up()
                    .withDetail("itunes.api", "Alive");
        } else if (CollectionUtils.isEmpty(albumsDtos)) {
            builder.up()
                    .withDetail("google.api", "Alive");
        }
    }
}
