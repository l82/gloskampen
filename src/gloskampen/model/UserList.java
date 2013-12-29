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
    
    private ArrayList<User> userList;
    
    /**
     * 
     */
    public UserList() {
        userList = new ArrayList();
    }
    
    /**
     * Checks if a certain user exists in database or not. The user is case in-
     * sensitive.
     * @param user The user name to check for
     * @return true if user exists else false
     */
    private Boolean checkIfUserExists(String user) {
        Boolean userExists;
        userExists = false;
        
        for (int i=0; i<userList.size();i++){
            if (userList.get(i).getUser().equals(user)){
                System.out.println("User found");
                userExists = true;
            }
        }
        
        return userExists;
    }
    
    /**
     * Gets the index for a user in list.
     * @param user The user to find in list
     * @return The index of user in list
     */
    private int getIndexOfUserInList(String user){
        int index;
        index = -1;
        for (int  i=0; i<userList.size(); i++){
            if (userList.get(i).getUser().equals(user)){
                System.out.println("User found. Returning index: "+i);
                index = i;
            }
        }
        return index;
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
        Boolean userExists;
        int index;
        
        index = 0;
        userExists = checkIfUserExists(user);
        
        if (userExists == false){
            System.out.println("User dont exists. Cant remove");
        }
        else {
            index = getIndexOfUserInList(user);
            System.out.println("Users exists. Will remove user");
            userList.remove(index);
        }
       
    } 
    
}
