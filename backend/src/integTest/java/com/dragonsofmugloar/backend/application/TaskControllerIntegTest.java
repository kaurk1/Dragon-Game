package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.IntegrationTestBase;
import com.dragonsofmugloar.backend.domain.model.Task;
import com.dragonsofmugloar.backend.domain.model.TaskResult;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

class TaskControllerIntegTest extends IntegrationTestBase {

    @Test
    void getMessages_returnsTaskList() {
        stubFor(WireMock.get(urlEqualTo("/VXLcwh8e/messages"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                [
                                    {
                                        "adId": "Rv9v1rGu",
                                        "message": "Help someone",
                                        "reward": 33,
                                        "expiresIn": 6,
                                        "encrypted": null,
                                        "probability": "Quite likely"
                                    }
                                ]
                                """)));

        Task[] result = restClient.get()
                .uri("/api/game/VXLcwh8e/messages")
                .retrieve()
                .body(Task[].class);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result[0].getAdId()).isEqualTo("Rv9v1rGu");
        assertThat(result[0].getReward()).isEqualTo(33);
        assertThat(result[0].getProbability()).isEqualTo("Quite likely");
    }

    @Test
    void solveTask_returnsTaskResult() {
        stubFor(WireMock.post(urlEqualTo("/VXLcwh8e/solve/Rv9v1rGu"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                    "success": true,
                                    "lives": 3,
                                    "gold": 67,
                                    "score": 67,
                                    "highScore": 67,
                                    "turn": 2,
                                    "message": "You successfully solved the mission!"
                                }
                                """)));

        TaskResult result = restClient.post()
                .uri("/api/game/VXLcwh8e/solve/Rv9v1rGu")
                .retrieve()
                .body(TaskResult.class);

        assertThat(result).isNotNull();
        assertThat(result.getSuccess()).isTrue();
        assertThat(result.getGold()).isEqualTo(67);
        assertThat(result.getMessage()).isEqualTo("You successfully solved the mission!");
    }
}
