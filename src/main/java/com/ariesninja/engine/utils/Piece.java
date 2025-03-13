package com.ariesninja.engine.utils;

public class Piece {

    private final boolean[][] piece;

    private final int w;
    private final int h;

    public Piece(boolean[][] piece) {

        this.piece = piece;

        // Check that subarrays have the same length
        int wC = piece[0].length;
        for (int i = 1; i < piece.length; i++) {
            if (piece[i].length != wC) {
                throw new IllegalArgumentException("Subarrays must have the same length");
            }
        }

        w = wC;
        h = piece.length;

    }

    public boolean[][] getPiece() {
        return piece;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

}
