package com.sity.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publicationDate;
}
