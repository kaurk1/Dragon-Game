package com.dragonsofmugloar.backend.infrastructure.api;

import com.dragonsofmugloar.backend.domain.model.*;
import com.dragonsofmugloar.backend.domain.service.GameRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameRestServiceImpl implements GameRestService {

    private final HttpClientService clientService;

    @Override
    public Game startNewGame() {
        var startNewGameResponse = clientService.startNewGame();
        return Game.builder()
                .gameId(startNewGameResponse.getGameId())
                .lives(startNewGameResponse.getLives())
                .gold(startNewGameResponse.getGold())
                .level(startNewGameResponse.getLevel())
                .score(startNewGameResponse.getScore())
                .highScore(startNewGameResponse.getHighScore())
                .turn(startNewGameResponse.getTurn())
                .build();
    }

    @Override
    public Reputation investigateReputation(String gameId) {
        var reputationResponse = clientService.investigateReputation(gameId);
        return Reputation.builder()
                .people(reputationResponse.getPeople())
                .state(reputationResponse.getState())
                .underworld(reputationResponse.getUnderworld())
                .build();
    }

    @Override
    public List<Task> getAvailableTasks(String gameId) {
        return clientService.getAvailableTasks(gameId).stream()
                .map(taskResponse -> Task.builder()
                        .adId(taskResponse.getAdId())
                        .message(taskResponse.getMessage())
                        .reward(taskResponse.getReward())
                        .expiresIn(taskResponse.getExpiresIn())
                        .encrypted(taskResponse.getEncrypted())
                        .probability(taskResponse.getProbability())
                        .build())
                .toList();
    }

    @Override
    public TaskResult solveTask(String gameId, String adId) {
        var solvedTaskResponse = clientService.solveTask(gameId, adId);
        return TaskResult.builder()
                .success(solvedTaskResponse.getSuccess())
                .lives(solvedTaskResponse.getLives())
                .gold(solvedTaskResponse.getGold())
                .score(solvedTaskResponse.getScore())
                .highScore(solvedTaskResponse.getHighScore())
                .turn(solvedTaskResponse.getTurn())
                .message(solvedTaskResponse.getMessage())
                .build();
    }

    @Override
    public List<ShopItem> getShopItems(String gameId) {
        return clientService.getShopItems(gameId).stream()
                .map(item -> ShopItem.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .cost(item.getCost())
                        .build())
                .toList();
    }

    @Override
    public Purchase purchaseItem(String gameId, String itemId) {
        var purchaseResult = clientService.purchaseItem(gameId, itemId);
        return Purchase.builder()
                .shoppingSuccess(purchaseResult.getShoppingSuccess())
                .gold(purchaseResult.getGold())
                .lives(purchaseResult.getLives())
                .level(purchaseResult.getLevel())
                .turn(purchaseResult.getTurn())
                .build();
    }
}
