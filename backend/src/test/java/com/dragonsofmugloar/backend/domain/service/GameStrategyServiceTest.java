package com.dragonsofmugloar.backend.domain.service;

import com.dragonsofmugloar.backend.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameStrategyServiceTest {

    private static final String GAME_ID = "game-1";
    private static final String TASK_ID = "task-1";

    @Mock
    private GameRestService gameRestService;

    @InjectMocks
    private GameStrategyService gameStrategyService;

    @Test
    void shouldReturnGameResultWithReachedTargetScore() {
        when(gameRestService.startNewGame()).thenReturn(game(0, 3, 0));
        when(gameRestService.getAvailableTasks(GAME_ID)).thenReturn(List.of(task()));
        when(gameRestService.solveTask(GAME_ID, TASK_ID)).thenReturn(taskResult(1000, 3, 100));
        when(gameRestService.getShopItems(GAME_ID)).thenReturn(List.of());

        var result = gameStrategyService.playGame(1000);

        assertThat(result.getReachedTarget()).isTrue();
        assertThat(result.getScore()).isEqualTo(1000);
        assertThat(result.getGold()).isEqualTo(100);
        assertThat(result.getLives()).isEqualTo(3);
    }

    @Test
    void shouldReturnGameResultWhenCharacterDies() {
        when(gameRestService.startNewGame()).thenReturn(game(0, 3, 100));
        when(gameRestService.getAvailableTasks(GAME_ID)).thenReturn(List.of(task()));
        when(gameRestService.solveTask(GAME_ID, TASK_ID)).thenReturn(taskResult(0, 0, 100));

        var result = gameStrategyService.playGame(1000);

        assertThat(result.getReachedTarget()).isFalse();
        assertThat(result.getLives()).isEqualTo(0);
        assertThat(result.getGold()).isEqualTo(100);
        assertThat(result.getScore()).isEqualTo(0);
    }

    @Test
    void shouldPurchaseItemWhenShoppingStrategyDecidesSo() {
        when(gameRestService.startNewGame()).thenReturn(game(0, 3, 0));
        when(gameRestService.getAvailableTasks(GAME_ID)).thenReturn(List.of(task()));
        when(gameRestService.solveTask(GAME_ID, TASK_ID)).thenReturn(taskResult(1000, 3, 300));
        when(gameRestService.getShopItems(GAME_ID)).thenReturn(getShopItems());
        when(gameRestService.purchaseItem(GAME_ID, "cs")).thenReturn(purchaseResult(200, 3, 1));

        gameStrategyService.playGame(1000);
    }

    @Test
    void shouldPlayMultipleTurnsUntilTargetReachedAndDontBuyAnything() {
        when(gameRestService.startNewGame()).thenReturn(game(0, 3, 0));
        when(gameRestService.getAvailableTasks(any())).thenReturn(List.of(task()));
        when(gameRestService.solveTask(any(), any()))
                .thenReturn(taskResult(300, 3, 100))
                .thenReturn(taskResult(700, 3, 170))
                .thenReturn(taskResult(1000, 3, 220));
        when(gameRestService.getShopItems(any())).thenReturn(List.of());

        var result = gameStrategyService.playGame(1000);

        assertThat(result.getReachedTarget()).isTrue();
        assertThat(result.getGold()).isEqualTo(220);
        verify(gameRestService, never()).purchaseItem(any(), any());
    }

    private static Game game(Integer score, Integer lives, Integer gold) {
        return Game.builder()
                .gameId(GAME_ID)
                .score(score)
                .lives(lives)
                .gold(gold).turn(0).level(0).build();
    }

    private static Task task() {
        return Task.builder()
            .adId(TASK_ID)
            .message("Do something")
            .probability("Walk in the park")
            .reward(100)
            .build();
    }

    private static TaskResult taskResult(Integer score, Integer lives, Integer gold) {
        return TaskResult.builder()
            .success(true)
            .score(score)
            .lives(lives)
            .gold(gold)
            .turn(1)
            .build();
    }

    private static Purchase purchaseResult(Integer gold, Integer lives, Integer level) {
        return Purchase.builder()
                .shoppingSuccess(true)
                .gold(gold)
                .lives(lives)
                .level(level)
                .turn(1)
                .build();
    }

    private static List<ShopItem> getShopItems() {
        return List.of(
            ShopItem.builder().id("hpot").name("Healing potion").cost(50).build(),
            ShopItem.builder().id("cs").name("Claw Sharpening").cost(100).build(),
            ShopItem.builder().id("gas").name("Gasoline").cost(100).build(),
            ShopItem.builder().id("wax").name("Copper Plating").cost(100).build());
    }
}
