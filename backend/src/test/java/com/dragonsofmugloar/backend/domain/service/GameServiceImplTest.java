package com.dragonsofmugloar.backend.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private GameRestService gameRestService;

    @Mock
    private GameStrategyService gameStrategyService;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;

    @Test
    void shouldStartGame() {
        gameServiceImpl.startGame();
        verify(gameRestService).startNewGame();
    }

    @Test
    void shouldInvestigateReputation() {
        gameServiceImpl.investigateReputation("game-1");
        verify(gameRestService).investigateReputation("game-1");
    }

    @Test
    void shouldGetAvailableTasks() {
        gameServiceImpl.getAvailableTasks("game-1");
        verify(gameRestService).getAvailableTasks("game-1");
    }

    @Test
    void shouldSolveTask() {
        gameServiceImpl.solveTask("game-1", "ad-1");
        verify(gameRestService).solveTask("game-1", "ad-1");
    }

    @Test
    void shouldGetShopItems() {
        gameServiceImpl.getShopItems("game-1");
        verify(gameRestService).getShopItems("game-1");
    }

    @Test
    void shouldPurchaseItem() {
        gameServiceImpl.purchaseItem("game-1", "hpot");
        verify(gameRestService).purchaseItem("game-1", "hpot");
    }

    @Test
    void shouldPlayGame() {
        gameServiceImpl.playGame(1000);
        verify(gameStrategyService).playGame(1000);
    }
}
