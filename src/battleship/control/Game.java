package battleship.control;
import battleship.model.OpponentBoard;
import battleship.model.FriendlyBoard;
import java.util.Objects;
import java.util.Random;
import battleship.view.*; 
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 * Represents Battleship game. Consists of the main objects that make up the game,
 * along with functions that allow the game to run until completion.
 */
public class Game {

    // ******************************************************************************************************************
    // Variables
    // ******************************************************************************************************************
    
    /** Length and width of the board. */
    public static final int BOARD_SIZE = 10;
    
    /** Number of hits needed to win the game. */
    private static final int HITS_TO_WIN =17;
    
    // a semaphore to prevent the code from executing any further until play game is pressed in menuBoard
    private final Semaphore playgameSemaphore = new Semaphore(0); 
    //private final Semaphore playgameSemaphore = new Semaphore(Integer.MAX_VALUE);
    // Player boards
    private final FriendlyBoard playerShipBoard = new FriendlyBoard();
    private final OpponentBoard playerGuessBoard = new OpponentBoard();
    
    // Bot boards
    private final FriendlyBoard botShipBoard = new FriendlyBoard();
    private final OpponentBoard botGuessBoard = new OpponentBoard();
    
    // Some variables needed to calculate win states and get user input
    
    public static int playerHits = 0, botHits = 0;
    private int[] coordinate;
    public static int playerScore;
    
    // GUI boards 
    private final menuBoard launch_menu = new menuBoard(playgameSemaphore);
    private final playerBoard launchPlayerView = new playerBoard();
    private final botBoard launchBotView = new botBoard();
    
    
    // ******************************************************************************************************************
    // Methods
    // ******************************************************************************************************************
 
    /** Main Entry point for our game program.
     * @return true to restart game, false to quit. */
    public boolean startGame()
    {
        //we don't need to see the bot's board.
        launchBotView.setVisible(false);
        
        // Now that the player has selected "Play", dispose of the main menu
        launch_menu.dispose();

       // Allow the player to initialize their board and create the opponents
        playerShipBoard.playerPlaceShips(launchPlayerView);
        botShipBoard.randomizeBoard();          // Randomize opponent board  
        
        //launch_menu.dispose();
        //JComponent comp = (JComponent) e.getSource();
        //Window win = SwingUtilities.getWindowAncestor(comp);
        //win.dispose();

        // Start gameplay loop, guessing back and forth
        
        while(true) {
            // Update board states with the player's guess 
            playerShipBoard.setMessageBoard("Player's turn to guess."+'\n');
            this.processTurn();
            playerShipBoard.setMessageBoard("Bot's turn to guess."+'\n');
            // Redraw the boat and guess board in the GUI
            playerShipBoard.updateBoatButtons(); // This is the bottom board on the player's screen that shows the player's board
            playerGuessBoard.updateGuessButtons(); // This is the top board on the player's screen that shows where the bot has guessed 
            
            // Print the board, and check to see if the game is over
            if(playerHits >= HITS_TO_WIN) {
                launchPlayerView.dispose();
                String message = String.format("Player wins! Hit accuracy: %f. Save game?",playerGuessBoard.getAccuracy());
                int response = JOptionPane.showConfirmDialog(null, message, "Game over.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    //Save the information.
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    // Create a text field for the player name
                    JTextField playerNameField = new JTextField();
                    Object[] fields = {"Player Name:", playerNameField};

                    // Display a dialog box with the text field for the player name
                    int result = JOptionPane.showConfirmDialog(null, fields, "Enter Player Name", JOptionPane.OK_CANCEL_OPTION);

                    // Check if the user clicked "OK"
                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            // Get the player name from the text field
                            String playerName = playerNameField.getText();

                            // Save the player name to the file
                            FileWriter outFile = new FileWriter("src/Files/battleship_score.txt", true);
                            outFile.write(playerName + ":  " + Game.getScore() + "  " + timeStamp +'\n');
                            outFile.close();
                            JOptionPane.showMessageDialog(null, "Score saved!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                break;
            }
            if(botHits >= HITS_TO_WIN) {
                launchPlayerView.dispose();
                String message = String.format("Bot wins! Hit accuracy: %f. Save game?",playerGuessBoard.getAccuracy());
                int response = JOptionPane.showConfirmDialog(null, message, "Game over.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION){
                    //Save the information.
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    // Create a text field for the player name
                    JTextField playerNameField = new JTextField();
                    Object[] fields = {"Player Name:", playerNameField};

                    // Display a dialog box with the text field for the player name
                    int result = JOptionPane.showConfirmDialog(null, fields, "Enter Player Name", JOptionPane.OK_CANCEL_OPTION);

                    // Check if the user clicked "OK"
                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            // Get the player name from the text field
                            String playerName = playerNameField.getText();

                            // Save the player name to the file
                            FileWriter outFile = new FileWriter("src/Files/battleship_score.txt", true);
                            outFile.write(playerName + ":  " + Game.getScore() + "  " + timeStamp +'\n');
                            outFile.close();
                            JOptionPane.showMessageDialog(null, "Score saved!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
        
        // TODO: Add endgame screen that prints stats
       return true;
    }
    
    /** Function that handles all the board logic when a user guesses on the board */
    private void processTurn()
    {
        // First, get input from the user and make sure it's not a square they've guessed before
        do {
        this.updateScore();    
        coordinate = launchPlayerView.getUserInput();
        } while(!(playerGuessBoard.getTile(coordinate[0], coordinate[1]) == 'E'));
        
            // If the player has a hit, and hasn't guessed that tile yet
            if(botShipBoard.getTile(coordinate[0], coordinate[1]) != 'E') {
                playerGuessBoard.updateBoard(coordinate[0], coordinate[1], 'H');
                playerHits++;
            }
            else 
                playerGuessBoard.updateBoard(coordinate[0], coordinate[1], 'M');

            // Next, the bot. The bot won't guess the same square twice
            coordinate = botGuessBoard.generateGuess();
            if(playerShipBoard.getTile(coordinate[0], coordinate[1]) != 'E') {
                botGuessBoard.updateBoard(coordinate[0], coordinate[1], 'H');
                botHits++;
            }
            else
                botGuessBoard.updateBoard(coordinate[0], coordinate[1], 'M');
    }
    
    public void updateScore(){
        System.out.println((playerGuessBoard.getAccuracy()));
        playerScore = ((playerHits)*100*(int)(playerGuessBoard.getAccuracy()));
    }
    
    public static int getScore (){
        return playerScore;
    }
}

