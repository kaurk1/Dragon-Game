package com.dragonsofmugloar.backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private Boolean shoppingSuccess;
    private Integer gold;
    private Integer lives;
    private Integer level;
    private Integer turn;
}
