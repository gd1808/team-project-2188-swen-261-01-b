package com.webcheckers.ui;

public class Space {
    private int cellIdx;
    private Piece piece;
    private boolean isValid;

    public Space(int id, boolean valid) {
        this.cellIdx = id;
        this.piece = null;
        this.isValid = valid;
    }

    public int getCellIdx(){
        return this.cellIdx;
    }
    public boolean isValid(){
        return this.isValid;
    }
    public Piece getPiece(){
        return this.piece;
    }
}
