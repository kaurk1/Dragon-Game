package com.dragonsofmugloar.backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolvedTaskResponse {
    private Boolean success;
    private Integer lives;
    private Integer gold;
    private Integer score;
    private Integer highScore;
    private Integer turn;
    private String message;
}
