package com.example.inventoryservice.service.implement;


import com.example.inventoryservice.dto.InventoryCatalog;
import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Book;
import com.example.inventoryservice.model.BookInventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private  final WebClient webClient;
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<Long> id) {
        return inventoryRepository.findByBookIdIn(id)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public InventoryCatalog getInventoryCatalog(Long id) {
        InventoryCatalog inventoryCatalog = new InventoryCatalog();
        Book book = webClient
                .get()
                .uri("http://localhost:8081/api/v1/books/" + id)
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        Optional<BookInventory> bookInventory = inventoryById(id);


        inventoryCatalog.setBookId(book.getId());
        inventoryCatalog.setTitle(book.getTitle());
        inventoryCatalog.setAuthor(book.getAuthor());
        inventoryCatalog.setIsbn(book.getIsbn());
        inventoryCatalog.setQuantity(bookInventory.get().getQuantity());

        return inventoryCatalog;

    }

    public InventoryResponse mapToResponse(BookInventory bookInventory){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookInventory, InventoryResponse.class);
    }
    public Optional<BookInventory> inventoryById(Long id){
        return inventoryRepository.findById(id);
    }
}
