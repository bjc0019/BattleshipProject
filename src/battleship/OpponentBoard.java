package battleship;

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
        
        // Declare and initialize board array
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++)
            for(int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = 'E';
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
        if(_newGuess != 'H' || _newGuess != 'M')
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
    /** 
     * Generates an optimal guess based on the hits and misses on the board array
     * @return An int array holding [x, y] for the next guess
     */
    public int[] generateGuess()
    {
        int returnValue[] = {0,0};
        return returnValue;
    }
    
}
