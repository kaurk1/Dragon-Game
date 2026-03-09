package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.IntegrationTestBase;
import com.dragonsofmugloar.backend.domain.model.Reputation;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReputationControllerIntegTest extends IntegrationTestBase {

    @Test
    void investigateReputation_returnsReputationWithCorrectFields() {
        stubFor(WireMock.post(urlEqualTo("/VXLcwh8e/investigate/reputation"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""
                                {
                                    "people": 1,
                                    "state": 2,
                                    "underworld": 3
                                }
                                """)));

        Reputation result = restClient.post()
                .uri("/api/game/VXLcwh8e/reputation")
                .retrieve()
                .body(Reputation.class);

        assertThat(result).isNotNull();
        assertThat(result.getPeople()).isEqualTo(1);
        assertThat(result.getState()).isEqualTo(2);
        assertThat(result.getUnderworld()).isEqualTo(3);
    }
}
