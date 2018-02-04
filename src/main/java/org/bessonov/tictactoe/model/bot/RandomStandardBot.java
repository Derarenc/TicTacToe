package org.bessonov.tictactoe.model.bot;

import org.bessonov.tictactoe.model.game.Cell;
import org.bessonov.tictactoe.model.game.Field;
import org.bessonov.tictactoe.model.game.Point;

import java.util.LinkedList;

public class RandomStandardBot extends StandardBot {
    @Override
    public void makeMove() {
        Point move = randomMove(session.getGameField());
        if (move == null) {
            throw new NullPointerException();
        }
        session.makeMove(move);
    }

    public Point randomMove(Field field) {
        Point point = null;
        LinkedList<Cell> emptyCells = getEmptyCells(field.getCells());
        if (emptyCells.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * emptyCells.size());
        point = emptyCells.get(randomIndex).getCoordinates();
        return point;
    }
}
