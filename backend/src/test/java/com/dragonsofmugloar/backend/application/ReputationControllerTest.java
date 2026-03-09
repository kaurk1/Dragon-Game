package com.dragonsofmugloar.backend.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReputationControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private ReputationController reputationController;

    @Test
    void shouldInvestigateReputation() {
        reputationController.investigateReputation("game-1");
        verify(gameService).investigateReputation("game-1");
    }
}
