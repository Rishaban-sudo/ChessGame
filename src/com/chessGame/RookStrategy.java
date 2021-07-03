package com.chessGame;

import java.util.ArrayList;
import java.util.List;


public class RookStrategy extends MoveStrategy {


    private void dfsRec(Board board, int x, int y, List<Square> possibleSquares, Direction direction, Square forSquare) {

        Square sq = board.getSquare(x, y);

        if (sq == null || (sq.getPiece() != null && sq.getPiece().isWhite() == forSquare.getPiece().isWhite())) {
            return;
        } else {
            possibleSquares.add(sq);
        }


        switch (direction) {
            case UP -> dfsRec(board, x - 1, y, possibleSquares, Direction.UP, forSquare);
            case LEFT -> dfsRec(board, x, y - 1, possibleSquares, Direction.LEFT, forSquare);
            case RIGHT -> dfsRec(board, x, y + 1, possibleSquares, Direction.RIGHT, forSquare);
            case DOWN -> dfsRec(board, x + 1, y, possibleSquares, Direction.DOWN, forSquare);
        }

    }

    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        int row = currentSquare.getX();
        int col = currentSquare.getY();

        List<Square> possibleSquares = new ArrayList<>();
        dfsRec(board, row - 1, col, possibleSquares, Direction.UP, currentSquare);
        dfsRec(board, row, col + 1, possibleSquares, Direction.RIGHT, currentSquare);
        dfsRec(board, row, col - 1, possibleSquares, Direction.LEFT, currentSquare);
        dfsRec(board, row + 1, col, possibleSquares, Direction.DOWN, currentSquare);

        return possibleSquares;
    }
}
