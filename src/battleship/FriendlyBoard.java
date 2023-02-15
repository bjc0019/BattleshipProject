package battleship;
import battleship.Ships.*;
import java.util.Arrays;
import java.util.Collections;

public class FriendlyBoard {
    // Variable Declaration
    private char[][] board;     // 8x8 array which holds ship locations in char representation
    private Carrier carrier;        // Classes which hold the 5 ship locations
    private BattleShip battleship;
    private Submarine submarine;
    private Cruiser cruiser;
    private Destroyer destroyer;
    
    /* Board constructor for Board class to be used as friendly board, containing 
       'E' for Empty or 'A', 'B', 'S', 'C', or 'D' to represent the ship types */
    
    public FriendlyBoard()
    {
        board = new char[8][8];
        resetBoard();
    }
    
    // Methods
    
    // Places ship on board and returns true if placement is valid, false otherwise
    public boolean placeShip(Ship _ship)
    {
        // Check to see if ship placement is valid
        if(!isShipPlacementValid(_ship))
            return false;
        
        // Put char representation on the board
        for(int i = 0; i < _ship.length; i++) {
            if(_ship.isVertical)
                board[_ship.x][_ship.y + i] = _ship.charRepresentation;
            else
                board[_ship.x + i][_ship.y] = _ship.charRepresentation;
        }
        
        // Update stored Ship instances for this class
        if(_ship.getClass() == Carrier.class  ) {
            if(carrier != null)   // Check to see if there's a carrier already on the board
                return false;
            carrier = (Carrier)_ship;
        }
        else if(_ship.getClass() == BattleShip.class  ) {
            if(battleship != null)   // Check to see if there's a carrier already on the board
                return false;
            battleship = (BattleShip)_ship;
        }
        else if(_ship.getClass() == Submarine.class  ) {
            if(submarine != null)   // Check to see if there's a carrier already on the board
                return false;
            submarine = (Submarine)_ship;
        }
        else if(_ship.getClass() == Cruiser.class  ) {
            if(cruiser != null)   // Check to see if there's a carrier already on the board
                return false;
            cruiser = (Cruiser)_ship;
        }   
        else if(_ship.getClass() == Destroyer.class  ) {
            if(destroyer != null)   // Check to see if there's a carrier already on the board
                return false;
            destroyer = (Destroyer)_ship;
        }      
        
        
        
        return true;
    }
    
    private boolean isShipPlacementValid(Ship _ship)
    {
        // First, check bounds for ship placement
        if(_ship.x < 0 || _ship.y < 0)
            return false;
        
        // Check bounds and overlapping tiles for horizontal placement
        if(_ship.isVertical) {
            if(_ship.y + _ship.length - 1 > 7)
                return false;
            
            for(int i = 0; i < _ship.length; i++)
                if(board[_ship.x][_ship.y + i] != 'E')
                    return false;
        }
        else {
            if(_ship.x + _ship.length - 1 > 7)
                return false;
            
            for(int i = 0; i < _ship.length; i++)
                if(board[_ship.x + i][_ship.y] != 'E')
                    return false;
        }
        
        // If the coordinates are valid and the boat doesn't overlapp others, return true
        return true;
    }
    
    // Clears the board and sets all values to 'E'
    private void resetBoard()
    {
        // Set all values in the char array to 'E' (Empty)
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = 'E';
            }
        }
        
        // Clear the stored boats
        carrier = null;
        battleship = null;
        cruiser = null;
        submarine = null;
        destroyer = null;
    }
    
    // Prints the board to the console
    public void printBoard()
    {
        for(int i = 7; i >= 0; i--) {
            for(int j = 0; j <= 7; j++) 
                System.out.print(board[j][i] + ", " );
            System.out.println("");
        }
    }
    
}
