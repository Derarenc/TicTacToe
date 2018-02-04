package org.bessonov.tictactoe.model;

import org.bessonov.tictactoe.model.bot.MinMaxStandardBot;
import org.bessonov.tictactoe.model.bot.RandomStandardBot;
import org.bessonov.tictactoe.model.bot.StandardBot;
import org.bessonov.tictactoe.model.controllers.GameController;
import org.bessonov.tictactoe.model.controllers.PlayerController;
import org.bessonov.tictactoe.model.errors.GameException;
import org.bessonov.tictactoe.model.game.Game;
import org.bessonov.tictactoe.model.game.StandardGame;
import org.bessonov.tictactoe.model.players.Player;
import org.bessonov.tictactoe.model.players.PlayerRole;
import org.bessonov.tictactoe.model.sessions.GameSession;
import org.bessonov.tictactoe.model.sessions.LocalGameSession;

public class GameManager {
    public static GameSession startSingleGame(Player player, PlayerRole role, boolean unbeatableBot)
            throws GameException {
        StandardBot bot = unbeatableBot ? new MinMaxStandardBot() : new RandomStandardBot();

        Game game = new StandardGame();
        GameController gameController = new GameController(game);
        PlayerController playerController = role == PlayerRole.X ?
                new PlayerController(player, bot) :
                new PlayerController(bot, player);

        gameController.setPlayerController(playerController);
        playerController.setGameController(gameController);
        game.setGameController(gameController);

        GameSession localGameSession = new LocalGameSession(game, playerController, player, role);
        GameSession botGameSession = new LocalGameSession(game, playerController, bot, role.inverse());
        bot.setSession(botGameSession);

        game.start();

        return localGameSession;
    }
}
