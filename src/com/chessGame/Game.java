package com.chessGame;

import java.util.*;

public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private boolean isCheckmate = false;
    private final LinkedList<Move> listOfMoves;

    public Game(Player p1, Player p2, Board board) {
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.listOfMoves = new LinkedList<>();
        this.board = board;


        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }
    }

    public boolean isCheckmate() {
        return isCheckmate;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public LinkedList<Move> getListOfMoves() {
        return listOfMoves;
    }

    private boolean getCollisions(Set<Square> squares) {

        HashSet<Square> setOfAllSquares = new HashSet<>();

        //if it is white's turn then look for black pieces position and their possible moves
        if (currentTurn.isWhiteSide()) {

            //to find all black Pieces and to get their list of possible moves
            for (int x = 0; x < ChessBoard.ROWS; x++) {
                for (int y = 0; y < ChessBoard.COLS; y++) {

                    Square testSq = board.getSquare(x, y);
                    if (testSq != null && testSq.getPiece() != null && !testSq.getPiece().isWhite()) {
                        List<Square> ls = testSq.getPiece().getValidMoves(board, testSq);

                        if (ls != null)
                            setOfAllSquares.addAll(ls);

                        if (setOfAllSquares.containsAll(squares)) {
                            return true;
                        }
                    }

                }

            }

        }
        //if it is black's turn then look for white pieces position and their possible moves
        else {
            //to find all white Pieces and to get their list of possible moves
            for (int x = 0; x < ChessBoard.ROWS; x++) {
                for (int y = 0; y < ChessBoard.COLS; y++) {

                    Square testSq = board.getSquare(x, y);
                    if (testSq != null && testSq.getPiece() != null && testSq.getPiece().isWhite()) {
                        List<Square> ls = testSq.getPiece().getValidMoves(board, testSq);

                        if (ls != null)
                            setOfAllSquares.addAll(ls);

                        if (setOfAllSquares.containsAll(squares)) {
                            return true;
                        }
                    }

                }

            }
        }

        return false;
    }

    private Square getWhiteKingSq() {
        //get location of the white King
        for (int x = 0; x < ChessBoard.ROWS; x++) {
            for (int y = 0; y < ChessBoard.COLS; y++) {

                if (board.getSquare(x, y).getPiece() != null && board.getSquare(x, y).getPiece().isWhite()
                        && board.getSquare(x, y).getPiece() instanceof King) {
                    return board.getSquare(x, y);
                }
            }
        }
        return null;
    }

    private Square getBlackKingSq() {
        //get location of the black King
        for (int x = 0; x < ChessBoard.ROWS; x++) {
            for (int y = 0; y < ChessBoard.COLS; y++) {

                if (board.getSquare(x, y).getPiece() != null && !board.getSquare(x, y).getPiece().isWhite()
                        && board.getSquare(x, y).getPiece() instanceof King) {
                    return board.getSquare(x, y);
                }
            }
        }

        return null;
    }

    private boolean isKingInCheck() {
        Set<Square> kingSingletonSet = new HashSet<>();
        kingSingletonSet.add(currentTurn.isWhiteSide() ? getWhiteKingSq() : getBlackKingSq());
        return getCollisions(kingSingletonSet);
    }

    private boolean checkMate() {
        Set<Square> possibleKingSqrs = new HashSet<>();
        Square kingSq = (currentTurn.isWhiteSide()) ? getWhiteKingSq() : getBlackKingSq();
        assert kingSq != null;
        possibleKingSqrs.addAll(kingSq.getPiece().getValidMoves(board, kingSq));
        return getCollisions(possibleKingSqrs);
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Square startSq = board.getSquare(startX, startY);
        Square endSq = board.getSquare(endX, endY);

        if (startSq == null || endSq == null) {
            System.out.println("Not a valid move !!!");
            return false;
        }


        if (isKingInCheck()) {
            System.out.println(currentTurn.getName() + " 's King is in check !!! ");
            if (checkMate()) {
                isCheckmate = true;
                System.out.println(currentTurn.isWhiteSide() ? "Black Wins !!" : "White wins !!");
                return false;
            }
        }

        Move move = new Move(player, startSq, endSq);

        return makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            return false;
        }

        if (!player.equals(currentTurn)) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!sourcePiece.isValidMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        board.updateBoard(move);

        listOfMoves.add(move);

        if (this.currentTurn.equals(players[0])) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }
}
