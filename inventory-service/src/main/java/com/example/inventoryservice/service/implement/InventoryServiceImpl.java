package com.example.inventoryservice.service.implement;


import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.BookInventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<Long> id) {
        return inventoryRepository.findByBookIdIn(id)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InventoryResponse mapToResponse(BookInventory bookInventory){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookInventory, InventoryResponse.class);
    }
}
