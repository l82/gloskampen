/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author lotta
 */
public class MainView {
    
    private JButton initiateButton, nextButton;
    private JLabel wordToTranslate, correctText, errorText, wordNo, endLabel; 
    private JTextField answer, selectOrWrite;
    private JComboBox fromLanguage, toLanguage, difficulty, numberOfTries;
    private ButtonGroup groupSelectOrWrite;
    private JRadioButton selectButton, writeButton;
    
    public void generateFrames() {
        JFrame myFrame;
        JPanel startPanel, gamePanel, fromLanguagePanel, toLanguagePanel, 
                difficultyPanel, numberOfTriesPanel, textMessagePanel, initiatePanel;
        JLabel fromLanguageLabel, toLanguageLabel, difficultyLabel, numberOfTriesLabel,
                selectOrWriteLabel;
        
        myFrame = generateFrame();
        startPanel = generatePanel();
        gamePanel = generatePanel();
        fromLanguagePanel = generatePanel();
        toLanguagePanel = generatePanel();
        difficultyPanel = generatePanel();
        numberOfTriesPanel = generatePanel();
        textMessagePanel = generatePanel();
        initiatePanel = generatePanel();
        
        initiateButton = new JButton("Start");
        nextButton = new JButton("Next");
        answer = new JTextField(20);
        wordToTranslate = new JLabel("");
        wordNo = new JLabel("");
        errorText = new JLabel();
        correctText = new JLabel();
        endLabel = new JLabel();
        fromLanguageLabel = new JLabel("Choose from language");
        toLanguageLabel = new JLabel("Choose to language");
        difficultyLabel = new JLabel("Choose difficulty");
        numberOfTriesLabel = new JLabel("Choose number of tries (1-3)");
        selectOrWriteLabel = new JLabel("Write or select");
        generateComboBoxes();
        generateRadioButtons();
        
        fromLanguagePanel.add(fromLanguageLabel);
        fromLanguagePanel.add(fromLanguage);
        toLanguagePanel.add(toLanguageLabel);
        toLanguagePanel.add(toLanguage);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficulty);
        numberOfTriesPanel.add(selectOrWriteLabel);
        numberOfTriesPanel.add(selectButton);
        numberOfTriesPanel.add(writeButton);
        initiatePanel.add(initiateButton);
        
        startPanel.add(fromLanguagePanel);
        startPanel.add(toLanguagePanel);
        startPanel.add(difficultyPanel);
        startPanel.add(numberOfTriesPanel);
        startPanel.add(initiatePanel);
        textMessagePanel.add(errorText);
        textMessagePanel.add(correctText);
        gamePanel.add(wordNo);
        gamePanel.add(wordToTranslate);
        gamePanel.add(answer);
        gamePanel.add(nextButton);
        gamePanel.add(endLabel);
        myFrame.getContentPane().setLayout(new GridLayout(4, 4));
        myFrame.getContentPane().add(startPanel);
        myFrame.getContentPane().add(textMessagePanel);
        myFrame.getContentPane().add(gamePanel);
        myFrame.setVisible(true);
    }
    
    private void generateRadioButtons() {
        groupSelectOrWrite = new ButtonGroup();
        selectButton = new JRadioButton("Select between choices");
        writeButton = new JRadioButton("Write self");
        groupSelectOrWrite.add(selectButton);
        groupSelectOrWrite.add(writeButton);
        writeButton.setSelected(true);
    }
    
    private void generateComboBoxes() {
        String[] languages = { "Svenska", "Engelska" };
        String[] difficultyLevel = { "Any", "Hard", "Middle", "Beginner" };
        String[] tries = { "1", "2", "3"};
        
        fromLanguage = new JComboBox(languages);
        toLanguage = new JComboBox(languages);
        fromLanguage.setSelectedIndex(0);
        toLanguage.setSelectedIndex(1);
        difficulty = new JComboBox(difficultyLevel);
        numberOfTries = new JComboBox(tries);
    }
    
    private JFrame generateFrame() {
        JFrame myFrame;
        myFrame = new JFrame("Gloskampen");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(600, 500);
        return myFrame;
    }  
    
    private JPanel generatePanel() {
        FlowLayout flowLayout = new FlowLayout();
        JPanel myPanel = new JPanel();
        myPanel.setLayout(flowLayout);
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
    
    public void setNewGlossary(String word) {
        wordToTranslate.setText(word);
    }
    
    public void setGlossaryNumber(int no) {
        String text;
        text = Integer.toString(no);
        wordNo.setText(text + ". ");
    }
    
    public String getAnswer() {
        String tobeAnswer;
        tobeAnswer = answer.getText();
        return tobeAnswer;
    }
    
    public void setErrorText(String errorMessage) {

        errorText.setForeground(Color.red);
        errorText.setText(errorMessage);
        System.out.println(errorMessage);
    }
    
    public void setCorrectText(String message) {

        errorText.setForeground(Color.blue);
        errorText.setText(message);
        System.out.println(message);
    }
    
    public void setEmptyAnswer() {
        answer.setText("");
    }
     
    public void setEndText(int tot, int correct) {
        String corrString, totString;
        corrString = Integer.toString(correct);
        totString = Integer.toString(tot);
        endLabel.setText("You had " + corrString + " correct answers of " + tot);
    }
}
