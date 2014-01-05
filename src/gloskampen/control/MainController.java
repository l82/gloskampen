/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package gloskampen.control;

import gloskampen.model.WordList;

/**
 *
 * @author lotta
 */
public class MainController 
{   
    private final gloskampen.view.MainView  mainView;
    private final gloskampen.model.Main  mainModel;
    static final int NUMBER_OF_WORDS_IN_TEST = 10;
    
    /**
     * Constructor for Controller object that initiates handle to main view and
     * main model object.
     * @param inModel main model object
     * @param inViewer main view object
     */
    public MainController(gloskampen.model.Main inModel, gloskampen.view.MainView inViewer)
    {       
        mainView = inViewer;
        mainModel = inModel;
    }
    
    public void initiateSystem() 
    {
        gloskampen.model.WordList testListSvEn;
                
        GlossaryController glossaryController = new GlossaryController(mainView,
            NUMBER_OF_WORDS_IN_TEST);
        
        glossaryController.intiateGlossaryControlerGame(mainView);
                
        //L8 TODO: Test code to make it easier to test. Should be removed later on
        testListSvEn = new WordList();
        //testListSvEn = new WordList("svenska", "engelska");
        testListSvEn.initiateTestWords();
        glossaryController.setWordList(testListSvEn);        
    }    
}
