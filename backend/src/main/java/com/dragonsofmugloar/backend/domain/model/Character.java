package com.dragonsofmugloar.backend.domain.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Character {
    private Map<String, Integer> items = new HashMap<>();

    public void addItem(String itemId) {
        items.merge(itemId, 1, Integer::sum);
    }

    public Integer getItemCount(String itemId) {
        return items.getOrDefault(itemId, 0);
    }
}
