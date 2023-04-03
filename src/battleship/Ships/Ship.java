package battleship.Ships;

import java.util.Random;
import battleship.FriendlyBoard;

/** 
 * Superclass for the ship classes.
 * @author bcalv
 */
public abstract class Ship {
    // Variables 
    public int x,y;  // Location of base tile for this ship
    public boolean isVertical; // Determines orientation of this ship's placement
    public int length;  // Length of this ship
    public char charRepresentation;  // The character this ship is rep'd by on the board
    
    // Constructors
    protected Ship(int _x, int _y, boolean _isVertical)
    {
        x = _x;
        y = _y;
        isVertical = _isVertical;
    }
    
    protected Ship()
    {
        
    }
    
    /** 
     * Generates random x, y, and isVertical values for this instance of 
     * a ship class. Given it doesn't overlap another ship on the board, 
     * the placement will always be valid. 
     */
    public void randomizeLocation()
    {
        Random rng = new Random();
        isVertical = rng.nextBoolean();
        if (isVertical)
        {
            x = rng.nextInt(0,FriendlyBoard.BOARD_SIZE);
            y = rng.nextInt(0,FriendlyBoard.BOARD_SIZE - length);
        }
        else {
            x = rng.nextInt(0,FriendlyBoard.BOARD_SIZE - length);
            y = rng.nextInt(0,FriendlyBoard.BOARD_SIZE);
        }
        
        
    }
}
