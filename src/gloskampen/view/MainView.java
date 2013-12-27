/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author lotta
 */
public class MainView {
    
    public JButton initiateButton, nextButton;
    
    public void generateFrames() {
        JFrame myFrame;
        JPanel startPanel, gamePanel;
        
        myFrame = generateFrame();
        startPanel = generatePanel(1, 1);
        gamePanel = generatePanel(1, 3);
        initiateButton = new JButton("Start");
        nextButton = new JButton("Next");
        JTextField answer = new JTextField("car");
        JLabel glossary = new JLabel("bil");
        startPanel.add(initiateButton);
        gamePanel.add(glossary);
        gamePanel.add(answer);
        gamePanel.add(nextButton);
        myFrame.getContentPane().setLayout(new FlowLayout());
        myFrame.getContentPane().add(startPanel);
        myFrame.getContentPane().add(gamePanel);
        myFrame.setVisible(true);
    }
    
    private JFrame generateFrame() {
        JFrame myFrame;
        myFrame = new JFrame("Gloskampen");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 500);
        return myFrame;
    }  
    
    private JPanel generatePanel(int numRows, int numCols) {
        GridLayout gridLayout = new GridLayout(numCols, numRows);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(gridLayout);
        return myPanel;
    }
    
    /**
     * Add a listener for the initiate button
     * @param listener listener to add
     */
    public void intiateAddListener(ActionListener listener) {
        initiateButton.addActionListener(listener);
    }
    
    /**
     * Add a listener for the next button
     * @param listener listener to add
     */
    public void nextAddListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
}
