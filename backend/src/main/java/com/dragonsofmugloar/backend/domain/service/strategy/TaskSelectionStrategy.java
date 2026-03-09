package com.dragonsofmugloar.backend.domain.service.strategy;

import com.dragonsofmugloar.backend.domain.model.Task;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Component
public class TaskSelectionStrategy {

    private static final Map<String, Integer> PROBABILITY_RANK = Map.of(
            "Walk in the park",   1,
            "Piece of cake",      2,
            "Sure thing",         3,
            "Quite likely",       4,
            "Hmmm....",           5,
            "Risky",              6,
            "Rather detrimental", 7,
            "Suicide mission",    8,
            "Impossible",         9
    );

    public static Optional<Task> selectBestTask(List<Task> tasks) {
        return tasks.stream()
                .min(Comparator.comparingInt((Task task) -> getRank(task.getProbability()))
                    .thenComparingInt(task -> -task.getReward()));
    }

    private static Integer getRank(String probability) {
        return PROBABILITY_RANK.getOrDefault(probability, 10);
    }
}
