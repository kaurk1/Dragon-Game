package com.dragonsofmugloar.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResult {
    private Boolean success;
    private Integer lives;
    private Integer gold;
    private Integer score;
    private Integer highScore;
    private Integer turn;
    private String message;
}
