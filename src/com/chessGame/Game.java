package com.chessGame;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private boolean isCheck = false;
    private boolean isCheckmate = false;
    private final LinkedList<Move> listOfMoves;

    public Game(Player p1, Player p2,Board board) {
        this.players = new Player[2];
        this.players[0]=p1;
        this.players[1]=p2;
        this.listOfMoves=new LinkedList<>();
        this.board=board;


        if(p1.isWhiteSide())
        {
            this.currentTurn=p1;
        }
        else
        {
            this.currentTurn=p2;
        }
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public LinkedList<Move> getListOfMoves() {
        return listOfMoves;
    }

    private void isKingInCheck() {

        HashSet<Square> setOfAllSquares = new HashSet<>();

        //if it is white's turn then look for black pieces position and their possible moves
        // and check whether white King is threatened by black pieces
        if(currentTurn.isWhiteSide()) {

            Square whiteKingSq = null;
            //get location of the white King
            outerLoop:
            for(int x=0;x<ChessBoard.rows;x++) {
                for(int y=0;y<ChessBoard.cols;y++) {

                    if(board.getSquare(x, y).getPiece()!=null && board.getSquare(x, y).getPiece().isWhite()
                            && board.getSquare(x, y).getPiece() instanceof King) {
                        whiteKingSq = board.getSquare(x, y);
                        break outerLoop;
                    }
                }
            }

            //to find all black Pieces and to get their list of possible moves
            for(int x=0;x<ChessBoard.rows;x++) {
                for(int y=0;y<ChessBoard.cols;y++) {

                    Square testSq = board.getSquare(x, y);
                    if(testSq != null && testSq.getPiece()!=null && !testSq.getPiece().isWhite()) {
                        List<Square> ls =  testSq.getPiece().getValidMoves(board, testSq);

                        if(ls!=null)
                            setOfAllSquares.addAll(ls);

                        if(setOfAllSquares.contains(whiteKingSq)){
                            isCheck = true;
                            return;
                        }
                    }

                }

            }

        }
        //if it is black's turn then look for white pieces position and their possible moves
        // and check whether black King is threatened by white pieces
        else {
            Square blackKingSq = null;

            //get location of the black King
            outerLoop:
            for(int x=0;x<ChessBoard.rows;x++) {
                for(int y=0;y<ChessBoard.cols;y++) {

                    if(board.getSquare(x, y).getPiece()!=null && !board.getSquare(x, y).getPiece().isWhite()
                            && board.getSquare(x, y).getPiece() instanceof King) {
                        blackKingSq = board.getSquare(x, y);
                        break outerLoop;
                    }
                }
            }

            //to find all white Pieces and to get their list of possible moves
            //and see if any move matches with king sq
            for(int x=0;x<ChessBoard.rows;x++) {
                for(int y=0;y<ChessBoard.cols;y++) {

                    Square testSq = board.getSquare(x, y);
                    if(testSq != null &&  testSq.getPiece()!=null && testSq.getPiece().isWhite()) {
                        List<Square> ls =  testSq.getPiece().getValidMoves(board, testSq);

                        if(ls!=null)
                            setOfAllSquares.addAll(ls);

                        if(setOfAllSquares.contains(blackKingSq)) {
                            isCheck = true;
                            return;
                        }
                    }

                }

            }
        }


    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Square startSq = board.getSquare(startX, startY);
        Square endSq   = board.getSquare(endX, endY);

        if(startSq==null || endSq==null)
        {
            System.out.println("Not a valid move !!!");
            return false;
        }

        isKingInCheck(); //if king in check sets the isCheck flag to true

        if(isCheck) {
            System.out.println(currentTurn.getName()+" 's King is in check !!! ");
            return false;
        }

        Move move = new Move(player, startSq, endSq);

        return makeMove(move, player);
    }

    private boolean makeMove(Move move,Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        if(sourcePiece ==null) {
            return false;
        }

        if(!player.equals(currentTurn)) {
            return false;
        }

        if(sourcePiece.isWhite()!=player.isWhiteSide()) {
            return false;
        }

        if(!sourcePiece.isValidMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        board.updateBoard(move);

        listOfMoves.add(move);

        if (this.currentTurn.equals(players[0]))
        {
            this.currentTurn = players[1];
        }
        else
        {
            this.currentTurn = players[0];
        }

        return true;
    }

}
