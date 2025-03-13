package com.ariesninja.engine.logic;

public class Math {

    public static boolean doesPieceFitAt(int[][] board, boolean[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j]) {
                    if (row + i >= board.length || col + j >= board[row].length) {
                        return false;
                    }
                    if (board[row + i][col + j] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
