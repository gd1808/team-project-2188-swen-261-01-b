package com.webcheckers.model;

import com.webcheckers.appl.PlayerServices;
import com.webcheckers.ui.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * unit tests for the Board class
 */
@Tag("Model-tier")
public class BoardTest {
    private Board CuT;
    private Square[][] board;
    private Move move;
    private Move move1;

    private Position p1 = new Position(0, 2);
    private Position p2 = new Position( 1, 1);
    private Position p3 = new Position(2,2);

    /**
     * Set up before each test
     */
    @BeforeEach
    public void testSetup(){
        move = new Move(p1, p2);
        move1 = new Move(p1, p3);
        CuT = new Board();
        assertNotNull(CuT);

        //setting up for board comparison
        this.board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.WHITE);
                    } else {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    }
                } else {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    } else {
                        board[row][col] = new Square(Square.Color.WHITE);
                    }
                }
            }
        }
    }

    /**
     * tests the isValid method
     */
    @Test
    public void test_isValid(){
        assertTrue(CuT.isValidMove(move));
        assertFalse(CuT.isValidMove(move1));
    }

    @Test
    void getActiveColorTest() {
        assertNotNull(CuT.getActiveColor());
        assertEquals(Piece.Color.RED, CuT.getActiveColor());
    }

    @Test
    void changeActiveColorTest() {
        CuT.changeActiveColor(Piece.Color.WHITE);
        assertEquals(Piece.Color.WHITE, CuT.getActiveColor());
    }

    @Test
    void getWhitePiecesTest() {
        assertEquals(12, CuT.getWhitePieces());
    }

    @Test
    void getRedPiecesTest() {
        assertEquals(12, CuT.getRedPieces());
    }

    @Test
    void getActualMove() {
        Move result = CuT.getActualMove(move);
        assertNotNull(result);
        assertTrue(CuT.isValidMove(result));
    }

    @Test
    void backUpMoveTest() {
        assertNotNull(CuT.backUpMove());

        assertEquals("moveList is empty, cannot backup the move.", CuT.backUpMove());
        CuT.isValidMove(move);
        assertEquals("true", CuT.backUpMove());
    }

    @Test
    void getBoardTest() {
        assertNotNull(CuT.getBoard());

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                assertEquals(board[row][col].getColor(), CuT.getBoard()[row][col].getColor());
            }
        }
    }

    @Test
    void toStringTest() {
        assertNotNull(CuT.toString());

        StringBuilder test = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == Square.Color.BLACK) {
                    if (board[i][j].getPiece() != null) {
                        test.append("[(B)]");
                    } else {
                        test.append("[B]");
                    }
                } else {
                    test.append("[W]");
                }
            }
            test.append("\n");
        }

        assertEquals(test.toString(), CuT.toString());
    }

    @Test
    void checkMakeKing() {
        CuT.getBoard()[0][0].addPiece(Piece.Color.RED);
        assertEquals(Piece.Type.SINGLE, CuT.getBoard()[0][0].getPiece().getType());
        CuT.checkMakeKing();
        assertEquals(Piece.Type.KING, CuT.getBoard()[0][0].getPiece().getType());
    }

    @Test
    void capturePiecesTest() {
        CuT.isValidMove(move);
        CuT.capturePieces();

        //check that no pieces were captured
        assertEquals(12, CuT.getWhitePieces());
        assertEquals(12, CuT.getRedPieces());
    }

    @Test
    void capturePieceTest() {
        CuT.capturePiece(0,0);
        assertNull(CuT.getBoard()[0][0].getPiece());
        assertEquals(11, CuT.getWhitePieces());

        CuT.capturePiece(0,2);
        assertEquals(10, CuT.getWhitePieces());
    }

    @Test
    void teamIsEliminatedTest() {
        assertFalse(CuT.teamIsEliminated());
    }

    @Test
    void resetMovesTest() {
        PlayerServices player = mock(PlayerServices.class);

        assertFalse(CuT.resetMoves(player));
        CuT.changeActiveColor(Piece.Color.WHITE);
        assertTrue(CuT.resetMoves(player));
    }

    @Test
    void trySubmitTurnTest() {
        assertNotNull(CuT.trySubmitTurn());

        //will change test when changes to this function are made
        assertEquals("No moves!", CuT.trySubmitTurn());
        CuT.isValidMove(move);
        assertEquals("true", CuT.trySubmitTurn());
    }

}
