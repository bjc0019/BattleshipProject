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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

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
    
    public static JButton[][] friendlyPanelButtonsLeft;
    public static JButton[][] friendlyPanelButtonsRight;
    
    public playerBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //displays both the bot and user boards.
        friendlypanelLeft = new JPanel();
        friendlypanelLeft.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        friendlypanelLeft.setPreferredSize(new Dimension(250, 250)); // Set preferred size
        
        friendlyPanelButtonsLeft = new JButton[10][10];
        
        friendlypanelRight = new JPanel();
        friendlypanelRight.setLayout(new GridLayout(10, 10, 0, 0));
        friendlypanelRight.setPreferredSize(new Dimension(250, 250)); // Set preferred size
        
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
   
        getContentPane().add(friendlypanelLeft, BorderLayout.SOUTH);
        getContentPane().add(friendlypanelRight, BorderLayout.NORTH);

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
