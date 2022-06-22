package org.tic_tac_toe.game;

public class PlayerNPC {

    private final Game game;

    public PlayerNPC(Game game){
        this.game = game;
    }

    public void nextMove(){
        Sign[][] gameField = game.getGameField();

        // Тестовое поведение
        boolean isMarked = false;

        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                if (gameField[row][column] == null){
                    gameField[row][column] = new Sign(game.getImageO());
                    isMarked = true;
                    break;
                }
            }
            if (isMarked){
                break;
            }
        }

    }

}
