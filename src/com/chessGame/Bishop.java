package com.chessGame;


import java.util.List;

public class Bishop extends Piece{

    Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if(end.getPiece()!=null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x=Math.abs(start.getX()- end.getX());
        int y=Math.abs(start.getY()- end.getY());

        return x==y;
    }

    @Override
    public List<Square> getValidMoves(Board board, Square currentSquare) {
        return null;
    }

    @Override
    public String toString() {
        return "B"+ (  (  this.isWhite() )?"W":"B"  );
    }

}
