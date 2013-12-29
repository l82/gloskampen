/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is responsible for handling all the results for all tests
 * @author Fredrik Johansson
 */
public class ResultList
{    
    private ArrayList<Result> resultList;
    private Integer tempResult; // changeMe trdt
    
    /**
     * 
     */
    public ResultList()
    {
        resultList = new ArrayList();
    }   
    
    /**
     * 
     */
    private void addNewResult(String user, int result, int level)
    {
        resultList.add( new Result(user,result,level) );
    }
    
    /**
     * 
     */
    private void getTopList()
    {
       
        
    }
    
    /**
     * 
     */
    private void sortList(){
        
    }
    
    /**
     * 
     * @return 
     */
    private Integer getResultForUser()
    {
        return tempResult;
    }    
}