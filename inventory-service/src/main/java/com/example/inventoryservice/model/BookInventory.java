package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    @Enumerated(EnumType.STRING)
    private  BookStatus status;
    private int quantity;

}
