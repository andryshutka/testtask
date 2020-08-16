package com.ashutka.app.response;

import com.ashutka.app.dto.BookDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BooksResponse {

    private String kind;

    private Long totalItems;

    @JsonProperty("items")
    private List<BookDto> books;
}
