/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for handling the control part of a glossary quiz.
 * @author lotta
 */
public class GlossaryController {
    
    private int numberOfExecutedGlossaries;
    private int numberOfFailedGlossaries;
    private int numberOfTrialsEachTest;
    private int currentNumberOfTrials;
    private String currentRightAnswer;
    private final int totNumberOfGlossaries;
    private gloskampen.model.Glossary glossary;
    gloskampen.view.MainView mainView;
    
    
    /**
     * Constructor that makes a new quiz
     * @param mainView Handle to main view object
     * @param totNumWordsInTest Total number of words in a test
     * @param wordList Handle to word list object
     */
    public GlossaryController(gloskampen.view.MainView inMainView, 
            int totNumWordsInTest)  {
        
        totNumberOfGlossaries = totNumWordsInTest;
        numberOfFailedGlossaries = 0;
        numberOfExecutedGlossaries = 0;
        numberOfTrialsEachTest = 0;            
        mainView = inMainView;
    }
    
    /**
     * Initiates the actionListeners for the glossary test parts for the pro-
     * gramme
     * @param mainView 
     */
    public void intiateGlossaryControlerGame(gloskampen.view.MainView mainView)  
    {
        
        mainView.initiateButton.addActionListener(new ActionListener() {
                
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
            }
        }); 
        
        mainView.nextButton.addActionListener(new ActionListener() {
                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               newGlossary();
            }
        }); 
        
        System.out.println("L8 to generate a glossary object");
        glossary = new gloskampen.model.Glossary();
    }
    
    /**
     * Asks for a new word from the glossary model object. If already reached 
     * totNumberOfglossaries one of the failed is returned instead
     * @return The word to be translated
     */
    public String getNewGlossary() {
        String word;
        word = "";
        
        //TODO: Check for failed array if numberOfExecutedGlossaries are done.
        //TODO: Check if currentNumberOfTrials is less than numberOfTrialsEachTest:
        //If it is the same glossary should be returned.
        numberOfExecutedGlossaries++;
        
        //TODO: Get glossary and the answer from model. Store the correct answer
        //in currentRightAnswer
        word = glossary.randomiseOneGlossary();
        
        return word;
    }
    
    public void setWordList(gloskampen.model.WordList tobeWordList) {
        System.out.println("In controller set wordList");
        glossary.setWordList(tobeWordList);
    }
    
    /**
     * Checks if the test is finished or not (failed test cases should have been
     * shown once again before the test is finished) 
     * @return true if test is finished. Otherwise false
     */
    Boolean checkEndOfTest() {
        Boolean end;
        end = false; //Intitiate to false and checnge only if should
        if (numberOfExecutedGlossaries == (totNumberOfGlossaries + 
                numberOfFailedGlossaries)) {
            end = true;
        }
        return end;
    }
    
    /**
     * Validates a glossary that the user has entered.
     * @param triedGlossary The glossary to check
     * @return Empty string if glossary was correct else the correct glossary
     */
    public String validateGlossary(String triedGlossary) {
        String correct;
        
        if (currentRightAnswer.equals(triedGlossary)) {
            correct = "";
            currentNumberOfTrials = 0;
        } 
        else {
            correct = currentRightAnswer;
            currentNumberOfTrials++;
        }
        return correct;
    }
    
    /**
     * Get percent of test that is done. This does not include extra tests to 
     * be done which was failed first
     * @return Percent of tests that has been done
     */
    public int getPercentDone() {
        int percent;
        percent = numberOfExecutedGlossaries/totNumberOfGlossaries;
        return percent;
    }
    
    /**
     * Gets number of failed test cases to be shown for user
     * @return Number of failed test cases
     */
    public int getNumberOfFailedGlossaries() {
        return numberOfFailedGlossaries;
    }
    
    /**
     * Gets total number of glossaries in test. To be shown for user
     * @return Total number of glossaries in test (not included extra glossaries
     * due to failed ones.
     */
    public int getTotNumberOfGlossaries() {
        return totNumberOfGlossaries;
    }
   
    /**
     * Sets to- and from language and level for quiz.
     * @param fromLanguage language to translate from
     * @param toLanguage language to translate to
     * @param level level for test
     * @param numberOfTrials number of trials for a glossary 
     */
    public void setLanguagesAndLevelTrials(int fromLanguage, int toLanguage, 
            int level, int numberOfTrials) {
        numberOfTrialsEachTest = numberOfTrials;
        //TODO Do a wrapper which sets this into model glossary
    }
    
    /**
     * Validates the user name for correct syntax
     * @return true if syntax is correct. Otherwise false
     */
    public Boolean validateUserName() {
        
        //TODO: Some code;.-)
        return true;
    }
    
    
    private void newGlossary() {
        int validAnswer; 
        
        String answer;
        answer = mainView.getAnswer();
        validAnswer = validateAnswer(answer);
        
        if (validAnswer >= 0) {
            String newGlossary;
            mainView.setErrorText("");
            newGlossary = getNewGlossary();
            mainView.setNewGlossary(newGlossary);
        }
    }
    
    private int validateAnswer(String answer) {
        int result;
        result = 0;
        
        if (answer.isEmpty()) {
            mainView.setErrorText("You must provide a text");
            result = -1;
        }
        return result;
    }
    
}
