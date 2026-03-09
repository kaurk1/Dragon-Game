package com.dragonsofmugloar.backend.application;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldReturn400WhenValidationFails() {
        var ex = new ConstraintViolationException("targetScore must be at least 1", Set.of());

        var response = handler.handleConstraintViolationException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(response.getBody()).getStatus()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("targetScore must be at least 1");
    }

    @Test
    void shouldReturn404WhenExternalApiReturnsNotFound() {
        var exception = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found", null, null, null);

        var response = handler.handleHttpStatusCodeException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(Objects.requireNonNull(response.getBody()).getStatus()).isEqualTo(404);
        assertThat(response.getBody().getMessage()).contains("External API error");
    }

    @Test
    void shouldReturn503WhenExternalApiReturnsServiceUnavailable() {
        var ex = HttpServerErrorException.create(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable", null, null, null);

        var response = handler.handleHttpStatusCodeException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(Objects.requireNonNull(response.getBody()).getStatus()).isEqualTo(503);
    }

    @Test
    void shouldReturn500ForGenericException() {
        var response = handler.handleGenericException(new RuntimeException("something went wrong"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(Objects.requireNonNull(response.getBody()).getStatus()).isEqualTo(500);
        assertThat(response.getBody().getMessage()).isEqualTo("An unexpected error occurred");
    }
}
