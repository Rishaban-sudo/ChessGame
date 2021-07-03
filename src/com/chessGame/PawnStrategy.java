package com.chessGame;

import java.util.ArrayList;
import java.util.List;

public class PawnStrategy extends MoveStrategy{

    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();

        List<Square> possibleSquares = new ArrayList<>();
        List<Integer> yPossibles = new ArrayList<>(List.of(y+1,y-1));

        for(Integer yp : yPossibles)
        {
            Square suspectSquare = board.getSquare(x, yp);
            if(suspectSquare!=null && suspectSquare.getPiece()!=null && suspectSquare.getPiece().isWhite()!=currentSquare.getPiece().isWhite())
            {
                possibleSquares.add(suspectSquare);
            }
        }

        return possibleSquares;
    }
}
