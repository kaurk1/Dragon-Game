package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.*;

import java.util.List;

public interface GameService {

    Game startGame();

    Reputation investigateReputation(String gameId);

    List<Task> getAvailableTasks(String gameId);

    TaskResult solveTask(String gameId, String adId);

    List<ShopItem> getShopItems(String gameId);

    PurchaseResult purchaseItem(String gameId, String itemId);
}
