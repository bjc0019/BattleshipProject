/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship.view;

/**
 *
 * @author joshpohly
 */
import battleship.FriendlyBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

public class botBoard extends JFrame {
    
    public JMenuBar menuBar = new JMenuBar();

    private JPanel botBoatsPanel;
    private JPanel friendlyGuessPanel;
    
    public static JButton[][] botBoatsButtons;
    public static JButton[][] friendlyGuessButtons;
    
    public botBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //Displays both the bot and user boards.
        botBoatsPanel = new JPanel();
        botBoatsPanel.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        botBoatsPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        
        botBoatsButtons = new JButton[10][10];
        
        friendlyGuessPanel = new JPanel();
        friendlyGuessPanel.setLayout(new GridLayout(10, 10, 0, 0));
        friendlyGuessPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        friendlyGuessButtons = new JButton[10][10];

        // Add buttons to friendlyBoatsPanel and botGuessPanel
        Font font = new Font("Arial", Font.BOLD, 12);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                botBoatsButtons[row][col] = new JButton();
                friendlyGuessButtons[row][col] = new JButton();
                botBoatsButtons[row][col].setEnabled(false);
                friendlyGuessButtons[row][col].setEnabled(false);
                
                botBoatsButtons[row][col].setFont(font);
                friendlyGuessButtons[row][col].setFont(font);
                
                MouseListener mouseAdapter = null;
                botBoatsButtons[row][col].addMouseListener(mouseAdapter);
                
                botBoatsPanel.add(botBoatsButtons[row][col]);
                friendlyGuessPanel.add(friendlyGuessButtons[row][col]);
            }
        }
   
        getContentPane().add(botBoatsPanel, BorderLayout.WEST);
        getContentPane().add(friendlyGuessPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        

        setJMenuBar(menuBar);
        
        JLabel friendlyBoardLabel = new JLabel("Bot Board");
        friendlyBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        menuBar.add(friendlyBoardLabel);

    }
    
    public JButton[][] getFriendlyBoardButtons() {
        return botBoatsButtons;
    }

    public JButton[][] getBotBoardButtons() {
        return friendlyGuessButtons;
    }
    
    
    
}
