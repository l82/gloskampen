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
        
        inMainView.addEndGameListener(new ActionListener() 
        {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               String user;
               user = mainView.getUser();
               //user.checkAndAddUser(user)
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
       
        answer = mainView.getAnswer();
        validAnswer = validateCorrectSyntaxAnswer(answer);
        
        if (validAnswer >= 0) 
            printAndFixNextStep(answer);
        }
        
    private void printAndFixNextStep(String answer) 
    {
        String errorText;
        Boolean newTrial;
        Boolean continueWithNextGlossary;
        
        errorText = generateResult(answer);
        newTrial = glossary.getNewTrial(); //Must be executed after generateResult
        continueWithNextGlossary = false;
        
        //Answer is correct
        if (errorText.equals("")) {
            printOkMessage();
            continueWithNextGlossary = true;
            glossary.resetCurrentNumberOfTrials();
        }
        else if (newTrial) { //A new trial should be done
            errorText = "Tyvärr, " + answer + " är inte rätt. Försök igen!";    
            mainView.setErrorText(errorText);
            mainView.setEmptyAnswer();
            continueWithNextGlossary = false;
        }
        else { //Answer is not ok but max number of trials are reached
            String currentRightAnswer = glossary.getCorrectAnswer();    
            errorText = "Tyvärr, " + answer + " är inte rätt. Rätt svar är " + currentRightAnswer + "!";    
            mainView.setErrorText(errorText);
            mainView.setEmptyAnswer();
            continueWithNextGlossary = true;
            glossary.resetCurrentNumberOfTrials();
        }
           
        if (continueWithNextGlossary) {
            printDataForNextStep();
        }
    }
    
    private void printDataForNextStep() {
        if (glossary.checkEndOfTest()) {
            mainView.setEndText(glossary.getTotNumberOfGlossaries(), 
                (glossary.getTotNumberOfGlossaries() - glossary.getNumberOfFailedGlossaries()));
            mainView.setNewGlossary("");
            mainView.setEmptyAnswer();    
            mainView.disableAnswerButton();
        }
        else {
            String newGlossary;
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
    }
    
    private void printOkMessage() {
        String currentRightAnswer = glossary.getCorrectAnswer();             
        mainView.setCorrectText("Bra! Rätt svar är " + currentRightAnswer + "!");
        mainView.setEmptyAnswer();
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
            errorText = "Du svarade '" + answer + "'. Rätt svar är " +
                    correctAnswer + ".";
        }    
        return(errorText);
    }
       
    private int validateCorrectSyntaxAnswer(String answer) 
    {
        int result;
        result = 0;

        if (answer.isEmpty()) 
        {
            mainView.setErrorText("Du måste skriva något");
            result = -1;
        }
        if (answer.equals("")) 
        {
            mainView.setErrorText("Du måste skriva något");
            result = -1;
        }
        return result;
    }
        
    private void generateNewGame() 
    {
        resetFieldsAndValues();
        
        ArrayList<String> threeWords;
        int numberOfExecutedGlossaries;
        String newGlossary;
        newGlossary = generateNewGlossary();
        numberOfExecutedGlossaries = glossary.getNumberOfExecutedGlossaries() + 1;
        mainView.setGlossaryNumber(numberOfExecutedGlossaries);
        mainView.setNewGlossary(newGlossary);
        if (writeWordSelf != true) 
        {
            threeWords = glossary.randomiseThreeAlternatives();
            mainView.setWordAlternatives(threeWords);
        }
    }
        
    private void resetFieldsAndValues()
    {       
        glossary.resetNumberOfFailedGlossaries();
        glossary.resetNumberOfExecutedGlossaries();     
        glossary.resetCurrentNumberOfTrials(); //L8 fixed
        mainView.enableAnswerButton();
        mainView.setEmptyEndText();
        mainView.setEmptyFeedbackText();     
    }    

    private String generateNewGlossary() 
    {
        String newGlossary;
        newGlossary = getNewGlossary();
        return newGlossary;
    }    
}