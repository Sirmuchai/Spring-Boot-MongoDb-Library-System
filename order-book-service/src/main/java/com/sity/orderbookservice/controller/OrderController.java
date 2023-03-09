package com.sity.orderbookservice.controller;

import com.sity.orderbookservice.dto.OrderBookRequest;
import com.sity.orderbookservice.service.OrderBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderBookService orderBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderBookRequest orderBookRequest){
        orderBookService.placeBookOrder(orderBookRequest);
        return "Order Placed Successfully";
    }


}
