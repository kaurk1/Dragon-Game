package com.dragonsofmugloar.backend.domain.service;

import com.dragonsofmugloar.backend.domain.model.*;

import java.util.List;

public interface GameRestService {

    Game startNewGame();

    Reputation investigateReputation(String gameId);

    List<Task> getAvailableTasks(String gameId);

    TaskResult solveTask(String gameId, String adId);

    List<ShopItem> getShopItems(String gameId);

    Purchase purchaseItem(String gameId, String itemId);
}
