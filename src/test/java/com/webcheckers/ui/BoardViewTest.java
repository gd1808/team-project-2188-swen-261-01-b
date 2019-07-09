package com.webcheckers.ui;

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

    /**
     * Setup new object for each test.
     */
    @BeforeEach
    public void setup() {
        rowList = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rowList.add(new Row(i));
        }

        CuT = new BoardView(1);
    }

    /**
     * Test for the getPlayerID method
     */
    @Test
    void getPlayerIDTest() {
        assertEquals(1, CuT.getPlayerID());
    }

    /**
     * Test for the iterator method
     */
    @Test
    void iteratorTest() {
        assertNotNull(CuT.iterator());
    }
}