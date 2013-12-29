/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;
import java.util.Random;

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
    private int currentWord;
    
    /**
     * 
     */
    public WordList()
    {
        words = new ArrayList();
        currentWord = 0;
    }
    
    /**
     * 
     */
    private void remove()
    {
        
    }
    
    /**
     * 
     */
    private void add(String wordOne, String wordTwo, int difficulty)
    {
        //Word tempWord = new Word(lang1, lang2, diff);
        words.add( new Word(wordOne.toLowerCase(), wordTwo.toLowerCase(), difficulty) );
    }
    
    /**
     * 
     * @return 
     */
    private String getWordList()
    {        
        return tempWordList;
    }   
    
    /**
     * 
     */
    public void printWordList(){
       for (int i=0; i<words.size(); i++){
           System.out.print(words.get(i).getWord(1));
           System.out.println(" - " + words.get(i).getWord(2));
       } 
    }
    
    /**
     * 
     * @param word
     * @return 
     */
    private Boolean checkIfWordExists(String word) {
        boolean wordExists;
        wordExists = false;
        
        for (int i=0; i<words.size();i++){
            if (words.get(i).getWord(1).equals(word)){
                System.out.println("Word found");
                wordExists = true;
            }
        }
        
        return wordExists;
    }
    
    /**
     * 
     * @param i
     * @param order 
     */
    public void printWord(int i, int order){
        System.out.println(words.get(i).getWord(order));
    }
    
    /**
     * 
     */
    public void printNotAnsweredWords(){
        for(int i=0; i<words.size(); i++){
            if (words.get(i).isAnswered() == false){
                System.out.println(words.get(i).getWord(1));
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getRandomWord(){
        Random rand = new Random();
        int nrOfWords = words.size();
        int iRand = rand.nextInt(nrOfWords);
              
        return words.get(iRand).getWord(1);
        
    }
    
    /**
     * 
     * @return 
     */
    public String getNextWord(){
        String tempWord;
        tempWord = words.get(currentWord).getWord(1);
        currentWord++;
        return tempWord;
    }
    
     public void initiateTestWords() {
    
        System.out.println("WrodList: initiateTestWords");
        add("bil", "car", 1);
        add("husvagn", "caravan", 2);
        add("olycka", "accident", 3);
        add("spel", "game", 1);
        add("mun", "mouth", 1);
        add("axel", "shoulder", 2);
        add("ansikte", "face", 2);
        add("gurka", "cucumber", 3);
        add("jordgubbe", "strawberry", 2);
        add("sommar", "summer", 2);
        add("jul", "Christmas", 2);
        add("dra", "pull", 3);
        add("finger", "finger", 2);
        add("byxor", "trousers", 2);
        add("gris", "pig", 1);
    }
    
    public void tearDownTestWords() {
    
        words.clear();
        
    }
    
     public String getRandomWordL8(){
        Random rand = new Random();
        int nrOfWords = words.size();
        int iRand = rand.nextInt(nrOfWords);
              
        return (words.get(iRand).getWord(1) + ";" + words.get(iRand).getWord(2));
        
    }
    
    
}