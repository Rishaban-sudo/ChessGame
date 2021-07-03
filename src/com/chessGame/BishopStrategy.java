package com.chessGame;

import java.util.ArrayList;
import java.util.List;

public class BishopStrategy extends MoveStrategy{


    public void diagonalSearch(Board board,int x,int y,List<Square> possibleSquares,Direction direction,Square forSquare) {
        Square sq = board.getSquare(x, y);

        if (sq == null || (sq.getPiece() != null && sq.getPiece().isWhite() == forSquare.getPiece().isWhite())) {
            return;
        }
        else {
            possibleSquares.add(sq);
        }

        switch (direction) {
            case TOP_RIGHT ->   diagonalSearch(board, x - 1, y + 1, possibleSquares, Direction.TOP_RIGHT, forSquare);
            case TOP_LEFT -> diagonalSearch(board, x - 1, y - 1, possibleSquares, Direction.TOP_LEFT, forSquare);
            case BOTTOM_RIGHT ->diagonalSearch(board, x + 1, y + 1, possibleSquares, Direction.BOTTOM_RIGHT, forSquare);
            case BOTTOM_LEFT -> diagonalSearch(board, x + 1, y - 1, possibleSquares, Direction.BOTTOM_LEFT, forSquare);
        }

    }

    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();

        List<Square> possibleSquares = new ArrayList<>();

        diagonalSearch(board, x - 1, y + 1, possibleSquares, Direction.TOP_RIGHT, currentSquare);
        diagonalSearch(board, x - 1, y - 1, possibleSquares, Direction.TOP_LEFT, currentSquare);
        diagonalSearch(board, x + 1, y + 1, possibleSquares, Direction.BOTTOM_RIGHT, currentSquare);
        diagonalSearch(board, x + 1, y - 1, possibleSquares, Direction.BOTTOM_LEFT, currentSquare);

        return possibleSquares;
    }
}
