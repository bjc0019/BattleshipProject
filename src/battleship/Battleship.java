package battleship;
import battleship.Ships.*;
import myExceptions.*;

public class Battleship {
    public static void main(String[] args) {
        FriendlyBoard fboard = new FriendlyBoard();
        Carrier carrier = new Carrier(2,1,true);
        BattleShip battleship = new BattleShip(1,1,true);
        Submarine submarine = new Submarine(4,4,true);
        Cruiser cruiser = new Cruiser(4,3, false);
        Destroyer destroyer = new Destroyer(7,6,true);
        
        //System.out.println(fboard.placeShip(carrier));
        //System.out.println(fboard.placeShip(battleship));
        //System.out.println(fboard.placeShip(cruiser));
        //System.out.println(fboard.placeShip(submarine));
        //System.out.println(fboard.placeShip(destroyer));
        
        fboard.randomizeBoard();
        fboard.printBoard();
    } 
}
