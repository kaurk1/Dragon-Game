package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.IntegrationTestBase;
import com.dragonsofmugloar.backend.domain.model.PurchaseResult;
import com.dragonsofmugloar.backend.domain.model.ShopItem;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

class ShopControllerIntegTest extends IntegrationTestBase {

    @Test
    void getShopItems_returnsItemList() {
        stubFor(WireMock.get(urlEqualTo("/VXLcwh8e/shop"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                [
                                    { "id": "hpot", "name": "Healing Potion", "cost": 50 },
                                    { "id": "cs",   "name": "Claw Sharpening", "cost": 100 }
                                ]
                                """)));

        ShopItem[] result = restClient.get()
                .uri("/api/game/VXLcwh8e/shop")
                .retrieve()
                .body(ShopItem[].class);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result[0].getId()).isEqualTo("hpot");
        assertThat(result[0].getCost()).isEqualTo(50);
    }
}
