/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship.view;
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
/**
 *
 * @author sbp0014
 */
public class leaderBoard extends JFrame{
    
    public JMenuBar menuBar = new JMenuBar();
    public JMenu menu = new JMenu("Menu");
    public JMenuItem saveMenuItem = new JMenuItem("Save");
    public JMenuItem quitMenuItem = new JMenuItem("Quit");
    public JMenuItem homeMenuItem = new JMenuItem("Home");
    public JTextArea fileWindow = new JTextArea(30, 50);
    public JButton backButton = new JButton("Back");

    private JPanel leaderboardPanel;
    private Semaphore currentSemaphore;
    
    public leaderBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //displays both the bot and user boards.
        leaderboardPanel = new JPanel();
        //leaderboardPanel.setLayout(new GridLayout(10, 10, 0, 0)); //set grid dimensions
        leaderboardPanel.setPreferredSize(new Dimension(550, 550)); // Set preferred size
        //fileWindow.setBounds(50, 50, 200, 200);
        leaderboardPanel.add(fileWindow);
        leaderboardPanel.add(backButton);
        
        getContentPane().add(leaderboardPanel, BorderLayout.NORTH);
        //getContentPane().add(backButton, BorderLayout.SOUTH);

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
    }
    /*
    public void getUserInput(){
        // Use a semaphore to delay the execution until there's a response.
        currentSemaphore = new Semaphore(0);
        try{
            currentSemaphore.acquire();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    */
}
