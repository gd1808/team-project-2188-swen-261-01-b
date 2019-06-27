package com.webcheckers.ui;

import com.webcheckers.ui.Piece;

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
        if(getPiece() == null && valid == true) {
            return true;
        }else{
            return false;
        }
    }

    public Piece getPiece(){
        return this.piece;
    }
}
