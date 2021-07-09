package com.chessGame.pieces;

import com.chessGame.*;
import com.chessGame.pieceStrategies.BishopStrategy;
import com.chessGame.pieceStrategies.MoveStrategy;
import com.chessGame.pieceStrategies.RookStrategy;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private int points = 9;
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if(end.getPiece()!=null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x=Math.abs(start.getX()- end.getX());
        int y=Math.abs(start.getY()- end.getY());

        return (x==y) || (x==0 && y>0) || (y==0 && x>0);
    }

    @Override
    public int getPoints() {
        return points;
    }

    private List<MoveStrategy> strategies = new ArrayList<>();

    {
        strategies.add(new RookStrategy());
        strategies.add(new BishopStrategy());
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
        return "Q"+ (  (  this.isWhite() )?"W":"B"  );
    }


}
