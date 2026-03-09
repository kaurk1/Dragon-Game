package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public Game startGame() {
        return gameService.startGame();
    }

    @PostMapping("/play")
    public Game playGame(@RequestParam(defaultValue = "5000") Integer targetScore) {
        return gameService.playGame(targetScore);
    }
}
