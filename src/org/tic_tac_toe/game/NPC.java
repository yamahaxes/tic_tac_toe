package org.tic_tac_toe.game;

import org.tic_tac_toe.game.Game;
import org.tic_tac_toe.game.Sign;
import org.tic_tac_toe.game.Symbol;

import java.util.Random;

public class NPC {

    private final Game game;
    private final Symbol symbol;

    public NPC(Game game, Symbol symbol){
        this.game = game;
        this.symbol = symbol;
    }

    public void nextMove(){
        Sign[][] gameField = game.getGameField();

        int random = new Random().nextInt(gameField.length * 2);
        boolean marked = false;

        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                if (random > 0 ){
                    random--;
                }else if (gameField[row][column] == null){
                    gameField[row][column] = new Sign(game.getImageO(), symbol);
                    marked = true;
                    break;
                }
            }
            if (marked){
                break;
            }
        }

        if (!marked){
            for (int row = 0; row < gameField.length; row++) {
                for (int column = 0; column < gameField[0].length; column++) {
                    if (gameField[row][column] == null){
                        gameField[row][column] = new Sign(game.getImageO(), symbol);
                    }
                }
            }
        }
    }

}
