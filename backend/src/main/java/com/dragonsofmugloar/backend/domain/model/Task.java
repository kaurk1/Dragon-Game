package com.dragonsofmugloar.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String adId;
    private String message;
    private Integer reward;
    private Integer expiresIn;
    private Integer encrypted;
    private String probability;
}
