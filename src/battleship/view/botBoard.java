/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship.view;

/**
 *
 * @author joshpohly
 */
import battleship.model.FriendlyBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the AI/Bot board.
 */
public class botBoard extends JFrame {
    
    public JMenuBar menuBar = new JMenuBar();

    private JPanel botPanelLeft;
    private JPanel botPanelRight;
    
    public static JButton[][] botPanelButtonsLeft;
    public static JButton[][] botPanelButtonsRight;
    
    /**
     * Constructor for the bot boards.
     */
    public botBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //Displays both the bot and user boards.
        botPanelLeft = new JPanel();
        botPanelLeft.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        botPanelLeft.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        
        botPanelButtonsLeft = new JButton[10][10];
        
        botPanelRight = new JPanel();
        botPanelRight.setLayout(new GridLayout(10, 10, 0, 0));
        botPanelRight.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        
        botPanelButtonsRight = new JButton[10][10];

        // Add buttons to friendlyBoatsPanel and botGuessPanel
        Font font = new Font("Arial", Font.BOLD, 12);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                botPanelButtonsLeft[row][col] = new JButton();
                botPanelButtonsRight[row][col] = new JButton();
                botPanelButtonsLeft[row][col].setEnabled(false);
                botPanelButtonsRight[row][col].setEnabled(false);
                
                botPanelButtonsLeft[row][col].setFont(font);
                botPanelButtonsRight[row][col].setFont(font);
                
                MouseListener mouseAdapter = null;
                botPanelButtonsLeft[row][col].addMouseListener(mouseAdapter);
                
                botPanelLeft.add(botPanelButtonsLeft[row][col]);
                botPanelRight.add(botPanelButtonsRight[row][col]);
            }
        }
   
        getContentPane().add(botPanelLeft, BorderLayout.WEST);
        getContentPane().add(botPanelRight, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        

        setJMenuBar(menuBar);
        
        JLabel friendlyBoardLabel = new JLabel("Bot Board");
        friendlyBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        menuBar.add(friendlyBoardLabel);

    }
    
    /**
     * Returns the boat locations for the AI's ships. This will not be visible to the user.
     * @return 
     */
    public JButton[][] getFriendlyBoardButtons() {
        return botPanelButtonsLeft;
    }

    /**
     * Returns the hit/miss board for the AI. This will not be visible to the user.
     * @return 
     */
    public JButton[][] getBotBoardButtons() {
        return botPanelButtonsRight;
    }
}
