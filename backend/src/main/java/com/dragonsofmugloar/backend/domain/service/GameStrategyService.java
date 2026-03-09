package com.dragonsofmugloar.backend.domain.service;

import com.dragonsofmugloar.backend.domain.model.*;
import com.dragonsofmugloar.backend.domain.model.Character;
import com.dragonsofmugloar.backend.domain.service.strategy.ShoppingStrategy;
import com.dragonsofmugloar.backend.domain.service.strategy.TaskSelectionStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GameStrategyService {

    private final GameRestService gameRestService;

    public Game playGame(Integer targetScore) {
        var game = startNewGame();
        var character = new Character();
        startGameplayLoop(game, character, targetScore);
        log.info("Game over: score={}, reachedTarget={}", game.getScore(), hasReachedTargetScore(game, targetScore));
        return Game.builder()
                .gameId(game.getGameId())
                .score(game.getScore())
                .turn(game.getTurn())
                .lives(game.getLives())
                .gold(game.getGold())
                .reachedTarget(hasReachedTargetScore(game, targetScore))
                .build();
    }

    private Game startNewGame() {
        var game = gameRestService.startNewGame();
        log.info("Game started: {}", game.getGameId());
        return game;
    }

    private void startGameplayLoop(Game game, Character character, Integer targetScore) {
        while (game.getScore() < targetScore) {
            var result = completeTask(game);
            if (result.isEmpty() || characterIsDead(game, result.get())) {
                break;
            }
            updateGameState(game, result.get());
            buyItemIfNeeded(game, character);
        }
    }

    private Optional<TaskResult> completeTask(Game game) {
        var tasks = gameRestService.getAvailableTasks(game.getGameId());
        var selectedTask = TaskSelectionStrategy.selectBestTask(tasks);
        if (selectedTask.isEmpty()) {
            return Optional.empty();
        }
        log.info("Selected task: probability={} reward={}",
                selectedTask.get().getProbability(),
                selectedTask.get().getReward());

        var result = gameRestService.solveTask(game.getGameId(), selectedTask.get().getAdId());
        log.info("Task solved: success={}, score={}, level={}, lives={}, gold={}",
                result.getSuccess(), result.getScore(), game.getLevel(), result.getLives(), result.getGold());

        return Optional.of(result);
    }

    private void buyItemIfNeeded(Game game, Character character) {
        var shopItems = gameRestService.getShopItems(game.getGameId());
        ShoppingStrategy.decideItemToBuy(game, character, shopItems).ifPresent(itemId -> {
            var purchase = gameRestService.purchaseItem(game.getGameId(), itemId);
            if (purchase.getShoppingSuccess()) {
                character.addItem(itemId);
                updateGameState(game, purchase);
                log.info("Purchased item: {}, character items: {}, gold remaining: {}",
                        itemId, character.getItems(), purchase.getGold());
            }
        });
    }

    private Boolean hasReachedTargetScore(Game game, Integer targetScore) {
        return game.getScore() >= targetScore;
    }

    private void updateGameState(Game game, TaskResult result) {
        game.setScore(result.getScore());
        game.setLives(result.getLives());
        game.setGold(result.getGold());
        game.setTurn(result.getTurn());
    }

    private void updateGameState(Game game, Purchase result) {
        game.setLives(result.getLives());
        game.setGold(result.getGold());
        game.setTurn(result.getTurn());
        game.setLevel(result.getLevel());
    }

    private Boolean characterIsDead(Game game, TaskResult result) {
        if (result.getLives() <= 0) {
            updateGameState(game, result);
            return true;
        }
        return false;
    }
}
