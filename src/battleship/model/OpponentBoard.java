package battleship.model;

import battleship.model.FriendlyBoard;
import static battleship.model.FriendlyBoard.BOARD_SIZE;
import battleship.view.botBoard;
import battleship.view.playerBoard;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the AI/Bot board.
 * @author bcalv
 */
public class OpponentBoard {
    
    // ******************************************************************************************************************
    // Variable Declaration
    // ******************************************************************************************************************
    
    /** 2D char array which holds either Empty 'E', Hit 'H', or Miss 'M'. */
    private char[][] board;
    
    /** Length and width of the board. */
    private static final int BOARD_SIZE = FriendlyBoard.BOARD_SIZE;
    
    /** Number of hits and misses on this board. */
    private int numHits, numMisses;
    
    /** Guessing accuracy, defined as hits/misses as a percent. */
    private float accuracy;
    
    /** Holds points not guessed by the player, so they don't repeat guesses. */
    private ArrayList<int[]> pointsToGuess;
    
    // ******************************************************************************************************************
    // Constructors
    // ******************************************************************************************************************
    
    /** Default constructor initializes board to empty. */
    public OpponentBoard()
    {
        // Initialize hit/miss variables
        numHits = 0;
        numMisses = 0;
        accuracy = 0;
        
        // Initialize board array
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = 'E';
        
        // Initialize array of points to guess
        pointsToGuess = new ArrayList<int[]>();
        
        // Populate all the coordinate pairs which can be guessed
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                pointsToGuess.add(new int[]{i,j});
    }
    
    // ******************************************************************************************************************
    // Methods
    // ******************************************************************************************************************
    
    /** 
     * Updates board with a new guess. _newGuess char is placed at (x,y) on the board
     * @param _x X coordinate of updated point
     * @param _y Y coordinate of updated point
     * @param _newGuess char to be placed on the board at (x,y)
     */
    public void updateBoard(int _x, int _y, char _newGuess)
    {
        // Make sure input is in the specified range
        if(_x < 0 && _y < 0)
            throw new IllegalArgumentException(String.format("Either x value %d or y value %d is less than 0", _x, _y));
        if(_x > BOARD_SIZE || _y > BOARD_SIZE)
            throw new IllegalArgumentException(String.format("Either x: %d or y: %d is larger than board size %d", _x, _y, BOARD_SIZE));
        if(_newGuess != 'H' && _newGuess != 'M')
            throw new IllegalArgumentException(String.format("Char input %c is not 'H' or 'M'", _newGuess));
        
        // Update board with new value
        board[_x][_y] = _newGuess;
        
        
        
        // Update statistical variables
        if(_newGuess == 'H')
            numHits++;
        if(_newGuess == 'M')
            numMisses++;
        
        // Update accuracy
        if(numHits + numMisses != 0)
            accuracy = (float)numHits / (numHits + numMisses) * 100;
    }
    
     
/**
 * Stores the location of the AI's boats. These are not visible to the player.
 */
    public void updateGuessButtons() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                    playerBoard.friendlyPanelButtonsRight[i][j].setText(" ");
                else {
                    String charToSet = Character.toString(this.getTile(j, BOARD_SIZE - 1 - i));
                    playerBoard.friendlyPanelButtonsRight[i][j].setText(charToSet);
                }
            }
        }
    }  
   
    /**
     * Returns char value at x,y.
     * @param _x 0 Indexed x coordinate 
     * @param _y 0 Indexed y coordinate
     * @return Char value at x,y on the board
     */
    public char getTile(int _x, int _y)
    {
        if (_x < BOARD_SIZE && _y < BOARD_SIZE && _x >= 0 && _y >= 0)
            return board[_x][_y];
        else
            throw new IllegalArgumentException("Invalid x,y pair (" + _x + ", " + _y + ')');
    }
    
    
    
    /** 
     * Returns the player's guessing accuracy
     * @return A float with the player's guessing accuracy
     */
    public float getAccuracy()
    {
        return accuracy;
    }
    
    
    /** 
     * Generates an optimal guess based on the hits and misses on the board array
     * @return An int array holding [x, y] for the next guess.
     */
    public int[] generateGuess()
    {
        Random rng = new Random();
        int returnValue[];
        int index;
        
        // Select a random element from the array list of tiles to guess
        index = rng.nextInt(pointsToGuess.size());
        returnValue = pointsToGuess.get(index).clone();
        pointsToGuess.remove(index);
        
        // Return coordinate pair
        return returnValue;
    }
}
