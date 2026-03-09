package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Reputation;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Validated
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class ReputationController {

    private final GameService gameService;

    @PostMapping("/{gameId}/reputation")
    public Reputation investigateReputation(@PathVariable @NotBlank(message = "gameId must not be blank") String gameId) {
        return gameService.investigateReputation(gameId);
    }
}
