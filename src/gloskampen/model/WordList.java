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
 * @author 
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
     * L8
     */
    private void add(String wordOne, String wordTwo)
    {
        //Word tempWord = new Word(lang1, lang2, diff);
        words.add( new Word(wordOne.toLowerCase(), wordTwo.toLowerCase()) );
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
        add("bil", "car"); //L8 removed level
        add("husvagn", "caravan");
        add("olycka", "accident");
        add("spel", "game");
        add("mun", "mouth");
        add("axel", "shoulder");
        add("ansikte", "face");
        add("gurka", "cucumber");
        add("jordgubbe", "strawberry");
        add("sommar", "summer");
        add("jul", "Christmas");
        add("dra", "pull");
        add("finger", "finger");
        add("byxor", "trousers");
        add("gris", "pig");
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