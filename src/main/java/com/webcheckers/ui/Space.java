package com.webcheckers.ui;

public class Space {
    private int cellIdx;
    private Piece piece;
    private boolean valid;

    public Space(int id, boolean valid) {
        this.cellIdx = id;
        this.piece = null;
        this.valid = valid;
    }

    public int getCellIdx(){
        return this.cellIdx;
    }
    public boolean isValid(){
        return getPiece() == null && valid == true;
    }

    public Piece getPiece(){
        return this.piece;
    }
}
