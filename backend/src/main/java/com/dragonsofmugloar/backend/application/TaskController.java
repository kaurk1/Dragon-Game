package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Task;
import com.dragonsofmugloar.backend.domain.model.TaskResult;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Validated
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class TaskController {

    private final GameService gameService;

    @GetMapping("/{gameId}/messages")
    public List<Task> getMessages(@PathVariable @NotBlank(message = "gameId must not be blank") String gameId) {
        return gameService.getAvailableTasks(gameId);
    }

    @PostMapping("/{gameId}/solve/{adId}")
    public TaskResult solveTask(
            @PathVariable @NotBlank(message = "gameId must not be blank") String gameId,
            @PathVariable @NotBlank(message = "adId must not be blank") String adId) {
        return gameService.solveTask(gameId, adId);
    }
}
