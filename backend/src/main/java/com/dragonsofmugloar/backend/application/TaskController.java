package com.dragonsofmugloar.backend.application;

import com.dragonsofmugloar.backend.domain.model.Task;
import com.dragonsofmugloar.backend.domain.model.TaskResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class TaskController {

    private final GameService gameService;

    @GetMapping("/{gameId}/messages")
    public List<Task> getMessages(@PathVariable String gameId) {
        var result = gameService.getAvailableTasks(gameId);
        log.info("Retrieved {} messages for game {}", result.size(), gameId);
        return result;
    }

    @PostMapping("/{gameId}/solve/{adId}")
    public TaskResult solveTask(@PathVariable String gameId, @PathVariable String adId) {
        var result = gameService.solveTask(gameId, adId);
        log.info("Solved task {} for game {}: success={}", adId, gameId, result.getSuccess());
        return result;
    }
}
