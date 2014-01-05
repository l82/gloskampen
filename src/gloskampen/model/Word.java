/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for a certain word between two certain languages
 * @author
 */
public class Word 
{    
    private String language1;
    private String language2;
    private Integer difficulty;
    private Boolean ok;
    private boolean answered;
    
    /**
     * Default constructor
     */
    public Word()
    {
        
    }
    
    /**
     * 
     */
    public Word(String language1, String language2, int difficulty){
        if (this.validChars(language1) && this.validChars(language2)){
            this.language1 = language1;
            this.language2 = language2;
            this.difficulty = difficulty;
            this.ok = false;
            this.answered = false; 
        }
        
    }
    
    /**
     * @param order of word to get
     * @return 
     */
    public String getWord(int order)
    {        
        if (order == 1){
            return language1;
        }
        return language2;
    } 
    
    /**
     * 
     * @param order of word in pair
     * @param word to insert
     */
    public void setWord(int order, String word){
        if (order==1){
            language1 = word;
        }
        else {
            language2 = word;
        }
    }
    
    /**
     * 
     * @param word
     * @return matchFound which indicates valid or invalid chars in word 
     */
    private boolean validChars(String word){
        Pattern p = Pattern.compile("[A-Ö][a-ö]-+");
        Matcher m = p.matcher(word);
        
        boolean matchFound = m.matches();
        if (matchFound){
            System.out.println("Valid");
        }
        else {
            System.out.println("Invalid");
        }
        return true;
        //L8 matchFound something wrong?
        //return matchFound;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isAnswered(){
        return answered;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isWrong(){
        return ok;
    }
    
    public void swapWords(){
        String tempWord;
        
        tempWord = this.language1;
        this.language1 = this.language2;
        this.language2 = tempWord;
        
    }
}