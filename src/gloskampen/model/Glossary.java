/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;
import java.util.*;
/**
 * This class is responsible to handle the quiz data
 * @author lotta
 */
public class Glossary {
    private int fromLanguage;
    private int toLanguage;
    private ArrayList<String> listOfAlreadyUsedWords;
    private String currentRightAnswer;
    private String currentGlossary;
    private final int totNumberOfGlossaries;
    private ArrayList<String> listOfFailedWords;
    private int listFailedCounter;
    private gloskampen.model.WordList   wordListToUse;
    private int numberOfExecutedGlossaries;
    private int numberOfFailedGlossaries;
    private int numberOfTrialsEachTest;
    private int currentNumberOfTrials;
    private final static int totNumWordsInTest = 10;
    private Boolean lastIsCorrect;
    
    
    public Glossary() 
    {
        totNumberOfGlossaries = totNumWordsInTest;
        numberOfFailedGlossaries = 0;
        numberOfExecutedGlossaries = 0;
        numberOfTrialsEachTest = 0; 
        listFailedCounter = 0;
        listOfFailedWords = new ArrayList<>();
        listOfAlreadyUsedWords = new ArrayList<>();
        currentNumberOfTrials = 1;
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
            listOfAlreadyUsedWords.add(tmpBothWords);
            randomisedGlossary = getWordToTranslateFromGlossary(tmpBothWords);
        }
        else {
             tmpBothWords = listOfFailedWords.get(listFailedCounter);
             randomisedGlossary = getWordToTranslateFromGlossary(tmpBothWords);
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
        currentGlossary = wordPair;
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
        setResultData(answerIsCorrect);
        return correct;
    }
    
    public void setTimeoutData() {
        setResultData(false);
    }
    
    private void setResultData(Boolean answerIsCorrect) 
    {        
        if (answerIsCorrect) 
        {
            resetCurrentNumberOfTrials();
        }
        else 
        {
            if (currentNumberOfTrials == 0) 
            {
                int lastElement = listOfAlreadyUsedWords.size();
                String failedWord = listOfAlreadyUsedWords.get(lastElement-1);
                listOfFailedWords.add(failedWord);
                numberOfFailedGlossaries++;
            }
            currentNumberOfTrials++;
        }
        if (!getNewTrial()) 
        {
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
        int i = 0;
        randWords = new ArrayList<>();
        wordToAdd = getWordThatMustBeThere();
        randWords.add(wordToAdd);
        while (i < 2) {
            wordToAddNonSplitted = wordListToUse.getRandomWordL8();
            wordToAdd = getWordToTranslateToGlossary(wordToAddNonSplitted);
            if (checkWordNotInList(randWords, wordToAdd)) {
                randWords.add(wordToAdd);
                i++;
            }
        } 
        randWords = randList(randWords);
        
        return randWords;
    }
    
    private String getWordThatMustBeThere() {
        String wordToAddNonSplitted, wordToAdd, wordToTest;
        wordToAddNonSplitted = currentGlossary;
        wordToAdd = getWordToTranslateToGlossary(wordToAddNonSplitted);
        
        return wordToAdd;
    }
    
    private ArrayList<String> randList(ArrayList<String> listToRand) {

        Collections.shuffle(listToRand);
        return listToRand;
    }
    
    private Boolean checkWordNotInList(ArrayList<String> randWords, String wordToAdd) {
        String triedWord;
        Boolean notUsed = true;
        
        for (int i = 0; i < randWords.size(); i++) {
            triedWord = randWords.get(i);
            if (wordToAdd.equals(triedWord)) {
                notUsed = false;
                break;
            }
        }
        return notUsed;
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
     * Set the two languages that should be used for the test
     * @param from The language which should be translated from
     * @param to The language which should be translated to
     */
    public void setLanguages(int from, int to) {
        fromLanguage = from;
        toLanguage = to;
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
        System.out.println("L8 checkEndOfTest executed=" + numberOfExecutedGlossaries +
                " tot=" + totNumberOfGlossaries + " failed=" + numberOfFailedGlossaries);
        if (numberOfExecutedGlossaries == (totNumberOfGlossaries + numberOfFailedGlossaries)) 
        {
            end = true;
        }
        //Never show wrong answers more than one extra time
        if (numberOfExecutedGlossaries >= (totNumberOfGlossaries * 2)) 
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
     * Resets the number of failed glossaries
     */
    public void resetNumberOfFailedGlossaries() 
    {
        numberOfFailedGlossaries = 0;
    }
    
    public void resetListFailedCounter()
    {
        listFailedCounter = 0;
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
     * Resets the number of executed glossaries
     */
    public void resetNumberOfExecutedGlossaries() 
    {
        numberOfExecutedGlossaries = 0;
    }
    
    /**
     * Resets the number of trials
     */
    public void resetCurrentNumberOfTrials() 
    {
        currentNumberOfTrials = 0;
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
        return newTrial;
    }
    
    public void emptyListOfAlreadyUsedWords() {
        listOfAlreadyUsedWords.clear();
    }
    
    public void emptyListOfFailedWords() {
        listOfFailedWords.clear();
        System.out.println("L8 no failed words " + listOfFailedWords.size());
    }
}
