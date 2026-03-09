package com.dragonsofmugloar.backend.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void shouldGetAvailableTasks() {
        taskController.getMessages("game-1");
        verify(gameService).getAvailableTasks("game-1");
    }

    @Test
    void shouldSolveTask() {
        taskController.solveTask("game-1", "ad-1");
        verify(gameService).solveTask("game-1", "ad-1");
    }
}
