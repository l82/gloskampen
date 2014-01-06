/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is responsible for handling the control part of a glossary quiz.
 * @author lotta
 */
public class GlossaryController 
{    
    
    private gloskampen.model.Glossary glossary;
    gloskampen.view.MainView mainView;    
    private Boolean writeWordSelf;
    
    
    /**
     * Constructor that makes a new quiz
     * @param inMainView Handle to main view object
     * @param totNumWordsInTest Total number of words in a test
     */
    public GlossaryController(gloskampen.view.MainView inMainView, int totNumWordsInTest)
    {        
               
        mainView = inMainView;
        writeWordSelf = true;
    }
       
    /**
     * Initiates the actionListeners for the glossary test parts for the program
     * @param inMainView 
     */
    public void intiateGlossaryControlerGame(gloskampen.view.MainView inMainView)  
    {
        
        inMainView.initiateAddListener(new ActionListener() 
        {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
                generateNewGame();
            }
        }); 
        
        inMainView.nextAddListener(new ActionListener() 
        {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               checkAndGenerateNewGlossary();               
            }
        }); 
        
        inMainView.confirmChoiceAddListener(new ActionListener() 
        {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               setGameSettings();      
            }
        }); 
        
        glossary = new gloskampen.model.Glossary();
    }
    
    private void setGameSettings() {
        writeWordSelf = mainView.isWriteWordSelected();
        glossary.setWriteWordSelf(writeWordSelf);
        glossary.setNumberOfTrialsEachTest(3);
    }
    
    /**
     * Asks for a new word from the glossary model object. If already reached 
     * totNumberOfglossaries one of the failed is returned instead
     * @return The word to be translated
     */
    public String getNewGlossary() 
    {
        String word;
        word = "";
        
        //TODO: Check for failed array if numberOfExecutedGlossaries are done.
        //TODO: Check if currentNumberOfTrials is less than numberOfTrialsEachTest:
        //If it is the same glossary should be returned.
        word = glossary.randomiseOneGlossary();
        return word;
    }
    
    public void setWordList(gloskampen.model.WordList tobeWordList) 
    {
        glossary.setWordList(tobeWordList);
    }
    
    /**
     * Get percent of test that is done. This does not include extra tests to 
     * be done which was failed first
     * @return Percent of tests that has been done
     */
    public int getPercentDone() 
    {
        int percent;
        percent = glossary.getPercentDone();
        return percent;
    }
    
    /**
     * Gets number of failed test cases to be shown for user
     * @return Number of failed test cases
     */
    public int getNumberOfFailedGlossaries() 
    {
        return glossary.getNumberOfFailedGlossaries();
    }
    
    /**
     * Gets total number of glossaries in test. To be shown for user
     * @return Total number of glossaries in test (not included extra glossaries
     * due to failed ones.
     */
    public int getTotNumberOfGlossaries() 
    {
        return glossary.getTotNumberOfGlossaries();
    }
    
    /**
     * Validates the user name for correct syntax
     * @return true if syntax is correct. Otherwise false
     */
    public Boolean validateUserName() 
    {        
        //TODO: Some code;.-)
        return true;
    }
    
    private void checkAndGenerateNewGlossary() 
    {
        int validAnswer; 
        String answer;
        String errorText;
        String newGlossary;
        int numberOfTrialsEachTest = glossary.getNumberOfTrialsEachTest();
        int currentTrial = glossary.getCurrentNumberOfTrials();
        Boolean newTrial;
       
        answer = mainView.getAnswer();
        validAnswer = validateAnswer(answer);
        
        if (validAnswer >= 0) 
        {
            errorText = generateResult(answer);
            newTrial = glossary.getNewTrial(); //Must be executed after generateResult
            
            if (errorText.equals("")) 
            {
                String currentRightAnswer = glossary.getCorrectAnswer();
                mainView.setCorrectText("Correct answer :-) " + currentRightAnswer);
                mainView.setEmptyAnswer();
            }
            else if (newTrial) {
                errorText = errorText + " Try again";
            }
            else {
                //Do nothing-already generated
            }
            mainView.setErrorText(errorText);
            
            
            if (!glossary.checkEndOfTest() && !newTrial) 
            {
                System.out.println("L8 no new trial and not end of test");
                int glossaryNumber = glossary.getNumberOfExecutedGlossaries() + 1;
                newGlossary = generateNewGlossary();
                mainView.setGlossaryNumber(glossaryNumber);
                mainView.setNewGlossary(newGlossary); 
                if (writeWordSelf != true) {
                    ArrayList<String> threeWords;
                    threeWords = glossary.randomiseThreeAlternatives();
                    mainView.setWordAlternatives(threeWords);
                }
            }
            else if (!newTrial)  
            {
                mainView.setEndText(glossary.getTotNumberOfGlossaries(), 
                        (glossary.getTotNumberOfGlossaries() - glossary.getNumberOfFailedGlossaries()));
                mainView.setNewGlossary("");
                mainView.setEmptyAnswer();
            }
            else {
                //Do nothing
            }
        }
    }
    
    private String generateResult(String answer) 
    {
        String correctAnswer;
        String errorText;
        
        correctAnswer = glossary.validateGlossary(answer);
        
        if  (correctAnswer.equals("")) 
        {
            errorText = "";
        }
        else 
        {
            errorText = "You entered " + answer + " correct answer is " +
                    correctAnswer;
        }    
        return(errorText);
    }
       
    private int validateAnswer(String answer) 
    {
        int result;
        result = 0;

        if (answer.isEmpty()) 
        {
            mainView.setErrorText("You must provide a text");
            result = -1;
        }
        if (answer.equals("")) 
        {
            mainView.setErrorText("You must provide a text");
            result = -1;
        }
        return result;
    }
        
    private void generateNewGame() 
    {
        ArrayList<String> threeWords;
        int numberOfExecutedGlossaries;
        String newGlossary;
        newGlossary = generateNewGlossary();
        numberOfExecutedGlossaries = glossary.getNumberOfExecutedGlossaries() + 1;
        mainView.setGlossaryNumber(numberOfExecutedGlossaries);
        mainView.setNewGlossary(newGlossary);
        if (writeWordSelf != true) {
            threeWords = glossary.randomiseThreeAlternatives();
            mainView.setWordAlternatives(threeWords);
        }
    }

    private String generateNewGlossary() 
    {
        String newGlossary;
        newGlossary = getNewGlossary();
        return newGlossary;
    }    
}