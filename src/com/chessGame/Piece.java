package com.chessGame;

//denotes a chess Piece such as Pawn,Rook,Knight...

import java.util.List;

public abstract class Piece {
    private boolean isKilled=false; //status of the piece whether it is alive or not
    private boolean isWhite;        //color of the piece (white/black)

    Piece(){}

    Piece(boolean isWhite) {
        this.isWhite=isWhite;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public abstract boolean isValidMove(Board board,Square start,Square end);

    public abstract int getPoints();

    public abstract List<Square> getValidMoves(Board board, Square currentSquare);

}
