package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Purchase;
import com.dragonsofmugloar.backend.domain.model.ShopItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class ShopController {

    private final GameService gameService;

    @GetMapping("/{gameId}/shop")
    public List<ShopItem> getShopItems(@PathVariable String gameId) {
        return gameService.getShopItems(gameId);
    }

    @PostMapping("/{gameId}/shop/buy/{itemId}")
    public Purchase purchaseItem(@PathVariable String gameId, @PathVariable String itemId) {
        return gameService.purchaseItem(gameId, itemId);
    }
}
