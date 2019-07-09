package com.webcheckers.model;

import com.webcheckers.model.Move;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class MoveTest {
    private Position p1;
    private Position p2;
    private Move CuT;

    private static int row1 = 0;
    private static int row2 = 1;
    private static int cell1 = 2;
    private static int cell2 = 1;

    /**
     * Setup before each test
     */
    @BeforeEach
    public void testSetup(){
        p1 = new Position(row1, cell1);
        p2 = new Position(row2, cell2);
        CuT = new Move(p1,p2);
        assertNotNull(CuT);
    }

    /**
     * Tests to find the starting position of the move
     */
    @Test
    public void test_getStart(){
        assertNotNull(CuT.getStart());
    }

    /**
     * Tests to find the end position of the move
     */
    @Test
    public void test_getEnd(){
        assertNotNull(CuT.getEnd());
    }
}
