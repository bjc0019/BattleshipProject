/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship.view;

/**
 *
 * @author joshpohly
 */
import battleship.control.Game;
import battleship.model.FriendlyBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.text.SimpleDateFormat;
import java.util.Date;

public class playerBoard extends JFrame {
    
    public JMenuBar menuBar = new JMenuBar();
    public JMenu menu = new JMenu("Menu");
    public JMenuItem saveMenuItem = new JMenuItem("Save");
    public JMenuItem quitMenuItem = new JMenuItem("Quit");
    public JMenuItem homeMenuItem = new JMenuItem("Home");
    public int[] last_coordinate_selected = {0,0};

    private JPanel friendlypanelLeft;
    private JPanel friendlypanelRight;
    private Semaphore currentSemaphore;
    
    public static JTextArea textArea = new JTextArea(10, 30);
    
    public static JButton[][] friendlyPanelButtonsLeft;
    public static JButton[][] friendlyPanelButtonsRight;
    
    public playerBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //displays both the bot and user boards.
        friendlypanelLeft = new JPanel();
        friendlypanelLeft.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        friendlypanelLeft.setPreferredSize(new Dimension(450, 300)); // Set preferred size
        
        friendlyPanelButtonsLeft = new JButton[10][10];
        
        friendlypanelRight = new JPanel();
        friendlypanelRight.setLayout(new GridLayout(10, 10, 0, 0));
        friendlypanelRight.setPreferredSize(new Dimension(450, 300)); // Set preferred size
        
        friendlyPanelButtonsRight = new JButton[10][10];

        // Add buttons to friendlypanelLeft and friendlypanelRight
        Font font = new Font("Arial", Font.BOLD, 12);
        for (int rowc = 0; rowc < 10; rowc++) {
            for (int col = 0; col < 10; col++) {
                int row = rowc;
                friendlyPanelButtonsLeft[row][col] = new JButton();
                friendlyPanelButtonsRight[row][col] = new JButton();
                
                friendlyPanelButtonsLeft[row][col].setFont(font);
                friendlyPanelButtonsRight[row][col].setFont(font);
                
                //this is a 90 degree rotation that maps 0,0 in the button grid to 0,0 in the boat grid.
                //0,0 in the button grid is in the top-left, while 0,0 in the main grid is in the bottom-left.
                final int frow = col;
                final int fcol = 9 - row;

                //add an acion listener for each button.
                friendlyPanelButtonsRight[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        currentSemaphore.release();
                        last_coordinate_selected[0] = frow;
                        last_coordinate_selected[1] = fcol;
                    }
                });
                friendlyPanelButtonsLeft[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        currentSemaphore.release();
                        last_coordinate_selected[0] = frow;
                        last_coordinate_selected[1] = fcol;
                    }
                });
                
                friendlypanelLeft.add(friendlyPanelButtonsLeft[row][col]);
                friendlypanelRight.add(friendlyPanelButtonsRight[row][col]);

            }
        }
   
        setJMenuBar(menuBar);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        getContentPane().add(friendlypanelLeft, BorderLayout.SOUTH);
        getContentPane().add(friendlypanelRight, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
           
        //Create and the menu bar for the game.
        menu.add(saveMenuItem);
        menu.add(quitMenuItem);
        menu.add(homeMenuItem);
        menuBar.add(menu);

        
        
        
        JLabel friendlyBoardLabel = new JLabel("Player Board");
        friendlyBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        menuBar.add(friendlyBoardLabel);
        
        // Menu -> Quit action listener
        quitMenuItem.addActionListener(e -> System.exit(0));
       
        // Add action listener to save menu item
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        });
        
        homeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
               //menuBoard launchNewMenu = new menuBoard(playgameSemaphore);
            }
        });
        
    }

    public JButton[][] getFriendlyBoardButtons() {
        return friendlyPanelButtonsLeft;
    }

    public JButton[][] getBotBoardButtons() {
        return friendlyPanelButtonsRight;
    }
    
    public int[] getUserInput(){
        // Use a semaphore to delay the execution until there's a response.
        currentSemaphore = new Semaphore(0);
        try{
            currentSemaphore.acquire();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Coordinate returned:");
        System.out.println(last_coordinate_selected[0]);
        System.out.println(last_coordinate_selected[1]);
        return last_coordinate_selected;
    }
    
}
