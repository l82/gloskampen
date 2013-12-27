/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

/**
 * This class is responsible for a certain word between two certain languages
 * @author Fredrik Johansson
 */
public class Word 
{    
    private String language1;
    private String language2;
    private Integer difficulty;
    private Boolean ok;
    private String tempWord; // changeMe
    
    
    public Word(String fromLanguage, String toLanguage, Integer inDifficulty)
    {
        language1 = fromLanguage;
        language2 = toLanguage;
        difficulty = inDifficulty;
        
    }
    
    private void remove()
    {
        
    }
    
    private void add(String string)
    {
        
    }
    
    private String getWord()
    {        
        return tempWord;
    }   
}