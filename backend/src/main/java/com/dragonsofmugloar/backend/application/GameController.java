package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public Game startGame() {
        var result = gameService.startGame();
        log.info("New game started: " + result);
        return result;
    }
}
