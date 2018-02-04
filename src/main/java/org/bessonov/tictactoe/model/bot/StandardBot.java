package org.bessonov.tictactoe.model.bot;

import org.bessonov.tictactoe.model.game.*;
import org.bessonov.tictactoe.model.players.PlayerRole;
import org.bessonov.tictactoe.model.sessions.GameSession;
import org.bessonov.tictactoe.model.players.Player;
import org.bessonov.tictactoe.model.utils.StandardWinChecker;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class StandardBot implements Player {
    protected GameSession session;
    protected PlayerRole botRole;

    public abstract void makeMove();

    protected LinkedList<Cell> getEmptyCells(Cell[] cells) {
        LinkedList<Cell> emptyCells = new LinkedList<>();
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].isEmpty()) {
                emptyCells.add(cells[i]);
            }
        }
        return emptyCells;
    }

    @Override
    public void makeMoveNotify() {
        makeMove();
    }

    @Override
    public void gameOverNotify() {

    }

    @Override
    public void interruptNotify() {

    }

    @Override
    public void refreshFieldNotify() {

    }

    public GameSession getSession() {
        return session;
    }

    public void setSession(GameSession session) {
        this.session = session;
        botRole = session.getRole();
    }

    public PlayerRole getBotRole() {
        return botRole;
    }

    public void setBotRole(PlayerRole botRole) {
        this.botRole = botRole;
    }
}
