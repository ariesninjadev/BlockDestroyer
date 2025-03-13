package com.ariesninja.engine.utils;

import com.ariesninja.engine.logic.Math;

public class Board {

    private final int[][] board;

    public Board(int[][] board) {
        this.board = board;

        if (board.length != 8) {
            throw new IllegalArgumentException("Board must have 8 rows");
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i].length != 8) {
                throw new IllegalArgumentException("Board must have 8 columns");
            }
        }
    }

    public boolean canPlace(Piece piece, int x, int y) {
        return Math.doesPieceFitAt(board, piece.getPiece(), x, y);
    }

    public Board place(Piece piece, int x, int y) {
        int[][] newBoard = new int[8][8];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                if (piece.getPiece()[i][j]) {
                    newBoard[x + i][y + j] = 1;
                }
            }
        }
        // If any row or column is full, clear it
        for (int i = 0; i < 8; i++) {
            boolean rowFull = true;
            boolean colFull = true;
            for (int j = 0; j < 8; j++) {
                if (newBoard[i][j] == 0) {
                    rowFull = false;
                }
                if (newBoard[j][i] == 0) {
                    colFull = false;
                }
            }
            if (rowFull) {
                for (int j = 0; j < 8; j++) {
                    newBoard[i][j] = 0;
                }
            }
            if (colFull) {
                for (int j = 0; j < 8; j++) {
                    newBoard[j][i] = 0;
                }
            }
        }
        return new Board(newBoard);
    }

    public int get(int x, int y) {
        return board[x][y];
    }

}
