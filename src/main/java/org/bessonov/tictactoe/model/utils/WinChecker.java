package org.bessonov.tictactoe.model.utils;

import org.bessonov.tictactoe.model.game.Cell;
import org.bessonov.tictactoe.model.game.Move;

import java.util.List;

public interface WinChecker {
    List<Cell> getWinSequence(Move move);
}
