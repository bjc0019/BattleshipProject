/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleship.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class menuBoard extends JFrame {
    private JPanel menuPanel;
    private Semaphore playgameSemaphore;
    
    public menuBoard(Semaphore playgameSemaphore) {    // Takes a semaphore to control the continuation of calling code until play game is pressed.
        setTitle("Start Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.playgameSemaphore = playgameSemaphore;
        
        // Create the menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));
        menuPanel.setPreferredSize(new Dimension(200, 300));
        
        // Create the menu buttons
        JButton exitButton = new JButton("Exit");
        JButton playButton = new JButton("Play Game");
        JButton leaderboardButton = new JButton("Leader Board");
        
        // Add action listeners to the buttons
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               playgameSemaphore.release();
            }
        });
        
        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leaderBoard launchLeaderBoard = new leaderBoard();
            }
        });
        
        // Add the buttons to the menu panel
        menuPanel.add(playButton);
        menuPanel.add(leaderboardButton);
        menuPanel.add(exitButton);
        
        // Add the menu panel to the frame
        getContentPane().add(menuPanel, BorderLayout.CENTER);
        
        // Set the preferred size and make the frame visible
        setSize(200, 300);
        setVisible(true);
        
        // This try catch block tries to acquire a playgameSemaphore but won't unless play game is pressed, thus interupting the execution of the calling code
        try {                            
            playgameSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
