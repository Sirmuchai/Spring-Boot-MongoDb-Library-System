package com.sity.orderbookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookRequest {
    private List<ReserveBookDto> reserveBookDtoList;
}
