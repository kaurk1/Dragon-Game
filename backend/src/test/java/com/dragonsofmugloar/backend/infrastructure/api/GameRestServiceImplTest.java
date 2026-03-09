package com.dragonsofmugloar.backend.infrastructure.api;

import com.dragonsofmugloar.backend.infrastructure.api.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRestServiceImplTest {

    @Mock
    private HttpClientService clientService;

    @InjectMocks
    private GameRestServiceImpl gameRestServiceImpl;

    @Test
    void shouldMapStartNewGameResponse() {
        when(clientService.startNewGame()).thenReturn(
                new StartNewGameResponse("game-1", 3, 100, 1, 0, 500, 0));

        var result = gameRestServiceImpl.startNewGame();

        assertThat(result.getGameId()).isEqualTo("game-1");
        assertThat(result.getLives()).isEqualTo(3);
        assertThat(result.getGold()).isEqualTo(100);
        assertThat(result.getLevel()).isEqualTo(1);
        assertThat(result.getScore()).isEqualTo(0);
        assertThat(result.getHighScore()).isEqualTo(500);
        assertThat(result.getTurn()).isEqualTo(0);
    }

    @Test
    void shouldMapInvestigateReputationResponse() {
        when(clientService.investigateReputation("game-1")).thenReturn(
                new ReputationResponse(5, -2, 3));

        var result = gameRestServiceImpl.investigateReputation("game-1");

        assertThat(result.getPeople()).isEqualTo(5);
        assertThat(result.getState()).isEqualTo(-2);
        assertThat(result.getUnderworld()).isEqualTo(3);
    }

    @Test
    void shouldMapAvailableTasksResponse() {
        when(clientService.getAvailableTasks("game-1")).thenReturn(List.of(
                new AvailableTaskResponse("ad-1", "Help someone", 50, 5, null, "Walk in the park")));

        var result = gameRestServiceImpl.getAvailableTasks("game-1");

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getAdId()).isEqualTo("ad-1");
        assertThat(result.getFirst().getMessage()).isEqualTo("Help someone");
        assertThat(result.getFirst().getReward()).isEqualTo(50);
        assertThat(result.getFirst().getExpiresIn()).isEqualTo(5);
        assertThat(result.getFirst().getProbability()).isEqualTo("Walk in the park");
    }

    @Test
    void shouldMapSolveTaskResponse() {
        when(clientService.solveTask("game-1", "ad-1")).thenReturn(
                new SolvedTaskResponse(true, 3, 150, 200, 500, 5, "Well done!"));

        var result = gameRestServiceImpl.solveTask("game-1", "ad-1");

        assertThat(result.getSuccess()).isTrue();
        assertThat(result.getLives()).isEqualTo(3);
        assertThat(result.getGold()).isEqualTo(150);
        assertThat(result.getScore()).isEqualTo(200);
        assertThat(result.getHighScore()).isEqualTo(500);
        assertThat(result.getTurn()).isEqualTo(5);
        assertThat(result.getMessage()).isEqualTo("Well done!");
    }

    @Test
    void shouldMapShopItemsResponse() {
        when(clientService.getShopItems("game-1")).thenReturn(List.of(
                new ShopItemListResponse("hpot", "Healing Potion", 50)));

        var result = gameRestServiceImpl.getShopItems("game-1");

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getId()).isEqualTo("hpot");
        assertThat(result.getFirst().getName()).isEqualTo("Healing Potion");
        assertThat(result.getFirst().getCost()).isEqualTo(50);
    }

    @Test
    void shouldMapPurchaseItemResponse() {
        when(clientService.purchaseItem("game-1", "cs")).thenReturn(
                new PurchaseResponse(true, 400, 3, 2, 6));

        var result = gameRestServiceImpl.purchaseItem("game-1", "cs");

        assertThat(result.getShoppingSuccess()).isTrue();
        assertThat(result.getGold()).isEqualTo(400);
        assertThat(result.getLives()).isEqualTo(3);
        assertThat(result.getLevel()).isEqualTo(2);
        assertThat(result.getTurn()).isEqualTo(6);
    }
}
