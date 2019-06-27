package com.webcheckers.ui;

public class Piece {

    public enum Type {
        SINGLE, KING
    }
    private Type type;

    public enum Color {
        RED, WHITE
    }
    private Color color;

    public Piece(Color color) {
        this.type = Type.SINGLE;
        this.color = color;
    }

    public Type getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
    }
}
