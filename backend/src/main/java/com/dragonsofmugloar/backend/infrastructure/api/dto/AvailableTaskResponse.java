package com.dragonsofmugloar.backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTaskResponse {
    private String adId;
    private String message;
    private Integer reward;
    private Integer expiresIn;
    private Integer encrypted;
    private String probability;
}
