package com.dragonsofmugloar.backend.domain.service;

import com.dragonsofmugloar.backend.application.GameService;
import com.dragonsofmugloar.backend.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRestService gameRestService;

    @Override
    public Game startGame() {
        return gameRestService.startNewGame();
    }

    @Override
    public Reputation investigateReputation(String gameId) {
        return gameRestService.investigateReputation(gameId);
    }

    @Override
    public List<Task> getAvailableTasks(String gameId) {
        return gameRestService.getAvailableTasks(gameId);
    }

    @Override
    public TaskResult solveTask(String gameId, String adId) {
        return gameRestService.solveTask(gameId, adId);
    }

    @Override
    public List<ShopItem> getShopItems(String gameId) {
        return gameRestService.getShopItems(gameId);
    }

    @Override
    public PurchaseResult purchaseItem(String gameId, String itemId) {
        return gameRestService.purchaseItem(gameId, itemId);
    }
}
