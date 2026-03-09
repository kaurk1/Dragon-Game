package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Reputation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class ReputationController {

    private final GameService gameService;

    @PostMapping("/{gameId}/reputation")
    public Reputation investigateReputation(@PathVariable String gameId) {
        var result = gameService.investigateReputation(gameId);
        log.info("Reputation for game {}: {}", gameId, result);
        return result;
    }
}
