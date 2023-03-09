package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<BookInventory, Long> {
    List<BookInventory> findByBookIdIn(List<Long> id);

}
