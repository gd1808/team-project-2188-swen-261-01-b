package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
class ReplayGameTest {
    private PlayerServices p1;
    private PlayerServices p2;
    private GameCenter gameCenter;
    private Board board;
    private ReplayGame CuT;

    /**
     * Setup new object for each test.
     */
    @BeforeEach
    public void setup() {
        gameCenter = new GameCenter();
        p1 = new PlayerServices("Spongebob", gameCenter);
        p2 = new PlayerServices("Patrick", gameCenter);
        gameCenter.createGame(p1.Id(), p2.Id());
        board = p1.getCurrentGame().getBoard();

        CuT = new ReplayGame(p1, p2, board);
    }

    @Test
    void getAttributes() {
    }

    @Test
    void getPlayerVsPlayer() {
    }

    @Test
    void tryNextReplayMove() {
    }

    @Test
    void addConfiguration() {
    }

    @Test
    void tryPreviousReplayMove() {
    }
}