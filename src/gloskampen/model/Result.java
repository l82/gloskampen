/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

/**
 * This class is responsible for handling the result for a certain quiz
 * @author Fredrik Johansson
 */
public class Result
{    
    private Integer result;
    private String user;
    private Integer level;
           
    /**
     * 
     */
    public Result()
    {
        
    }        
    
    public Result(String user, int result, int level){
        this.user = user;
        this.result = result;
        this.level = level;
    }
    
    /**
     * 
     */
    public void add(){
        
    }
    
    /**
     * 
     * @param user
     * @param result
     * @param level 
     */
    public void setResult(String user, int result, int level){
        this.user = user;
        this.result = result;
        this.level = level;
    }
    
    /**
     * 
     * @return 
     */
    public int getPoints(){
        return result;
    }
}