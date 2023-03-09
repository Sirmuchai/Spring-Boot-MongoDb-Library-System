package com.sity.orderbookservice.repository;

import com.sity.orderbookservice.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
}
