/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 *
 * @author lotta
 */
public class ViMain {
    
    public void generateFrames() {
        JFrame myFrame;
        JPanel myPanel;
        JButton initiateButton;
        
        myFrame = generateFrame();
        myPanel = generatePanel();
        initiateButton = new JButton("Start");
        myPanel.add(initiateButton);
        myFrame.getContentPane().setLayout(new FlowLayout());
        myFrame.getContentPane().add(myPanel);
        myFrame.setVisible(true);
    }
    
    private JFrame generateFrame() {
        JFrame myFrame;
        myFrame = new JFrame("Gloskampen");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 500);
        return myFrame;
    }  
    
    private JPanel generatePanel() {
        GridLayout gridLayout = new GridLayout(3, 3);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(gridLayout);
        return myPanel;
    }
}
