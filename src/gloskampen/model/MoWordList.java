/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;

/**
 * This class is responsible for handling a list of all lists of words that the 
 * programme has, i.e one entry for a list between Swedish-English, one for 
 * Swedish-German etc
 * @author Fredrik Johansson
 */
public class MoWordList
{    
    private String language1;
    private String language2;
    private ArrayList words;
    private String tempWordList; // changeMe
       
    
    public MoWordList()
    {
        
    }
    
    private void remove()
    {
        
    }
    
    private void add()
    {
        
    }
    
    private String getWordList()
    {        
        return tempWordList;
    }   
}