/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

/**
 * This class is responsible for a user
 * @author lotta
 */
public class User {
    private String userName;
    
    /**
     * Default constructor
     */
    public User(){
        
    }
    
    /**
     * Constructor for a new user
     * @param userName The name of the user
     */
    public User(String userName) {
        this.userName = userName;
    }
    
    /**
     * @param userName
     */
    public void setUser(String userName){
       this.userName = userName;
    }
    
    /**
     * @return
     */
    public String getUser(){
        return userName;
    }
    
}
