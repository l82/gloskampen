/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;
/**
 * This class is responsible to handle the quiz data
 * @author lotta
 */
public class Glossary {
    private int level;
    private int fromLanguage;
    private int toLanguage;
    private ArrayList<String> listOfAlreadyUsedWords;
    private String currentRightAnswer;
    private final int totNumberOfGlossaries;
    private ArrayList<String> listOfFailedWords;
    private int listFailedCounter;
    private gloskampen.model.WordList   wordListToUse;
    private int numberOfExecutedGlossaries;
    private int numberOfFailedGlossaries;
    private int numberOfTrialsEachTest;
    private int currentNumberOfTrials;
    private Boolean writeWordSelf;
    private static int totNumWordsInTest = 10;
    private Boolean lastIsCorrect;
    
    public Glossary() {
        totNumberOfGlossaries = totNumWordsInTest;
        numberOfFailedGlossaries = 0;
        numberOfExecutedGlossaries = 0;
        numberOfTrialsEachTest = 0; 
        listFailedCounter = 0;
        listOfFailedWords = new ArrayList<>();
        listOfAlreadyUsedWords = new ArrayList<>();
        currentNumberOfTrials = 1;
        writeWordSelf = true;
        lastIsCorrect = false;
    }
    
    public void setWordList(gloskampen.model.WordList wordList) {
        wordListToUse = wordList;
    }
    /**
     * Asks for a glossary that the user should test
     * @return The glossary that the pupils should translate
     */
    public String randomiseOneGlossary() {
       
        String randomisedGlossary, tmpBothWords;
        
        tmpBothWords = getRandomGlossary();
        randomisedGlossary = "";
        if (numberOfExecutedGlossaries < totNumWordsInTest) {
            System.out.println("L8 still below 10 " + numberOfExecutedGlossaries);
            listOfAlreadyUsedWords.add(tmpBothWords);
            randomisedGlossary = getWordToTranslateFromGlossary(tmpBothWords);
        }
        else {
             System.out.println("L8 should get one of the failed ones");
             tmpBothWords = listOfFailedWords.get(listFailedCounter);
             randomisedGlossary = getWordToTranslateFromGlossary(tmpBothWords);
             System.out.println("L8 failed glossary " + randomisedGlossary);
             listFailedCounter++;
        }
        
        return randomisedGlossary;
    }  
    
    private String getWordToTranslateFromGlossary(String wordPair) {
        String [] bothWords;
        String randomisedGlossary;
        bothWords = wordPair.split(";");
        randomisedGlossary = bothWords[0];
        currentRightAnswer = bothWords[1];
        return randomisedGlossary;
    }
    
    private String getWordToTranslateToGlossary(String wordPair) {
        String [] bothWords;
        String answer;
        bothWords = wordPair.split(";");
        answer = bothWords[1];
        return answer;
    }
    
    private String getRandomGlossary() {
        String tmpBothWords;
        Boolean alreadyUsed = true;
        
        tmpBothWords = "";
        while (alreadyUsed == true) {
            tmpBothWords = wordListToUse.getRandomWordL8();
            alreadyUsed = checkWordUsedAlready(tmpBothWords);
        }
        return tmpBothWords;
    }
    
    private Boolean checkWordUsedAlready(String word) {
        Boolean found = false;
        
        if (listOfAlreadyUsedWords.isEmpty()) {
            found = false;
        }
        else {
            found = checkAlreadyUsed(word);
        }
        return found;
    }
    
    private Boolean checkAlreadyUsed(String word) {
        Boolean found = false;
        String wordCombToTest;
        
        for (int i = 0; i < listOfAlreadyUsedWords.size(); i++) {
            wordCombToTest = listOfAlreadyUsedWords.get(i);
            if (wordCombToTest.equals(word)) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    /**
     * Validates a glossary that the user has entered.
     * @param triedGlossary The glossary to check
     * @return Empty string if glossary was correct else the correct glossary
     */
    public String validateGlossary(String triedGlossary) 
    {
        String correct;
        Boolean answerIsCorrect;
        
        if (currentRightAnswer.equals(triedGlossary)) 
        {
            correct = "";
            answerIsCorrect = true;
        } 
        else 
        {
            correct = currentRightAnswer;
            answerIsCorrect = false;
        }
        lastIsCorrect = answerIsCorrect;
        System.out.println("L8 lastiscorrect " + lastIsCorrect);
        setResultData(answerIsCorrect);
        return correct;
    }
    
    private void setResultData(Boolean answerIsCorrect) {
        
        if (answerIsCorrect) {
            currentNumberOfTrials = 0;
        }
        else {
            if (currentNumberOfTrials == 0) {
                int lastElement = listOfAlreadyUsedWords.size();
                String failedWord = listOfAlreadyUsedWords.get(lastElement-1);
                listOfFailedWords.add(failedWord);
                numberOfFailedGlossaries++;
            }
            currentNumberOfTrials++;
        }
        if (!getNewTrial()) {
            numberOfExecutedGlossaries++;
        }
    }
    
    /**
     * Randomises three alternatives for the glossary that should be tested
     * @return An arrayList with three alternatives
     */
    public ArrayList<String> randomiseThreeAlternatives() {
        ArrayList<String> randWords;
        String wordToAddNonSplitted, wordToAdd;
        randWords = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            wordToAddNonSplitted = wordListToUse.getRandomWordL8();
            wordToAdd = getWordToTranslateToGlossary(wordToAddNonSplitted);
            randWords.add(wordToAdd);
        }            
        int lastElement = listOfAlreadyUsedWords.size();
        wordToAddNonSplitted = listOfAlreadyUsedWords.get(lastElement-1);
        wordToAdd = getWordToTranslateToGlossary(wordToAddNonSplitted);
        randWords.add(wordToAdd);
        return randWords;
    }
    
    /**
     * Get a list of glossaries that the pupil answered wrong on. To be 
     * tested again when user has done all glossaries the first time
     * @return A list with failed glossaries
     */
    ArrayList<String> getAlreadyFailedGlossaries() {
        ArrayList<String> failedWords;
        failedWords = new ArrayList<>();
        return failedWords;
    }
    
    /**
     * Sets the result for a certain user
     * @param noOfFailed The number of failed glossaries
     * @param user The user to set the result for
     */
    public void setResult(int noOfFailed, String user) {
        
    }
    
    /**
     * Set the two languages and level that should be used for the test
     * @param from The language which should be translated from
     * @param to The language which should be translated to
     * @param qLevel The level of the quiz
     */
    public void setLanguagesAndLevel(int from, int to, int qLevel) {
        fromLanguage = from;
        toLanguage = to;
        level = qLevel;
    }
    
    /**
     * Checks if the test is finished or not (failed test cases should have been
     * shown once again before the test is finished) 
     * @return true if test is finished. Otherwise false
     */
    public Boolean checkEndOfTest() 
    {
        Boolean end;
        end = false; //Intitiate to false and change only if should
        if (numberOfExecutedGlossaries == (totNumberOfGlossaries + numberOfFailedGlossaries)) 
        {
            end = true;
        }
        return end;
    }
    
     /**
     * Get percent of test that is done. This does not include extra tests to 
     * be done which was failed first
     * @return Percent of tests that has been done
     */
    public int getPercentDone() 
    {
        int percent;
        percent = numberOfExecutedGlossaries/totNumberOfGlossaries;
        return percent;
    }
    
     /**
     * Gets number of failed test cases to be shown for user
     * @return Number of failed test cases
     */
    public int getNumberOfFailedGlossaries() 
    {
        return numberOfFailedGlossaries;
    }
    
    /**
     * Gets number of executed test cases to be shown for user
     * @return Number of failed test cases
     */
    public int getNumberOfExecutedGlossaries() 
    {
        return numberOfExecutedGlossaries;
    }
    
      /**
     * Gets total number of glossaries in test. To be shown for user
     * @return Total number of glossaries in test (not included extra glossaries
     * due to failed ones.
     */
    public int getTotNumberOfGlossaries() 
    {
        return totNumberOfGlossaries;
    }
    
    /**
     * Sets to- and from language and level for quiz.
     * @param inWriteWordSelf if word should be written or if it should be 
     * selected from a list
     */
    public void setWriteWordSelf(Boolean inWriteWordSelf) 
    {
        writeWordSelf = inWriteWordSelf;
    }
    
    public void setNumberOfTrialsEachTest(int inNumberOfTrials) {
        numberOfTrialsEachTest = inNumberOfTrials;
    }
    
    public String getCorrectAnswer() {
        return currentRightAnswer;
    }
    
    public int getNumberOfTrialsEachTest() {
        return numberOfTrialsEachTest;
    }
    
    public int getCurrentNumberOfTrials() {
        return currentNumberOfTrials;
    }
    
    public Boolean getNewTrial() {
        Boolean newTrial = false;  //Set to false and only changed if needed
        if ((currentNumberOfTrials < numberOfTrialsEachTest) && !lastIsCorrect) {
            newTrial = true;
        }       
        System.out.println("L8 getNewTrial cur " + currentNumberOfTrials + 
                " numEachTest "  +  numberOfTrialsEachTest + " last is OK " + 
                lastIsCorrect);
        return newTrial;
    }
}
