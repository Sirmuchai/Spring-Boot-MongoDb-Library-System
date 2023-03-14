package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryCatalog;
import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<Long> id){

        return inventoryService.isInStock(id);
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryCatalog getInventoryCatalog(@PathVariable("bookId") Long id){
        return inventoryService.getInventoryCatalog(id);
    }
}
