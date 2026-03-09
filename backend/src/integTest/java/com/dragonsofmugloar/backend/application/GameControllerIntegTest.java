package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.IntegrationTestBase;
import com.dragonsofmugloar.backend.domain.model.Game;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameControllerIntegTest extends IntegrationTestBase {

    @Test
    void startGame_returnsGameWithCorrectFields() {
        stubFor(WireMock.post(urlEqualTo("/game/start"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                    "gameId": "VXLcwh8e",
                                    "lives": 3,
                                    "gold": 0,
                                    "level": 0,
                                    "score": 0,
                                    "highScore": 0,
                                    "turn": 0
                                }
                                """)));

        Game result = restClient.post()
                .uri("/api/game/start")
                .retrieve()
                .body(Game.class);

        assertThat(result).isNotNull();
        assertThat(result.getGameId()).isEqualTo("VXLcwh8e");
        assertThat(result.getLives()).isEqualTo(3);
    }
}
