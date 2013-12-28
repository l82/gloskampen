/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is responsible for handling a list of all lists of words that the 
 * programme has, i.e one entry for a list between Swedish-English, one for 
 * Swedish-German etc
 * @author Fredrik Johansson
 */
public class WordList
{    
    private String language1;
    private String language2;
    private ArrayList<Word> words;
    private String tempWordList; // changeMe
    private int i;  //TODO removed just testcode
       
    
    public WordList()
    {
        i = 0; //TODO
    }
    
    public WordList(String fromLanguage, String toLanguage)
    {
        language1 = fromLanguage;
        language2 = toLanguage;
        words = new ArrayList();
    }
    
    public String randomiseGlossary() {
        String newWordString;
        
        Word newWordObject;
        Collections.shuffle(words);
        newWordObject = words.get(i);
        newWordString = newWordObject.getWord();
        i++;
        return(newWordString);
    }
    
    public void initiateTestWords() {
    
        add("bil", "car", 1);
        add("husvagn", "caravan", 2);
        add("olycka", "accident", 3);
        add("spel", "game", 1);
        add("näsa", "nose", 1);
        add("axel", "shoulder", 2);
        add("ansikte", "face", 2);
        add("gurka", "cucumber", 3);
        add("jordgubbe", "straberry", 2);
        add("sommar", "summer", 2);
        add("jul", "Christmas", 2);
        add("höst", "autumn", 3);
        add("vår", "spring", 2);
        add("byxor", "trousers", 2);
        add("gris", "pig", 1);
    }
    
    public void tearDownTestWords() {
    
        words.clear();
        
    }
    
    private void remove()
    {
        
    }
    
     /** 
     * Adds a movie into list
     * 
     * @param mTitle        The title of the movie to add
     * @param mGenre        The genre of the movie, for example children 
     * @param mDirector     The director of the movie to add
     * @param mAge          The age which the movie is allowed from
     * @param mTime         The time in minutes which the movie last
     */
    private void add(String fromLanguage, String toLanguage, 
            Integer difficulty) {
        
        Word word;
        word = new Word(fromLanguage, toLanguage, difficulty);
        words.add(word);
    }
    
    private String getWordList()
    {        
        return tempWordList;
    }   
}