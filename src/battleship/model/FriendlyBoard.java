package battleship.model;
import battleship.model.ships.Ship;
import battleship.model.ships.BattleShip;
import battleship.model.ships.Submarine;
import battleship.model.ships.Carrier;
import battleship.model.ships.Cruiser;
import battleship.model.ships.Destroyer;
import battleship.view.*;

/**
 * Represents the players version of the ship board.
 */
public class FriendlyBoard {
    // *********************************************************************************************
    // Variable Declaration
    // *********************************************************************************************
    
    /** Defines the size of the battleship board as n by n grid. The rules state this should be 10. */
    public static final int BOARD_SIZE = 10;
    
    /** BOARD_SIZE x BOARD_SIZE array which holds ship locations in char representation. */
    private char[][] board;
    
    /** Current location of Carrier on this board, can be null if ship hasn't been placed yet. */ 
    private Carrier carrier;  
    
    /** Current location of BattleShip on this board, can be null if ship hasn't been placed yet. */
    private BattleShip battleship;
    
    /** Current location of Submarine on this board, can be null if ship hasn't been placed yet. */
    private Submarine submarine;
    
    /** Current location of Cruiser on this board, can be null if ship hasn't been placed yet. */
    private Cruiser cruiser;
    
    /** Current location of Destroyer on this board, can be null if ship hasn't been placed yet. */
    private Destroyer destroyer;
    
    // *********************************************************************************************
    // Constructors
    // *********************************************************************************************
    
    /** Default board constructor which initializes an empty board. */
    public FriendlyBoard()
    {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        resetBoard();
    }
    
    // *********************************************************************************************
    // Methods
    // *********************************************************************************************
    
    /** 
     * Returns the char representation of the board at coordinates (x,y)
     @return char 'E' (empty), 'A'(Aircraft Carrier), 'B'(Battleship), 'S'(Submarine), 'C'(Cruiser), or 'D'(Destroyer) 
     @param x x coordinate on the board, starting at 0 index
     @param y y coordinate on the board, starting at 0 index
     */
    public char getTile(int x, int y)
    {
        if (x < BOARD_SIZE && y < BOARD_SIZE && x >= 0 && y >= 0)
            return board[(x)][y];
        else
            throw new IllegalArgumentException("Invalid x,y pair (" + x + ", " + y + ')');
    }
    
    
    /** 
     * Returns current instance of ship _shipType, which can be a Ship instance
     * or null, depending on current board state. 
     * @param _shipType One of 'A', 'B', 'C', 'D', or 'S'.
     * @return Either null or a Ship subclass
     */
    public Ship getShip(char _shipType)
    {
        return switch(_shipType) {
            case 'A' -> carrier;
            case 'B' -> battleship;
            case 'C' -> cruiser;
            case 'D' -> destroyer;
            case 'S' -> submarine;
            default -> throw new IllegalArgumentException("Input should be char A,B,C,D,or S.");
        };
    }
    
    
    /** Clears and resets the board with randomized ship placement. */
    public void randomizeBoard()
    {
        // First, clear the board
        resetBoard();
        
        // Declare holding variables for new ships
        Carrier newCarrier = new Carrier();
        BattleShip newBattleship = new BattleShip();
        Cruiser newCruiser = new Cruiser();
        Submarine newSubmarine = new Submarine();
        Destroyer newDestroyer = new Destroyer();
        
        // Randomly each ship on the board until it fits
        while (placeShip(newCarrier) == false)
            newCarrier.randomizeLocation();   
        
        while (placeShip(newBattleship) == false)
            newBattleship.randomizeLocation();
        
        while (placeShip(newCruiser) == false)
            newCruiser.randomizeLocation();
        
        while (placeShip(newSubmarine) == false)
            newSubmarine.randomizeLocation();
        
        while (placeShip(newDestroyer) == false)
            newDestroyer.randomizeLocation();
    }
    
    /**
     * Allows players to choose the location and orientation of their ships as they place them on their ship board.
     * @param corresponding_button_panel button panel that the player interacts with to place their ships
     */
    public void playerPlaceShips(playerBoard corresponding_button_panel)
    {
        // First, clear the board
        resetBoard();
        
        //Get user input until it's valid
        this.setMessageBoard("Select a coordinate for your carrier."+'\n');
        int[] coordinate_input = corresponding_button_panel.getUserInput();
        Carrier newCarrier = new Carrier(-1,-1,corresponding_button_panel.isVerticalPlacement());
        newCarrier.setLocation(coordinate_input);
        while (placeShip(newCarrier) == false){
            this.setMessageBoard("Try again."+'\n');
            newCarrier.setLocation(corresponding_button_panel.getUserInput());
            newCarrier.setOrientation(corresponding_button_panel.isVerticalPlacement());
        }
        updateBoatButtons();
        this.setMessageBoard("Select a coordinate for your battleship."+'\n');
        coordinate_input = corresponding_button_panel.getUserInput();
        BattleShip newBattleship = new BattleShip(-1,-1,corresponding_button_panel.isVerticalPlacement());
        newBattleship.setLocation(coordinate_input);
        while (placeShip(newBattleship) == false){
            this.setMessageBoard("Try again."+'\n');
            newBattleship.setLocation(corresponding_button_panel.getUserInput());
            newBattleship.setOrientation(corresponding_button_panel.isVerticalPlacement());
        }
        updateBoatButtons();
        
        this.setMessageBoard("Select a coordinate for your cruiser."+'\n');
        System.out.println("Select a coordinate for your cruiser."+'\n');
        coordinate_input = corresponding_button_panel.getUserInput();
        Cruiser newCruiser = new Cruiser(-1,-1,corresponding_button_panel.isVerticalPlacement());
        newCruiser.setLocation(coordinate_input);
        while (placeShip(newCruiser) == false){
            this.setMessageBoard("Try again."+'\n');
            newCruiser.setLocation(corresponding_button_panel.getUserInput());
            newCruiser.setOrientation(corresponding_button_panel.isVerticalPlacement());
        }
        updateBoatButtons();
        
        this.setMessageBoard("Select a coordinate for your submarine."+'\n');
        coordinate_input = corresponding_button_panel.getUserInput();
        Submarine newSubmarine = new Submarine(-1,-1,corresponding_button_panel.isVerticalPlacement());
        newSubmarine.setLocation(coordinate_input);
        while (placeShip(newSubmarine) == false){
            this.setMessageBoard("Try again."+'\n');
            newSubmarine.setLocation(corresponding_button_panel.getUserInput());
            newSubmarine.setOrientation(corresponding_button_panel.isVerticalPlacement());
        }
        updateBoatButtons();
        
        this.setMessageBoard("Select a coordinate for your destroyer."+'\n');
        coordinate_input = corresponding_button_panel.getUserInput();
        Destroyer newDestroyer = new Destroyer(-1,-1,corresponding_button_panel.isVerticalPlacement());
        newDestroyer.setLocation(coordinate_input);
        while (placeShip(newDestroyer) == false){
            this.setMessageBoard("Try again."+'\n');
            newDestroyer.setLocation(corresponding_button_panel.getUserInput());
        }
        updateBoatButtons();
        this.setMessageBoard("All boats placed" +'\n');
    }
    
    

    /**
     * Prints location of the player's boats after they have been placed.
     */
    public void updateBoatButtons() { 
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                    playerBoard.friendlyPanelButtonsLeft[i][j].setText(" ");
                else
                    playerBoard.friendlyPanelButtonsLeft[i][j].setText(Character.toString(this.getTile(j, BOARD_SIZE - 1 - i)));
            }
        }
    }

    /** 
     * Places ship on the board and returns true if successful, or false if 
     * the ship placement is invalid. 
     * @param _ship Ship subclass to be placed 
     * @return True or false, if the placement is valid and ship was placed
     */
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
        
        // Once ship is placed validly, return true
        return true;
    }
    
    
    
    /** 
     * Sets the board at coordinates (x,y) to char _in
     @param _x x coordinate on the board, starting at 0 index
     @param _y y coordinate on the board, starting at 0 index
     @param _input char that gets put in the board
     */
    private void setTile(int _x, int _y, char _input)
    {
        if (_x < BOARD_SIZE && _y < BOARD_SIZE && _x >= 0 && _y >= 0)
            board[_x][_y] = _input;
        else
            throw new IllegalArgumentException("Invalid (x,y) pair (" + _x + ", " + _y + ')');
    }
    
    
    
    /**
     * Returns true or false if the ship parameter can be placed 
     * on the board without intersecting other ships or running off the board
     * @param _ship Ship subclass to be placed
     * @return True or false, if the ship placement is valid
     */
    private boolean isShipPlacementValid(Ship _ship)
    {
        // First, check bounds for ship placement
        if(_ship.x < 0 || _ship.y < 0)
            return false;
        
        // Check bounds and overlapping tiles for horizontal placement
        if(_ship.isVertical) {
            if(_ship.y + _ship.length > BOARD_SIZE)
                return false;
            
            for(int i = 0; i < _ship.length; i++)
                if(board[_ship.x][_ship.y + i] != 'E')
                    return false;
        }
        else {
            if(_ship.x + _ship.length > BOARD_SIZE)
                return false;
            
            for(int i = 0; i < _ship.length; i++)
                if(board[_ship.x + i][_ship.y] != 'E')
                    return false;
        }
        
        // If the coordinates are valid and the boat doesn't overlapp others, return true
        return true;
    }
    
    
    /** 
     * Resets the internal board, setting all squares to 'E' for empty, and
     * sets all internal ship placement variables to null.
     */
    private void resetBoard()
    {
        // Set all values in the char array to 'E' (Empty)
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
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
    
    /**
     * Used to update the instructions/confirmations given to the player as they update their ship locations.
     * @param stringArgument 
     */
    public void setMessageBoard(String stringArgument){
        playerBoard.textArea.append(stringArgument);  
    }

}

