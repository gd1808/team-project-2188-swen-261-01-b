package com.webcheckers.ui;

import com.webcheckers.model.Board;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the BoardView class.
 *
 */

@Tag("UI-Tier")
class BoardViewTest {
    //component under test
    private BoardView CuT;

    //friendly objects
    private List<Row> rowList;

    //mock objects
    private Board board;

    /**
     * Setup new object for each test.
     */
    @BeforeEach
    public void setup() {
        rowList = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rowList.add(new Row(i));
        }
        board = mock(Board.class);

        CuT = new BoardView(board);
        assertNotNull(CuT);
    }

    /**
     * Test for the iterator method
     */
    @Test
    void iteratorTest() {
        assertNotNull(CuT.iterator());
    }

    /**
     * Test for the getBoard method by comparing the board's toString methods
     */
    @Test
    void getBoardTest() {
        assertNotNull(CuT.getBoard());
        assertEquals(board.toString(), CuT.getBoard().toString());
    }

    /**
     * Test for the flip method
     */
    @Test
    void flipTest() {

    }
}