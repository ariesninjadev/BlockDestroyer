package com.ariesninja.api;

import com.ariesninja.engine.logic.Compute;
import com.ariesninja.engine.utils.Board;
import com.ariesninja.engine.utils.Piece;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class RestAPI {
    public static void main(String[] args) {
        SpringApplication.run(RestAPI.class, args);
    }
}

@RestController
@RequestMapping("/locate")
class LocateController {
    @GetMapping
    public String locate(@RequestParam String board,
                         @RequestParam String piece1,
                         @RequestParam String piece2,
                         @RequestParam String piece3) {

//        return "Received board: " + board + ", piece1: " + piece1 + ", piece2: " + piece2 + ", piece3: " + piece3;

        // Convert the board string to a double int array (board is 8x8)
        if (board.length() != 64) {
            return "Invalid board size";
        }

        int[][] boardInt = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardInt[i][j] = Character.getNumericValue(board.charAt(i * 8 + j));
            }
        }

        // Do the same for the pieces. They will be 5x5
        if (piece1.length() != 25 || piece2.length() != 25 || piece3.length() != 25) {
            return "Invalid piece size";
        }

        boolean[][] piece1Int = new boolean[5][5];
        boolean[][] piece2Int = new boolean[5][5];
        boolean[][] piece3Int = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                piece1Int[i][j] = piece1.charAt(i * 5 + j) == '1';
                piece2Int[i][j] = piece2.charAt(i * 5 + j) == '1';
                piece3Int[i][j] = piece3.charAt(i * 5 + j) == '1';
            }
        }

        Compute.MoveStep[] steps = Compute.survive(
                new Board(boardInt),
                new Piece[] {
                        new Piece(piece1Int),
                        new Piece(piece2Int),
                        new Piece(piece3Int)
                }
        );

        // Stringify data in MoveStep
        StringBuilder result = new StringBuilder();
        if (steps != null) {
            for (Compute.MoveStep step : steps) {
                result.append("Piece ").append(step.getPiece()).append(" at (").append(step.getX()).append(", ").append(step.getY()).append(")\n");
            }
        } else {
            result.append("No solution found");
        }

        return result.toString();

    }
}
