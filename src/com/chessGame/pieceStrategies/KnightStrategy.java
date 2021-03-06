package com.chessGame.pieceStrategies;

import com.chessGame.Board;
import com.chessGame.Square;

import java.util.ArrayList;
import java.util.List;

public class KnightStrategy extends MoveStrategy {

    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();

        List<Square> possibleSquares = new ArrayList<>();

        List<Integer> xPossibles = new ArrayList<>(List.of(x + 2, x + 2, x - 2, x - 2, x + 1, x + 1, x - 1, x - 1));
        List<Integer> yPossibles = new ArrayList<>(List.of(y + 1, y - 1, y + 1, y - 1, y + 2, y - 2, y + 2, y - 2));


        for (int i = 0; i < xPossibles.size(); i++) {
            Square squareSuspect = board.getSquare(xPossibles.get(i), yPossibles.get(i));
            if (
                    squareSuspect == null ||
                            (
                                    squareSuspect.getPiece() != null &&
                                            squareSuspect.getPiece().isWhite() == currentSquare.getPiece().isWhite()
                            )
            ) {
                continue;
            }
            possibleSquares.add(squareSuspect);
        }

        return possibleSquares;
    }
}
