package com.ariesninja.engine.logic;

import com.ariesninja.engine.utils.Board;
import com.ariesninja.engine.utils.Piece;

import java.util.ArrayList;
import java.util.List;

public class Compute {

    public static class MoveStep {
        private int x;
        private int y;
        private int piece; // 0, 1, 2

        public MoveStep(int x, int y, int piece) {
            this.x = x;
            this.y = y;
            this.piece = piece;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPiece() {
            return piece;
        }
    }

    public static MoveStep[] survive(Board board, Piece[] pieces) {
        List<Piece[]> permutations = generatePermutations(pieces);
        for (Piece[] permutation : permutations) {
            List<MoveStep> steps = new ArrayList<>();
            if (tryPlacePieces(board, permutation, 0, steps)) {
                return steps.toArray(new MoveStep[0]);
            }
        }
        return null;
    }

    private static List<Piece[]> generatePermutations(Piece[] pieces) {
        List<Piece[]> permutations = new ArrayList<>();
        permute(pieces, 0, permutations);
        return permutations;
    }

    private static void permute(Piece[] pieces, int start, List<Piece[]> result) {
        if (start == pieces.length) {
            result.add(pieces.clone());
            return;
        }
        for (int i = start; i < pieces.length; i++) {
            swap(pieces, start, i);
            permute(pieces, start + 1, result);
            swap(pieces, start, i);
        }
    }

    private static void swap(Piece[] pieces, int i, int j) {
        Piece temp = pieces[i];
        pieces[i] = pieces[j];
        pieces[j] = temp;
    }

    private static boolean tryPlacePieces(Board board, Piece[] pieces, int index, List<MoveStep> steps) {
        if (index == pieces.length) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.canPlace(pieces[index], i, j)) {
                    Board newBoard = board.place(pieces[index], i, j);
                    steps.add(new MoveStep(i, j, index));

                    if (tryPlacePieces(newBoard, pieces, index + 1, steps)) {
                        return true;
                    }

                    // Backtrack if placement doesn't lead to solution
                    steps.remove(steps.size() - 1);
                }
            }
        }
        return false;
    }

    public static MoveStep[] step(Board board, Piece[] pieces, Evaluate.Method method) {
        switch (method) {
            case GIVE_UP:
//                return giveUp(board, pieces);
            case STREAK:
//                return streak(board, pieces);
            case SURVIVE:
                return survive(board, pieces);
            case RANDOM:
//                return random(board, pieces);
            default:
                return new MoveStep[0];
        }
    }
}