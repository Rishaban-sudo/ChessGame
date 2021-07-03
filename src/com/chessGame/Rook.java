package com.chessGame;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return (x == 0 && y > 0) || (y == 0 && x > 0);

    }

    private List<MoveStrategy> strategies = new ArrayList<>();

    {
        strategies.add(new RookStrategy());
    }

    public List<Square> getValidMoves(Board board, Square currentSquare) {
        List<Square> allPossibleSquares = new ArrayList<>();


        for (MoveStrategy strategy : strategies) {
            allPossibleSquares.addAll(0, strategy.getValidMoves(board, currentSquare));
        }

        return allPossibleSquares;
    }

    @Override
    public String toString() {
        return "R" + ((this.isWhite()) ? "W" : "B");
    }

}
