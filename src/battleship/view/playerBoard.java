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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

public class playerBoard extends JFrame {
    
    public JMenuBar menuBar = new JMenuBar();
    public JMenu menu = new JMenu("Menu");
    public JMenuItem saveMenuItem = new JMenuItem("Save");
    public JMenuItem quitMenuItem = new JMenuItem("Quit");
    public JMenuItem homeMenuItem = new JMenuItem("Home");

    private JPanel friendlyBoatsPanel;
    private JPanel botGuessPanel;
    
    public static JButton[][] friendlyBoatsButtons;
    public static JButton[][] botGuessButtons;
    
    public playerBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //Displays both the bot and user boards.
        friendlyBoatsPanel = new JPanel();
        friendlyBoatsPanel.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        friendlyBoatsPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        
        friendlyBoatsButtons = new JButton[10][10];
        
        botGuessPanel = new JPanel();
        botGuessPanel.setLayout(new GridLayout(10, 10, 0, 0));
        botGuessPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        
        botGuessButtons = new JButton[10][10];

        // Add buttons to friendlyBoatsPanel and botGuessPanel
        Font font = new Font("Arial", Font.BOLD, 12);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                friendlyBoatsButtons[row][col] = new JButton();
                botGuessButtons[row][col] = new JButton();
                friendlyBoatsButtons[row][col].setEnabled(false);
                botGuessButtons[row][col].setEnabled(false);
                
                friendlyBoatsButtons[row][col].setFont(font);
                botGuessButtons[row][col].setFont(font);
                
                MouseListener mouseAdapter = null;
                friendlyBoatsButtons[row][col].addMouseListener(mouseAdapter);
                
                friendlyBoatsPanel.add(friendlyBoatsButtons[row][col]);
                botGuessPanel.add(botGuessButtons[row][col]);

            }
        }
   
        getContentPane().add(friendlyBoatsPanel, BorderLayout.WEST);
        getContentPane().add(botGuessPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        //Create and the menu bar for the game.
        menu.add(saveMenuItem);
        menu.add(quitMenuItem);
        menu.add(homeMenuItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
        
        JLabel friendlyBoardLabel = new JLabel("Player Board");
        friendlyBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        menuBar.add(friendlyBoardLabel);
        
        // Menu -> Quit action listener
        quitMenuItem.addActionListener(e -> System.exit(0));

        // Add action listener to save menu item
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter outFile = new FileWriter("battleship_score.txt", true);
                    outFile.write("Add score here");
                    outFile.close();
                    JOptionPane.showMessageDialog(null, "Score saved!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JButton[][] getFriendlyBoardButtons() {
        return friendlyBoatsButtons;
    }

    public JButton[][] getBotBoardButtons() {
        return botGuessButtons;
    }
    
    
    
}
