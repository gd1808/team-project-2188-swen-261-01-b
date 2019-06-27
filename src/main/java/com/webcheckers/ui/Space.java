package com.webcheckers.ui;

public class Space {
    private int cellIdx;
    private Piece piece;

    public Space(int id){
        this.cellIdx = id;
        this.piece = null;
    }

    public boolean hasPiece(){
        return this.piece != null;
    }
    public int getCellIdx(){
        return this.cellIdx;
    }
    public boolean isValid(){
        return false;
    }
    public Piece getPiece(){
        return this.Piece;
    }
}
