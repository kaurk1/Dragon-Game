package com.dragonsofmugloar.backend.infrastructure.api;

import com.dragonsofmugloar.backend.infrastructure.api.dto.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface HttpClientService {

    @PostExchange(url = "/game/start", accept = MediaType.APPLICATION_JSON_VALUE)
    StartNewGameResponse startNewGame();

    @PostExchange(url = "/{gameId}/investigate/reputation", accept = MediaType.APPLICATION_JSON_VALUE)
    ReputationResponse investigateReputation(@PathVariable String gameId);

    @GetExchange(url = "/{gameId}/messages", accept = MediaType.APPLICATION_JSON_VALUE)
    List<AvailableTaskResponse> getAvailableTasks(@PathVariable String gameId);

    @PostExchange(url = "/{gameId}/solve/{adId}", accept = MediaType.APPLICATION_JSON_VALUE)
    SolvedTaskResponse solveTask(@PathVariable String gameId,
                                 @PathVariable String adId);

    @GetExchange(url = "/{gameId}/shop", accept = MediaType.APPLICATION_JSON_VALUE)
    List<ShopItemListResponse> getShopItems(@PathVariable String gameId);

    @PostExchange(url = "/{gameId}/shop/buy/{itemId}", accept = MediaType.APPLICATION_JSON_VALUE)
    PurchaseResponse purchaseItem(@PathVariable String gameId,
                                  @PathVariable String itemId);
}
