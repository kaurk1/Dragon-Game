package com.dragonsofmugloar.backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItemListResponse {
    private String id;
    private String name;
    private Integer cost;
}
