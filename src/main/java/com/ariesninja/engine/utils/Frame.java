package com.ariesninja.engine.utils;

public class Frame {

    private Board board;
    private Piece[] pieces;

    public Frame(Board board, Piece[] pieces) {
        this.board = board;
        this.pieces = pieces;

        if (pieces.length != 3) {
            throw new IllegalArgumentException("Frame must have 3 pieces");
        }
    }

    public Board getBoard() {
        return board;
    }

    public Piece[] getPieces() {
        return pieces;
    }

}
