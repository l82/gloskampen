/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;
import java.util.ArrayList;

/**
 * This class is responsible to handle a list with users and adding/removing 
 * users from the list
 * @author lotta
 */
public class UserList {
    
    ArrayList<User> userList;
    
    /**
     * Checks if a certain user exists in database or not. The user is case in-
     * sensitive.
     * @param user The user name to check for
     * @return true if user exists else false
     */
    private Boolean checkIfUserExists(String user) {
        Boolean userExists;
        userExists = false;
        return userExists;
    }
    
    /**
     * Adds a new user in userList. If user already exists the user is not added
     * @param user The name of the user to add
     */
    public void checkAndAddUser(String user) {
        User newUser;
        Boolean userExists;
        
        userExists = checkIfUserExists(user);
        
        if (userExists == false) {
            newUser = new User(user);
            userList.add(newUser);
        }
    } 
    
    /**
     * Removes a user form user list. First it is checked that user already 
     * exists. User is case insensitive
     * @param user The user to remove 
     */
    public void checkAndRemoveUser(String user) {
        
    } 
    
}
