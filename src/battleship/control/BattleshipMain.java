package battleship.control;

import battleship.control.Game;

/**
 * Runs Battleship.
 */

public class BattleshipMain {
    public static void main(String[] args)
    {
        Game theGame = new Game();
        theGame.startGame();
    }
}
