package com.sity.orderbookservice.service;

import com.sity.orderbookservice.dto.OrderBookRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderBookService {
    void placeBookOrder(OrderBookRequest orderBookRequest);
}
