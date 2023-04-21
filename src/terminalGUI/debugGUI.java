package terminalGUI;

import battleship.model.ships.Ship;
import battleship.model.ships.BattleShip;
import battleship.model.ships.Submarine;
import battleship.model.ships.Carrier;
import battleship.model.ships.Cruiser;
import battleship.model.ships.Destroyer;
import battleship.model.OpponentBoard;
import battleship.model.FriendlyBoard;
import static battleship.model.FriendlyBoard.BOARD_SIZE;
import battleship.view.playerBoard;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Singleton class that emulates the real GUIs behavior in the terminal. 
 * @author Ben
 */
public class debugGUI {
    // ******************************************************************************************************************
    // Variable Declaration
    // ******************************************************************************************************************
    
    /** Scanner used to get input from the user. */
    private Scanner sc = new Scanner(System.in);
    
    /** Holds points guessed by the player, so they don't repeat guesses. */
    private ArrayList<int[]> guessedPoints = new ArrayList<int[]>();
    
    /** Pointer to the instance of the debugGUI class */ 
    private static debugGUI theInstance;
    
    // ******************************************************************************************************************
    // Constructors
    // ******************************************************************************************************************
    
    /** Empty constructor called by getInstance(). */
    public debugGUI() {}
    
    // ******************************************************************************************************************
    // Methods
    // ******************************************************************************************************************
    
    /**
     * This method instantiates the instance of the debugGUI class if it doesn't
     * already exist, and returns it. 
     * @return The singleton instance of debugGUI.
     */
    public static debugGUI getInstance()
    {
         if(theInstance == null)
             theInstance = new debugGUI();
         return theInstance;
    }
    
    /** 
     * Prints the main menu and gets options from user.
     * @return either "start" or "quit" depending on the user's input.
     */
    public String printMainMenu()
    {
        System.out.println("\tMain Menu\nSelect one:\n1. Start\n2. Quit\n");
        while(true) {
            switch(sc.nextInt()) {
                case 1:
                    return "start";
                case 2:
                    return "quit";
                default:
                    continue;
                
            }
        }
    }
    
    /**
     * Used for instantiating new boats whenever player places ships.
     * @param _fboard Board where user's boats are placed.
     */
    public void placeShips(FriendlyBoard _fboard) {
        BattleShip battleship = new BattleShip();
        Carrier carrier = new Carrier();
        Cruiser cruiser = new Cruiser();
        Destroyer destroyer = new Destroyer();
        Submarine submarine = new Submarine();
        };

    /**
     * Prints the two boards that the user interacts with on the main game panel. The top board is the player's guess board, the bottom board is the player's ship board.
     * @param _fboard Player's ship board.
     * @param _oboard Player's guess board.
     */
    public void printBoard(FriendlyBoard _fboard, OpponentBoard _oboard)
    {
        // Print Opponent Board first
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) 
                if (_oboard.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                    System.out.print(" ");
                else
                    System.out.print(_oboard.getTile(j, BOARD_SIZE - 1 - i));
                System.out.print(", ");
                        
            System.out.println("");
        }
        
        // Print divider
        for(int i = 0; i < 30; i++) { System.out.print('-'); }
        System.out.println('\n');
        
        // Print friendly board on the bottom
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++)
                if (_fboard.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                    System.out.print(" ");
                else
                    System.out.print(_fboard.getTile(j, BOARD_SIZE - 1 - i));
                System.out.print(", ");
                
                        
            System.out.println("");
        }
        System.out.println("\n");
    }
    
    /** 
     * Get an x,y coordinate pair for a guess from the player.
     * @return an [x,y] coordinate pair
     */
    public int[] getGuess() 
    {
        int x, y;
        int[] coordinate;
        
        do {
            // Get the x coordinate
            do {
                System.out.println("Enter the x coordinate for a guess: ");
                x = sc.nextInt();
            } while (!(x >= 0 && x < BOARD_SIZE));
            
            // Get the y coordinate
            do {
                System.out.println("Enter the y coordinate for a guess: ");
                y = sc.nextInt(); 
            } while(!(y >= 0 && y < BOARD_SIZE));
            
            // Construct the coordinate pair, and check to see if it's already been used
            coordinate = new int[]{x,y};
        } while(!guessedPoints.contains(coordinate));
        
        // Push the guessed coordinate pair into guessed points, and return
        guessedPoints.add(coordinate);
        return coordinate;
    }
    
    
    /**
     * Prints game over screen and player stats
     * @param _playerWon used to display results of game.
     * @param _oboard player's guess board; used for player to make guesses and retrieve player accuracy.
     */
    public void gameOver(boolean _playerWon, OpponentBoard _oboard)
    {
        if(_playerWon)
            System.out.println("Game over. You Won!");
        else
            System.out.println("Game over. You Lost!");
        
        System.out.println("Your accuracy was: %" + _oboard.getAccuracy());
        System.out.println("Enter any value to continue: ");
        sc.next();
    }
            
    /**
     * Used to display type of ship being placed and orientation.
     * @param _shipName Name of the type of ship being placed by user.
     * @param _shipToPlace Type of ship being placed by user.
     */
    private void getShipInfo(String _shipName, Ship _shipToPlace)
    {
        System.out.println("\nEnter the x value for the base of the " + _shipName + ":");
        _shipToPlace.x = sc.nextInt();
        System.out.println("Enter the y value for the base of the " + _shipName + ":");
        _shipToPlace.y = sc.nextInt();
        System.out.println("Enter true for vertical placement or false for horizontal: ");
        _shipToPlace.isVertical = sc.nextBoolean();
    }
}

