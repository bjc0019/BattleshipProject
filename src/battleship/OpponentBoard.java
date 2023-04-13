package battleship;

import static battleship.FriendlyBoard.BOARD_SIZE;
import battleship.view.botBoard;
import battleship.view.playerBoard;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import myExceptions.*;

/**
 *
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
    OpponentBoard()
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
        
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                pointsToGuess.add(new int[]{i,j});
    }
    
    // ******************************************************************************************************************
    // Methods
    // ******************************************************************************************************************
    
    /** 
     * Updates board with a new guess. _newGuess char is placed at (x,y) on the board
     * @throws IndexOutOfBounds If x or y are not in the board, or char is an invalid value, throws this exception
     * @param _x X coordinate of updated point
     * @param _y Y coordinate of updated point
     * @param _newGuess char to be placed on the board at (x,y)
     */
    public void updateBoard(int _x, int _y, char _newGuess) throws IndexOutOfBounds
    {
        // Make sure input is in the specified range
        if(_x < 0 && _y < 0)
            throw new IndexOutOfBounds(String.format("Either x value %d or y value %d is less than 0", _x, _y));
        if(_x > BOARD_SIZE || _y > BOARD_SIZE)
            throw new IndexOutOfBounds(String.format("Either x: %d or y: %d is larger than board size %d", _x, _y, BOARD_SIZE));
        if(_newGuess != 'H' && _newGuess != 'M')
            throw new IndexOutOfBounds(String.format("Char input %c is not 'H' or 'M'", _newGuess));
        
        // Update board with new value
        board[_x][_y] = _newGuess;
        
        
        
        // Update statistical variables
        if(_newGuess == 'H')
            numHits++;
        if(_newGuess == 'M')
            numMisses++;
        
        // Update accuracy
        if(numHits + numMisses != 0)
            accuracy = numHits / (numHits + numMisses) * 100;
    }
    
     public void updateBoatButtons() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                try {
                    if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                        botBoard.botBoatsButtons[i][j].setText("OB");
                    else
                        botBoard.botBoatsButtons[i][j].setText(Character.toString(this.getTile(j, BOARD_SIZE - 1 - i)));
                }
                catch(IndexOutOfBounds ex) {
                    System.out.println("Error updating board buttons: " + ex.getMessage());
                    System.exit(1);
                }
            }
        }
    }
     
    public void updateGuessButtons() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                try {
                    if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                        playerBoard.botGuessButtons[i][j].setText("OG");
                    else
                        playerBoard.botGuessButtons[i][j].setText(Character.toString(this.getTile(j, BOARD_SIZE - 1 - i)));
                }
                catch(IndexOutOfBounds ex) {
                    System.out.println("Error updating board buttons: " + ex.getMessage());
                    System.exit(1);
                }
            }
        }
    }    
       
 
    
    
    
   /*
    public void updateBoardButtons() {
    for(int i = 0; i < BOARD_SIZE; i++) {
        for(int j = 0; j < BOARD_SIZE; j++) {
            try {
                if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E') {
                    playerBoard.botGuessButtons[i][j].setText("O!");
                } else {
                    playerBoard.botGuessButtons[i][j].setText(Character.toString(this.getTile(j, BOARD_SIZE - 1 - i)));
                }
       
                // Update botGuessButtons
                char tile = this.getTile(j, BOARD_SIZE - 1 - i);
                if (tile == 'H') {
                    playerBoard.botGuessButtons[i][j].setText("H");
                    playerBoard.botGuessButtons[i][j].setBackground(Color.RED);
                } else if (tile == 'M') {
                    playerBoard.botGuessButtons[i][j].setText("M");
                    playerBoard.botGuessButtons[i][j].setBackground(Color.WHITE);
                } else {
                    playerBoard.botGuessButtons[i][j].setText(" ");
                }
                
            } catch(IndexOutOfBounds ex) {
                System.out.println("Error updating board buttons: " + ex.getMessage());
                System.exit(1);
            }
        }
    }
}
*/
 /**   
   
    /**
     * Returns char value at x,y.
     * @param _x 0 Indexed x coordinate 
     * @param _y 0 Indexed y coordinate
     * @return Char value at x,y on the board
     * @throws IndexOutOfBounds if x or y aren't between 0-9
     */
    public char getTile(int _x, int _y) throws IndexOutOfBounds
    {
        if (_x < BOARD_SIZE && _y < BOARD_SIZE && _x >= 0 && _y >= 0)
            return board[_x][_y];
        else
            throw new IndexOutOfBounds("Invalid x,y pair (" + _x + ", " + _y + ')');
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
    
/**
    public void updateBoardButtons() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                try {
                    if (this.getTile(j, BOARD_SIZE - 1 - i) == 'E')
                        gameBoard.botBoardButtons[i][j].setText(" ");
                    else
                        gameBoard.botBoardButtons[i][j].setText(Character.toString(this.getTile(j, BOARD_SIZE - 1 - i)));
                }
                catch(IndexOutOfBounds ex) {
                    System.out.println("Error updating board buttons: " + ex.getMessage());
                    System.exit(1);
                }
            }
        }
    }
*/

}
