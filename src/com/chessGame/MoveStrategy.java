package com.chessGame;

import java.util.List;

public abstract class MoveStrategy {
    public abstract List<Square> getValidMoves(Board board, Square currentSquare);
}
