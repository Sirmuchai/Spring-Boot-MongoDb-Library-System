package com.example.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publicationDate;
}
