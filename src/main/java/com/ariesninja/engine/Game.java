package com.ariesninja.engine;

import com.ariesninja.engine.logic.Compute;
import com.ariesninja.engine.utils.Board;
import com.ariesninja.engine.utils.Frame;
import com.ariesninja.engine.utils.Piece;

import com.ariesninja.engine.logic.Evaluate;
import com.ariesninja.engine.logic.Evaluate.Method;

public class Game {

    private Frame currentFrame;

    public Game() {

    }

    public void loadGame(Board board, Piece[] pieces) {
        currentFrame = new Frame(board, pieces);
    }

    public void step(Piece[] pieces) {
        Method method = Evaluate.checkFrame(currentFrame);
//        currentFrame = new Frame(Compute.step(currentFrame.getBoard(), currentFrame.getPieces(), method), pieces);
    }

    public void display() {

        System.out.println("----- BOARD ----");

        // Print the board as "  " and "██"
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(currentFrame.getBoard().get(i, j) == 0 ? "  " : "██");
            }
            System.out.println();
        }

        System.out.println("---- PIECES ----");

        // Print the pieces as "  " and "██"
        for (Piece piece : currentFrame.getPieces()) {
            for (int i = 0; i < piece.getHeight(); i++) {
                for (int j = 0; j < piece.getWidth(); j++) {
                    System.out.print(piece.getPiece()[i][j] ? "██" : "  ");
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("----------------");
    }

}
