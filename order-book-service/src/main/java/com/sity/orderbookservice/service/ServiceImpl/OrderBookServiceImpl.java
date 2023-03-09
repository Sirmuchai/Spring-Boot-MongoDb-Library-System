package com.sity.orderbookservice.service.ServiceImpl;

import com.sity.orderbookservice.dto.InventoryResponse;
import com.sity.orderbookservice.dto.OrderBookRequest;
import com.sity.orderbookservice.dto.ReserveBookDto;
import com.sity.orderbookservice.model.OrderBook;
import com.sity.orderbookservice.model.ReservedBooks;
import com.sity.orderbookservice.repository.OrderBookRepository;
import com.sity.orderbookservice.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderBookServiceImpl implements OrderBookService {

    private final OrderBookRepository orderBookRepository;

    private final WebClient webClient;
    @Override
    public void placeBookOrder(OrderBookRequest orderBookRequest) {
        OrderBook orderBook = new OrderBook();
        orderBook.setOrderNumber(UUID.randomUUID().toString());

        List<ReservedBooks> reservedBooks = orderBookRequest.getReserveBookDtoList()
                .stream()
                .map(this::mapToReserveEntity)
                .collect(Collectors.toList());

        orderBook.setReservedBooksList(reservedBooks);

        List<Long> bookIds = orderBook.getReservedBooksList().stream()
                .map(ReservedBooks::getBookId)
                .toList();

        //Call inventory service and place order if book is in stock
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8083/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("id", bookIds).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allBooksInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if(allBooksInStock){
            orderBookRepository.save(orderBook);
        }
        else {
            throw new IllegalArgumentException("Book is not availlable");
        }


    }

    public ReservedBooks mapToReserveEntity(ReserveBookDto reserveBookDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reserveBookDto, ReservedBooks.class);
    }
}
