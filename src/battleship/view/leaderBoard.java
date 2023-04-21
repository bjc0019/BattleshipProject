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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * High scores are displayed here. Can be checked when player is at main menu.
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
    
    /**
     * The constructor for the leader board.
     */
    public leaderBoard() {

        setTitle("BattleShip");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create the GameBoard GUI, a 10x10 grid to play on that 
        //displays both the bot and user boards.
        leaderboardPanel = new JPanel();
        leaderboardPanel.setPreferredSize(new Dimension(600, 550)); // Set preferred size
        leaderboardPanel.add(fileWindow);
        
        JButton backButton = new JButton("Back");
        
        getContentPane().add(leaderboardPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Read contents of the file and display on leaderboardPanel
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Files/battleship_score.txt"));
            String line;
            StringBuilder string = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                string.append(line);
                string.append(System.lineSeparator());
            }
            fileWindow.setText(string.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        leaderboardPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JLabel friendlyBoardLabel = new JLabel("Score Board");
        friendlyBoardLabel.setHorizontalAlignment(JLabel.CENTER);
        menuBar.add(friendlyBoardLabel);
        
    }
}
