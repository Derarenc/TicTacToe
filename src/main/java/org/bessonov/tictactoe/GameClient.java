package org.bessonov.tictactoe;

import javafx.application.Platform;
import org.bessonov.tictactoe.controllers.ViewController;
import org.bessonov.tictactoe.model.GameManager;
import org.bessonov.tictactoe.model.errors.GameException;
import org.bessonov.tictactoe.model.game.Cell;
import org.bessonov.tictactoe.model.game.GameResult;
import org.bessonov.tictactoe.model.game.Point;
import org.bessonov.tictactoe.model.players.Player;
import org.bessonov.tictactoe.model.players.PlayerRole;
import org.bessonov.tictactoe.model.sessions.GameSession;

import java.util.List;

public class GameClient implements Player {
    private GameSession gameSession;
    private PlayerRole role;
    private ViewController viewController;

    public boolean createNewGame(PlayerRole role, boolean unbeatableBot) {
        try {
            if (gameSession != null && gameSession.isAlive()) {
                gameSession.interruptRequest();
            }
            gameSession = GameManager.startSingleGame(this, role, unbeatableBot);
            this.role = gameSession.getRole();
            viewController.newStatus("");
            return true;
        } catch (GameException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void makeMoveNotify() {
        Platform.runLater(() -> makeMoveNotifyHandler());
    }

    private void makeMoveNotifyHandler() {
        viewController.newStatus("your move");
    }

    @Override
    public void gameOverNotify() {
        Platform.runLater(() -> gameOverNotifyHandler());
    }

    private void gameOverNotifyHandler() {
        GameResult result = gameSession.getGameResult();
        switch (result) {
            case X_WIN:
                viewController.newStatus(role == PlayerRole.X ? "You win" : "You lose");
                showWinSequence();
                break;

            case O_WIN:
                viewController.newStatus(role == PlayerRole.O ? "You win" : "You lose");
                showWinSequence();
                break;

            case DRAW:
                viewController.newStatus("Draw");
                break;

            case UNDEFINED:
                viewController.newStatus("undefined result");
                break;

            default:
                viewController.newStatus("unknown result");
                break;
        }
        viewController.gameOver();
    }

    private void showWinSequence() {
        List<Cell> cells = gameSession.getWinSequence();
        if (cells != null) {
            cells.forEach(cell -> viewController.highlight(cell));
        }
    }

    @Override
    public void interruptNotify() {
        Platform.runLater(() -> interruptNotifyHandler());
    }

    private void interruptNotifyHandler() {

    }

    @Override
    public void refreshFieldNotify() {
        Platform.runLater(() -> refreshFieldNotifyHandler());
    }

    private void refreshFieldNotifyHandler() {
        Cell modifiedCell = gameSession.getLastModifiedCell();
        if (modifiedCell != null) {
            viewController.setCell(modifiedCell);
        }
    }

    public boolean makeMove(Point c) {
        if (gameSession == null || !gameSession.isAlive()) {
            return false;
        }
        return gameSession.makeMove(c);
    }

    public PlayerRole getRole() {
        return role;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void stop() {
        if (gameSession != null && gameSession.isAlive()) {
            gameSession.interruptRequest();
        }
    }
}
