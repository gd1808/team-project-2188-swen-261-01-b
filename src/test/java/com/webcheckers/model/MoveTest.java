package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void testSetup(){
        p1 = new Position(row1, cell1);
        p2 = new Position(row2, cell2);
        CuT = new Move(p1,p2);
        assertNotNull(CuT);
    }

    /**
     * Tests to find the starting position of the move
     */
    @Test
    void test_getStart(){
        assertNotNull(CuT.getStart());
        assertEquals(p1, CuT.getStart());
    }

    /**
     * Tests to find the end position of the move
     */
    @Test
    void test_getEnd(){
        assertNotNull(CuT.getEnd());
        assertEquals(p2, CuT.getEnd());
    }

    /**
     * Tests to see if two Positions are equal/unequal
     */
    @Test
    void test_equals() {
        Move m1 = new Move(new Position(0, 2), new Position(1, 1));
        Move m2 = new Move(new Position(7, 2), new Position(6, 1));

        assertTrue(CuT.equals(m1));
        assertFalse(CuT.equals(m2));
        assertFalse(CuT.equals(p1));
    }

    /**
     * Tests to get where does a Position hash to
     */
    @Test
    void test_hashCode() {
        Move m1 = new Move(new Position(0, 2), new Position(1, 1));
        Move m2 = new Move(new Position(5, 0), new Position(4, 1));


        assertEquals(m1.hashCode(), CuT.hashCode());
        assertNotEquals(m2.hashCode(), CuT.hashCode());
    }
}
