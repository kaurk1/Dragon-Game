package com.dragonsofmugloar.backend.domain.service.strategy;

import com.dragonsofmugloar.backend.domain.model.Character;
import com.dragonsofmugloar.backend.domain.model.Game;
import com.dragonsofmugloar.backend.domain.model.ShopItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingStrategyTest {

    @Test
    void shouldBuyHealingPotionWhenLivesLow() {
        var game = gameWith(2, 200);
        var character = new Character();

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo("hpot");
    }

    @Test
    void shouldNotBuyHealingPotionWhenNotEnoughGold() {
        var game = gameWith(2, 10);
        var character = new Character();

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldBuyItemNotYetOwned() {
        var game = gameWith(3, 500);
        var character = new Character();
        character.addItem("cs");

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isPresent();
        assertThat(result.get()).isNotEqualTo("cs");
        assertThat(result.get()).isNotEqualTo("hpot");
    }

    @Test
    void shouldRestartFromBeginningWhenAllItemsOwned() {
        var game = gameWith(3, 1000);
        var character = new Character();
        character.addItem("cs");
        character.addItem("gas");
        character.addItem("wax");
        character.addItem("tricks");
        character.addItem("wingpot");

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo("cs");
    }

    @Test
    void shouldNotBuyUpgradeWhenNotEnoughGold() {
        var game = gameWith(3, 100);
        var character = new Character();

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldNotBuyHealingPotionAsUpgrade() {
        var game = gameWith(3, 500);
        var character = new Character();

        var result = ShoppingStrategy.decideItemToBuy(game, character, getShopItems());

        assertThat(result).isPresent();
        assertThat(result.get()).isNotEqualTo("hpot");
    }

    private static Game gameWith(Integer lives, Integer gold) {
        return Game.builder()
                .gameId("test")
                .lives(lives)
                .gold(gold)
                .build();
    }

    private static List<ShopItem> getShopItems() {
        return List.of(
                ShopItem.builder().id("hpot").name("Healing Potion").cost(50).build(),
                ShopItem.builder().id("cs").name("Claw Sharpening").cost(90).build(),
                ShopItem.builder().id("gas").name("Gasoline").cost(100).build(),
                ShopItem.builder().id("wax").name("Copper Plating").cost(100).build(),
                ShopItem.builder().id("tricks").name("Book of Tricks").cost(100).build(),
                ShopItem.builder().id("wingpot").name("Potion of Stronger Wings").cost(300).build()
        );
    }
}
