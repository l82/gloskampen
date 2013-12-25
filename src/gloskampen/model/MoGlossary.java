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
public class MoGlossary {
    private int numberOfFailed;
    private int level;
    private int fromLanguage;
    private int toLanguage;
    private ArrayList<String> listOfAlreadyUsedWords;
    private ArrayList<String> listOfFailedWords;
    private ArrayList<String> listOfFailedWordsTranslated;
    
    /**
     * Asks for a glossary that the user should test
     * @return The glossary that the pupils should translate
     */
    public String randomiseOneGlossary() {
        String randomisedGlossary;
        randomisedGlossary = "";
        return randomisedGlossary;
    }  
    
    /**
     * Randomises three alternatives for the glossary that should be tested
     * @param glossary The glossary that should have three alternatives
     * @return An arrayList with three alternatives
     */
    public ArrayList<String> randomiseThreeAlternatives(String glossary) {
        ArrayList<String> randWords;
        randWords = new ArrayList<>();
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
    
}
