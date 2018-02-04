package org.bessonov.tictactoe.model.game;

public class StandardGame extends Game {
    public StandardGame() {
        super(new GameSettings(3, 3));
        gameState = GameState.WAIT_TO_START;
    }

    @Override
    public void run() {
        while (gameState != GameState.INTERRUPTED && gameState != GameState.OVER) {
            if (isInterrupted()) {
                gameController.interrupt();
            }
            switch (gameState) {
                case WAIT_TO_START:
                    gameState = GameState.X_PLAYER_MOVE;

                case X_PLAYER_MOVE:
                    gameController.xPlayerMove();
                    break;

                case O_PLAYER_MOVE:
                    gameController.oPlayerMove();
                    break;

                default:
                    break;
            }
        }
        gameController.gameOver();
    }

}
