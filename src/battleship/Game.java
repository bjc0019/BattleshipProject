package battleship;
import java.util.Objects;
import java.util.Random;
import myExceptions.IndexOutOfBounds;
import terminalGUI.debugGUI;

public class Game {
    public static void main(String[] args) {
        while(true) {
            // Variable Declaration
            FriendlyBoard playerShipBoard = new FriendlyBoard();
            OpponentBoard playerGuessBoard = new OpponentBoard();

            FriendlyBoard botShipBoard = new FriendlyBoard();
            OpponentBoard botGuessBoard = new OpponentBoard();

            int playerHits = 0, botHits = 0;
            int[] coordinate;
            Random rng = new Random();

            final int hitsToWin = 17;

            /*
            Carrier carrier = new Carrier(2,1,true);
            BattleShip battleship = new BattleShip(1,1,true);
            Submarine submarine = new Submarine(4,4,true);
            Cruiser cruiser = new Cruiser(4,3, false);
            Destroyer destroyer = new Destroyer(7,6,true);
            */
            debugGUI gui = debugGUI.getInstance();
            //gui.placeShips(fboard);
            gui.printBoard(playerShipBoard, playerGuessBoard);


            // Get user input from main menu, and quit if that's what's selected
            String userInput = gui.printMainMenu();
            if(Objects.equals(userInput, "quit")) 
                break;
            
            // Allow the player to initialize their board and create the opponents
            //gui.placeShips(playerShipBoard);
            playerShipBoard.randomizeBoard();       // For testing, just randomize the player board cause placing ships in the terminal is a pain
            botShipBoard.randomizeBoard();          // Randomize opponent board
            
            
            // Start gameplay loop, guessing back and forth
            while(true) {
            // Display the board and get a guess from the player
            gui.printBoard(playerShipBoard,playerGuessBoard);
            //coordinate = gui.getGuess();
            coordinate = playerGuessBoard.generateGuess();
            
            // Update the boards with the player's guess, and generate one from the bot
            try {
                // First, process the player
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
                
            } catch(IndexOutOfBounds e) {
                System.out.println(e);
                System.exit(1);
            }
            
            // Print the board, and check to see if the game is over
            gui.printBoard(playerShipBoard, playerGuessBoard);
            if(playerHits >= hitsToWin) {
                System.out.println("Player Board:");
                gui.printBoard(playerShipBoard, playerGuessBoard);
                System.out.println("Bot Board:");
                gui.printBoard(botShipBoard, botGuessBoard);
                gui.gameOver(true, playerGuessBoard);
                break;
            }
            if(botHits >= hitsToWin) {
                gui.gameOver(false, botGuessBoard);
                break;
            }
            
            }
        }
    }  
}

