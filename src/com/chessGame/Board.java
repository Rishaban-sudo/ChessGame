package com.chessGame;

import com.chessGame.pieces.*;
import com.chessGame.utils.ChessBoard;

public class Board {
    public Square[][] box;

    public Board() {
        box = new Square[ChessBoard.ROWS][ChessBoard.COLS];
        this.resetBoard();
    }

    public Square getSquare(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            return box[x][y];
        }
        return null;
    }

    public void resetBoard() {
        //setting black side pieces
        box[0][0] = new Square(new Rook(false), 0, 0);
        box[0][1] = new Square(new Knight(false), 0, 1);
        box[0][2] = new Square(new Bishop(false), 0, 2);

        box[0][3] = new Square(new Queen(false), 0, 3);
        box[0][4] = new Square(new King(false), 0, 4);

        box[0][5] = new Square(new Bishop(false), 0, 5);
        box[0][6] = new Square(new Knight(false), 0, 6);
        box[0][7] = new Square(new Rook(false), 0, 7);

        for (int y = 0; y < ChessBoard.COLS; y++) {
            box[1][y] = new Square(new Pawn(false), 1, y);
        }

        //setting white side pieces
        box[7][0] = new Square(new Rook(true), 7, 0);
        box[7][1] = new Square(new Knight(true), 7, 1);
        box[7][2] = new Square(new Bishop(true), 7, 2);

        box[7][3] = new Square(new Queen(true), 7, 3);
        box[7][4] = new Square(new King(true), 7, 4);

        box[7][5] = new Square(new Bishop(true), 7, 5);
        box[7][6] = new Square(new Knight(true), 7, 6);
        box[7][7] = new Square(new Rook(true), 7, 7);

        for (int y = 0; y < ChessBoard.COLS; y++) {
            box[6][y] = new Square(new Pawn(true), 6, y);
        }

        for (int x = 2; x < ChessBoard.ROWS - 2; x++) {
            for (int y = 0; y < ChessBoard.COLS; y++) {
                box[x][y] = new Square(null, x, y);
            }
        }

    }

    public void displayBoard() {
        for (int x = 0; x < ChessBoard.ROWS; x++) {
            for (int y = 0; y < ChessBoard.COLS; y++) {
                if (box[x][y].getPiece() != null) {
                    System.out.print(box[x][y].getPiece() + "  ");
                } else {
                    System.out.print("_   ");
                }
            }
            System.out.println();
        }
    }

    public void updateBoard(Move move) {
        Square sourceSq = move.getStart();
        Square destinationSq = move.getEnd();

        move.setPieceMoved(sourceSq.getPiece());

        if (destinationSq.getPiece() == null) {
            destinationSq.setPiece(sourceSq.getPiece());
            sourceSq.setPiece(null);
            return;
        }

        destinationSq.getPiece().setKilled(true);
        move.getPlayer().setPoints(destinationSq.getPiece().getPoints());
        move.setPieceKilled(destinationSq.getPiece());
        destinationSq.setPiece(sourceSq.getPiece());
        sourceSq.setPiece(null);
    }
}
