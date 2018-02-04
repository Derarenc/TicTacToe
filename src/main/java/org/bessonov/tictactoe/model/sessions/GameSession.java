package org.bessonov.tictactoe.model.sessions;

import org.bessonov.tictactoe.model.game.*;
import org.bessonov.tictactoe.model.players.PlayerRole;

import java.util.List;

public interface GameSession {
    Field getGameField();

    boolean makeMove(Point coordinates);

    GameResult getGameResult();

    PlayerRole getRole();

    boolean isAlive();

    void interruptRequest();

    Cell getLastModifiedCell();

    List<Cell> getWinSequence();
}
