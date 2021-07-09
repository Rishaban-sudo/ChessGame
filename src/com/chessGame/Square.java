package com.chessGame;

import com.chessGame.pieces.Piece;

import java.util.Objects;

public class Square {
    private Piece piece;
    private int x;
    private int y;

    public Square(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.getX() && y == square.getY() && Objects.equals(piece, square.getPiece());
    }

    @Override
    public int hashCode() {
        return Objects.hash( x, y);
    }
}
