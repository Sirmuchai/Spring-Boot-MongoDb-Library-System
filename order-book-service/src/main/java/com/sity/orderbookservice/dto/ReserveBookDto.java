package com.sity.orderbookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveBookDto {
    private Long id;
    private Long bookId;
    private Integer quantity;
}
