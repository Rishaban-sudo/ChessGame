package com.chessGame;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private int points = 0;

    King(boolean isWhite) {
        super(isWhite);
    }

    private boolean lookForKnight(Board board, Square end, int x1, int y1) {
        if (x1 < ChessBoard.ROWS && y1 < ChessBoard.COLS) {
            if (board.box[x1][y1].getPiece() instanceof Knight
                    && board.box[x1][y1].getPiece().isWhite() != end.getPiece().isWhite()) {
                return false;
            } else
                return true;
        }
        return false;
    }


    //checks whether the end square is safe for the king
    private boolean isSafeMove(Board board, Square end) {
        int x = end.getX();
        int y = end.getY();

        //checking along the row(right) if there is any check for the King
        //Expected Threat Pieces:Rook,Queen
        for (int j = y; j < ChessBoard.COLS; j++) {
            if (board.box[x][j].getPiece() instanceof Rook || board.box[x][j].getPiece() instanceof Queen) {
                if (board.box[x][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //checking along the row(right) if there is any check for the King
        //Expected Threat Pieces:Rook,Queen
        for (int j = y; j >= 0; j--) {
            if (board.box[x][j].getPiece() instanceof Rook || board.box[x][j].getPiece() instanceof Queen) {
                if (board.box[x][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //checking along the col(downwards) if there is any check for the King
        //Expected Threat Pieces:Rook,Queen
        for (int i = x; i < ChessBoard.ROWS; i++) {
            if (board.box[i][y].getPiece() instanceof Rook || board.box[i][y].getPiece() instanceof Queen) {
                if (board.box[i][y].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //checking along the col(upwards) if there is any check for the King
        //Expected Threat Pieces:Rook,Queen
        for (int i = x; i >= 0; i--) {
            if (board.box[i][y].getPiece() instanceof Rook || board.box[i][y].getPiece() instanceof Queen) {
                if (board.box[i][y].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //The king is safe from all rows and cols

        //Checking the diagonals (upper right)
        //Expected Threat Pieces:Bishop,Queen
        for (int i = x, j = y; i > 0 && j < ChessBoard.COLS; i--, j++) {
            if (board.box[i][j].getPiece() instanceof Bishop || board.box[i][j].getPiece() instanceof Queen) {
                if (board.box[i][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //Checking the diagonals (lower left)
        //Expected Threat Pieces:Bishop,Queen
        for (int i = x, j = y; i < ChessBoard.ROWS && j > 0; i++, j--) {
            if (board.box[i][j].getPiece() instanceof Bishop || board.box[i][j].getPiece() instanceof Queen) {
                if (board.box[i][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //Checking the diagonals (upper left)
        //Expected Threat Pieces:Bishop,Queen
        for (int i = x, j = y; i > 0 && j > 0; i--, j--) {
            if (board.box[i][j].getPiece() instanceof Bishop || board.box[i][j].getPiece() instanceof Queen) {
                if (board.box[i][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //Checking the diagonals (lower right)
        //Expected Threat Pieces:Bishop,Queen
        for (int i = x, j = y; i < ChessBoard.ROWS && j < ChessBoard.COLS; i++, j++) {
            if (board.box[i][j].getPiece() instanceof Bishop || board.box[i][j].getPiece() instanceof Queen) {
                if (board.box[i][j].getPiece().isWhite() == end.getPiece().isWhite()) {
                    break;
                } else {
                    return false;
                }
            }
        }

        //checking if king is threatened by knight
        return lookForKnight(board, end, x - 2, y + 1)
                && lookForKnight(board, end, x - 2, y - 1)
                && lookForKnight(board, end, x + 2, y + 1)
                && lookForKnight(board, end, x + 2, y - 1)
                && lookForKnight(board, end, x + 1, y - 2)
                && lookForKnight(board, end, x + 1, y + 2)
                && lookForKnight(board, end, x - 1, y - 2)
                && lookForKnight(board, end, x - 1, y + 2);
    }

    @Override
    public boolean isValidMove(Board board, Square start, Square end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x == 1 && y == 1 || x == 1 && y == 0 || y == 1 && x == 0) {
            return isSafeMove(board, end);
        }

        return false;
    }

    @Override
    public int getPoints() {
        return points;
    }

    private List<MoveStrategy> strategies = new ArrayList<>();

    {
        strategies.add(new KingStrategy());
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
        return "K" + ((this.isWhite()) ? "W" : "B");
    }

}
