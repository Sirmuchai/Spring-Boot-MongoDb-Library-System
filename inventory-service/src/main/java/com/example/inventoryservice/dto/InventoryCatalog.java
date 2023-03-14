package com.example.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCatalog {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private int quantity;

}
