package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class PieceTest {
    private Piece CuT;

    @BeforeEach
    public void testSetup(){
        CuT = new Piece(Piece.Color.WHITE);
        assertNotNull(CuT);
    }

    @Test
    public void test_getType(){
        assertEquals(Piece.Type.SINGLE, CuT.getType());
    }

    @Test
    public void test_getColor(){
        assertEquals(Piece.Color.WHITE, CuT.getColor());
    }
}
