package com.dragonsofmugloar.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResult {
    private Boolean shoppingSuccess;
    private Integer gold;
    private Integer lives;
    private Integer level;
    private Integer turn;
}
