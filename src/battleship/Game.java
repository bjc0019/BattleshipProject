package battleship;
import java.util.Objects;
import java.util.Random;
import battleship.view.*; 
import java.util.concurrent.Semaphore;


public class Game {
    // ******************************************************************************************************************
    // Variables
    // ******************************************************************************************************************
    private Semaphore playgameSemaphore = new Semaphore(0); // a semaphore to prevent the code from executing any further until play game is pressed in menuBoard
    private FriendlyBoard playerShipBoard = new FriendlyBoard();
    private OpponentBoard playerGuessBoard = new OpponentBoard();

    private FriendlyBoard botShipBoard = new FriendlyBoard();
    private OpponentBoard botGuessBoard = new OpponentBoard();

    private int playerHits = 0, botHits = 0;
    private int[] coordinate;

    private final int hitsToWin = 17;

    private menuBoard launch_menu = new menuBoard(playgameSemaphore);

    private playerBoard launchPlayerView = new playerBoard();
    private botBoard launchBotView = new botBoard();
    
    
    // ******************************************************************************************************************
    // Methods
    // ******************************************************************************************************************
    
    /** Main Entry point for our game program.      */
    public void startGame()
    {
        //we don't need to see the bot's board.
        launchBotView.setVisible(false);

       // Allow the player to initialize their board and create the opponents
        playerShipBoard.playerPlaceShips(launchPlayerView);
        botShipBoard.randomizeBoard();          // Randomize opponent board  

        // Start gameplay loop, guessing back and forth
        while(true) {
            // Update the boards with the player's guess, and generate one from the bot
            // First, process the player
            coordinate = launchPlayerView.getUserInput();
            
            // Update board states with the player's guess 
            this.processTurn();
            
            // Redraw the boat and guess board in the GUI
            playerShipBoard.updateBoatButtons(); // This is the bottom board on the player's screen that shows the player's board
            playerGuessBoard.updateGuessButtons(); // This is the top board on the player's screen that shows where the bot has guessed 

            // Print the board, and check to see if the game is over
            if(playerHits >= hitsToWin) {
                System.out.println("Player won:");
                break;
            }
            if(botHits >= hitsToWin) {
                System.out.println("Player won:");
                break;
            }

        }
       System.out.println("END of CODE:");
    //}
    }
    
    
    /** Function that handles all the board logic when a user guesses on the board */
    private void processTurn()
    {
            if(botShipBoard.getTile(coordinate[0], coordinate[1]) != 'E') {   // If the player has a hit
                playerGuessBoard.updateBoard(coordinate[0], coordinate[1], 'H');
                playerHits++;
            }
            else 
                playerGuessBoard.updateBoard(coordinate[0], coordinate[1], 'M');

            // Next, the bot
            coordinate = botGuessBoard.generateGuess();

            if(playerShipBoard.getTile(coordinate[0], coordinate[1]) != 'E') {
                botGuessBoard.updateBoard(coordinate[0], coordinate[1], 'H');
                botHits++;
            }
            else
                botGuessBoard.updateBoard(coordinate[0], coordinate[1], 'M');
    }
}

