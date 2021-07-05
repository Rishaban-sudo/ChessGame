package com.chessGame;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private int points = 1;
    private boolean initialMove=true;

    Pawn(boolean isWhite) {
        super(isWhite);
    }



    @Override
    public boolean isValidMove(Board board,Square start,Square end) {
        if(end.getPiece()!=null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x=Math.abs(start.getX()- end.getX());
        int y=Math.abs(start.getY()- end.getY());

        //Pawn 1 step forward
        if(x==1 && y==0) {
            return true;
        }
        //Pawn 2 step forward
        else if(this.initialMove && x==2 && y==0 ) {
            this.initialMove=false;
            return true;
        }
        else return x == 1 && y == 1;   //Pawn diagonal movement when killing other Piece
    }

    @Override
    public int getPoints() {
        return points;
    }

    private List<MoveStrategy> strategies = new ArrayList<>();

    {
        strategies.add(new PawnStrategy());
    }


    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        List<Square> allPossibleSquares = new ArrayList<>();


        for (MoveStrategy strategy : strategies) {
            allPossibleSquares.addAll(0, strategy.getValidMoves(board, currentSquare));
        }

        return allPossibleSquares;
    }

    @Override
    public String toString() {
        return "P"+ (  (  this.isWhite() )?"W":"B"  );
    }
}
