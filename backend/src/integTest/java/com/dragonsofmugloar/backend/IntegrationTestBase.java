package com.dragonsofmugloar.backend;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;
import org.wiremock.spring.EnableWireMock;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "game.api.base-url=${wiremock.server.baseUrl}"
)
@EnableWireMock
public abstract class IntegrationTestBase {

    @LocalServerPort
    private int port;

    protected RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + port);
    }
}
