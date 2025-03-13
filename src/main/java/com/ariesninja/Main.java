package com.ariesninja;

import com.ariesninja.engine.Game;
import com.ariesninja.engine.utils.Board;
import com.ariesninja.engine.utils.Piece;

public class Main {

    public static void main(String[] args) {

        Board board = new Board(new int[][] {
            {0, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0}
        });

        Piece piece1 = new Piece(new boolean[][] {
            {true, true, true},
            {false, false, true},
            {false, false, true}
        });

        Piece piece2 = new Piece(new boolean[][] {
            {true, true, true},
            {true, false, false}
        });

        Piece piece3 = new Piece(new boolean[][] {
            {true, true},
            {true, true}
        });

        Piece[] pieces = {piece1, piece2, piece3};

        Game game = new Game();

        game.loadGame(board, pieces);

        game.display();

        game.step(new Piece[] {piece2, piece3, piece1});

        game.display();

    }

}