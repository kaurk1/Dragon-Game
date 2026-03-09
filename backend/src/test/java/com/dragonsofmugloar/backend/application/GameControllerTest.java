package com.dragonsofmugloar.backend.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void shouldStartNewGame() {
        gameController.startGame();
        verify(gameService).startGame();
    }

    @Test
    void shouldPlayTheGameOnAutoPilot() {
        gameController.playGame(2000);
        verify(gameService).playGame(2000);
    }

}