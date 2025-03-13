package com.ariesninja.engine.logic;

import com.ariesninja.engine.utils.Board;
import com.ariesninja.engine.utils.Piece;

import java.util.ArrayList;
import java.util.List;

public class Compute {

    private static Board survive(Board board, Piece[] pieces) {
        List<Piece[]> permutations = generatePermutations(pieces);
        for (Piece[] permutation : permutations) {
            Board result = placePieces(board, permutation, 0);
            if (result != null) {
                return result;
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

    private static Board placePieces(Board board, Piece[] pieces, int index) {
        if (index == pieces.length) {
            return board;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.canPlace(pieces[index], i, j)) {
                    Board newBoard = board.place(pieces[index], i, j);
                    Board result = placePieces(newBoard, pieces, index + 1);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }

    public static Board step(Board board, Piece[] pieces, Evaluate.Method method) {
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
                return board;
        }
    }

}
