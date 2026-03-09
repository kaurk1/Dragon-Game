package com.dragonsofmugloar.backend.domain.service.strategy;


import com.dragonsofmugloar.backend.domain.model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskSelectionStrategyTest {

    @Test
    void shouldTakeLowestRistHighestRewardTask() {
        var result = TaskSelectionStrategy.selectBestTask(getTasks());

        assertThat(result).isPresent();
        assertThat(result.get().getMessage()).isEqualTo("Fight the troll");
        assertThat(result.get().getReward()).isEqualTo(120);
    }

    private static List<Task> getTasks() {
        return List.of(
            Task.builder().adId("a1").message("Help someone").probability("Walk in the park").reward(20).expiresIn(6).build(),
            Task.builder().adId("a2").message("Deliver a package").probability("Piece of cake").reward(33).expiresIn(6).build(),
            Task.builder().adId("a3").message("Guard the gates").probability("Sure thing").reward(45).expiresIn(5).build(),
            Task.builder().adId("a4").message("Find the lost cat").probability("Quite likely").reward(67).expiresIn(4).build(),
            Task.builder().adId("a5").message("Slay the goblin").probability("Hmmm....").reward(80).expiresIn(3).build(),
            Task.builder().adId("a6").message("Rob the merchant").probability("Risky").reward(95).expiresIn(3).build(),
            Task.builder().adId("a7").message("Fight the troll").probability("Walk in the park").reward(120).expiresIn(2).build(),
            Task.builder().adId("a8").message("Attack the castle").probability("Suicide mission").reward(200).expiresIn(1).build(),
            Task.builder().adId("a9").message("Defeat the dragon").probability("Impossible").reward(500).expiresIn(1).build()
        );
    }

}