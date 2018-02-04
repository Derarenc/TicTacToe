package org.bessonov.tictactoe.model.players;

public interface Player {
    void makeMoveNotify();

    void gameOverNotify();

    void interruptNotify();

    void refreshFieldNotify();
}
