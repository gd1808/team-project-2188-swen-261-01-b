package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BoardTest {
    private Board CuT;
    private Move move;
    private Move move1;

    private Position p1 = new Position(0, 2);
    private Position p2 = new Position( 1, 1);
    private Position p3 = new Position(2,2);

    @BeforeEach
    public void testSetup(){
        move = new Move(p1, p2);
        move1 = new Move(p1, p3);
        CuT = new Board();
        assertNotNull(CuT);
    }

    @Test
    public void test_isValid(){
        assertTrue(CuT.isValidMove(move));
        assertFalse(CuT.isValidMove(move1));
    }

}
