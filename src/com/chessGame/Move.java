package com.chessGame;

public class Move {
    private Player player;
    private Square start;
    private Square end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Player getPlayer() {
        return player;
    }

    public Move(Player player, Square start, Square end) {
        this.player = player;
        this.start = start;
        this.end = end;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }

    public void setPieceMoved(Piece pieceMoved) {
        this.pieceMoved = pieceMoved;
    }

    public void setPieceKilled(Piece pieceKilled) {
        this.pieceKilled = pieceKilled;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", start=" + start +
                ", end=" + end +
                ", pieceMoved=" + pieceMoved +
                ", pieceKilled=" + pieceKilled +
                '}';
    }
}
