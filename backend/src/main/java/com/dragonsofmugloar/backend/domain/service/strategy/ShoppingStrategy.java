package com.dragonsofmugloar.backend.domain.service.strategy;

import com.dragonsofmugloar.backend.domain.model.Character;
import com.dragonsofmugloar.backend.domain.model.Game;
import com.dragonsofmugloar.backend.domain.model.ShopItem;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingStrategy {

    private static final String HEALING_POTION = "hpot";
    private static final Integer SAFE_GOLD_RESERVE = 150;

    public static Optional<String> decideItemToBuy(Game game, Character character, List<ShopItem> shopItems) {
        if (game.getLives() < 3) {
            return shopItems.stream()
                    .filter(item -> item.getId().equals(HEALING_POTION))
                    .filter(item -> game.getGold() >= item.getCost())
                    .map(ShopItem::getId)
                    .findFirst();
        }

        return shopItems.stream()
                .filter(item -> !item.getId().equals(HEALING_POTION))
                .filter(item -> game.getGold() >= item.getCost() + SAFE_GOLD_RESERVE)
                .min(Comparator.comparingInt(item -> character.getItemCount(item.getId())))
                .map(ShopItem::getId);
    }
}
