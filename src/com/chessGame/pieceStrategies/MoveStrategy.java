package com.chessGame.pieceStrategies;

import com.chessGame.Board;
import com.chessGame.Square;

import java.util.List;

public abstract class MoveStrategy {
    public abstract List<Square> getValidMoves(Board board, Square currentSquare);
}
