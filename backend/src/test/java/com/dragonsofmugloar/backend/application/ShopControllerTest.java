package com.dragonsofmugloar.backend.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShopControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private ShopController shopController;

    @Test
    void shouldGetShopItems() {
        shopController.getShopItems("game-1");
        verify(gameService).getShopItems("game-1");
    }

    @Test
    void shouldPurchaseItem() {
        shopController.purchaseItem("game-1", "hpot");
        verify(gameService).purchaseItem("game-1", "hpot");
    }
}
