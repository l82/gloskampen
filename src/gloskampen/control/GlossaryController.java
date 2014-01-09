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
public class GlossaryController implements Callback
{    
    private gloskampen.model.Glossary glossary;
    gloskampen.view.MainView mainView;    
    private Boolean writeWordSelf;
    private Boolean timerStarted;
    Timer timer;
    
    
    /**
     * Constructor that makes a new quiz
     * @param inMainView Handle to main view object
     * @param totNumWordsInTest Total number of words in a test
     */
    public GlossaryController(gloskampen.view.MainView inMainView, int totNumWordsInTest)
    {              
        mainView = inMainView;
        writeWordSelf = true;
        timerStarted = false;
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
                startTimer();
            }
        }); 
        
        inMainView.nextAddListener(new ActionListener() 
        {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               checkAndGenerateNewGlossary();
               stopTimer();
               startTimer();
               //System.out.println("L8 next " + mainView.getFeedbackText());
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
               stopTimer();
            }
        }); 
        
        inMainView.comBoxThreeWordsAddListener(new ActionListener()
         {                
            @Override
            public void actionPerformed(ActionEvent e)
            {
               setTextFromComboAlternative();
            }
        }); 
                
        glossary = new gloskampen.model.Glossary();
    }
    
    private void setGameSettings() {
        Boolean moreThanOneTrial;
        int toLanguage, fromLanguage;
        
        moreThanOneTrial = mainView.isMoreOneTrial();
        writeWordSelf = mainView.isWriteWordSelected();
        
        if (moreThanOneTrial) {
            glossary.setNumberOfTrialsEachTest(3);
        }
        else {
            glossary.setNumberOfTrialsEachTest(1);
        }
        
        toLanguage = mainView.getToLanguage();
        fromLanguage = mainView.getFromLanguage();
        glossary.setLanguages(fromLanguage, toLanguage);
        
        System.out.println("L8 game settings are: writeWordsSelf=" + writeWordSelf +
                " moreThanOneTrial=" + moreThanOneTrial + " fromLanguage=" + 
                fromLanguage + " toLanguage=" + toLanguage);
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
     
        //System.out.println("L8 checkAndGenerateNewAnswer");
        answer = mainView.getAnswer();
        validAnswer = validateCorrectSyntaxAnswer(answer);
        
        if (validAnswer >= 0) { 
            printAndFixNextStep(answer, false);
        }
        //System.out.println("L8 checkAndGenerateNewGlossary feedback " + mainView.getFeedbackText());
    }     
   
    private void printAndFixNextStep(String answer, Boolean timeout) 
    {
        String errorText;
        Boolean newTrial;
        Boolean continueWithNextGlossary;
        
        errorText = generateResult(answer, timeout);
        newTrial = glossary.getNewTrial(); //Must be executed after generateResult
        
        //Answer is correct
        if (errorText.equals("")) {
            printOkMessage();
            continueWithNextGlossary = true;
            glossary.resetCurrentNumberOfTrials();
        }
        else if (newTrial) { //A new trial should be done
            if (timeout == false) {
                errorText = "Tyvärr, " + answer + " är inte rätt. Försök igen!";    
            }
            else {
                startTimer();
                errorText = "Tyvärr frågan timade ut. Försök igen";
            }
            //System.out.println("L8 should write error text and new trial should be done" + errorText);
            mainView.setErrorText(errorText);
            mainView.setEmptyAnswer();
            continueWithNextGlossary = false;
            //System.out.println("L8 should write error text and new trial should be done " + mainView.getFeedbackText());
        }
        else { //Answer is not ok but max number of trials are reached
            String currentRightAnswer = glossary.getCorrectAnswer();
            if (timeout == false) {
                errorText = "Tyvärr, " + answer + " är inte rätt. Rätt svar är " + currentRightAnswer + "!";    
            }
            else {
                errorText = "Tyvärr frågan timade ut.";
            }
            mainView.setErrorText(errorText);
            //System.out.println("L8 Answer is not ok but max number of trials are reached " + mainView.getFeedbackText());
            mainView.setEmptyAnswer();
            continueWithNextGlossary = true;
            glossary.resetCurrentNumberOfTrials();
        }
           
        if (continueWithNextGlossary) {
            printDataForNextStep();
        }
        //System.out.println("L8 feedback " + mainView.getFeedbackText());
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
            
            removeWordChoices();
            if (writeWordSelf != true) {
                setWordChoices();
            }
         }
    }
    
    private void setWordChoices() {
        ArrayList<String> wordAlternatives;
        wordAlternatives = glossary.randomiseThreeAlternatives();
        for (int i = 0; i < wordAlternatives.size(); i++) 
        {
            mainView.setWordChoice(wordAlternatives.get(i));
        }
    }
    
    private void removeWordChoices() 
    {
        if (!writeWordSelf) {
            mainView.emptyWordChoices();
        }
    }
    
    private void printOkMessage() {
        String currentRightAnswer = glossary.getCorrectAnswer();             
        mainView.setCorrectText("Bra! Rätt svar är " + currentRightAnswer + "!");
        mainView.setEmptyAnswer();
    }
    
    private String generateResult(String answer, Boolean timeout) 
    {
        String correctAnswer;
        String errorText;
        
        correctAnswer = glossary.validateGlossary(answer);
        if (timeout == false) {
            
            if  (correctAnswer.equals("")) 
            {
                errorText = "";
            }
            else 
            {
                errorText = "Du svarade '" + answer + "'. Rätt svar är " +
                        correctAnswer + ".";
            }    
        }
        else {
            errorText = "Frågan timade ut. ";
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

        int numberOfExecutedGlossaries;
        String newGlossary;
        newGlossary = generateNewGlossary();
        numberOfExecutedGlossaries = glossary.getNumberOfExecutedGlossaries() + 1;
        mainView.setGlossaryNumber(numberOfExecutedGlossaries);
        mainView.setNewGlossary(newGlossary);
        if (writeWordSelf != true) 
        {
            setWordChoices();
        }
    }
        
    private void resetFieldsAndValues()
    {       
        glossary.resetNumberOfFailedGlossaries();
        glossary.resetListFailedCounter();
        glossary.resetNumberOfExecutedGlossaries();    
        glossary.resetCurrentNumberOfTrials();
        glossary.emptyListOfFailedWords();
        glossary.emptyListOfAlreadyUsedWords();
        mainView.enableAnswerButton();
        mainView.setEmptyEndText();
        mainView.setEmptyFeedbackText();     
        mainView.emptyWordChoices();
    }    

    private String generateNewGlossary() 
    {
        String newGlossary;
        newGlossary = getNewGlossary();
        return newGlossary;
    }    
    
    private void startTimer() {
        
        if (timerStarted == false) {
            timer = new Timer(20000, this);
            timerStarted = true;
            timer.start();
        }
        else {
            System.out.println("Previous timer not stopped. An error in code!");
        }
    }
    
    private void stopTimer() {
        
        if (timerStarted == true) {
            timerStarted = false;
            try {
                timer.interrupt();
                timer.join();
            } 
            catch (IllegalThreadStateException | InterruptedException itse) {
                System.out.println("Error in glossaryController: " + itse.getMessage());
            }
        }
        else {
            System.out.println("Previous timer was already stopped. An error in code!");
        }
    }
    
    @Override
    public void callback() {
        String enteredChars;
        
        stopTimer();
        enteredChars = mainView.getAnswer();
        printAndFixNextStep(enteredChars, true);
    }
    
    private void  setTextFromComboAlternative() {
        String answer;
        answer = mainView.getWordText();
        mainView.setAnswerText(answer);
    }
}