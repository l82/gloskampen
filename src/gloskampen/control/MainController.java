/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package gloskampen.control;

/**
 *
 * @author lotta
 */
public class MainController {
   
    private final gloskampen.view.MainView  mainView;
    private final gloskampen.model.Main  mainModel;
    static final int NUMBER_OF_WORDS_IN_TEST = 10;
    
    /**
     * Constructor for Controller object that initiates handle to main view and
     * main model object.
     * @param inModel main model object
     * @param inViewer main view object
     */
    public MainController(gloskampen.model.Main inModel, 
            gloskampen.view.MainView inViewer) {
        mainView = inViewer;
        mainModel = inModel;
    }
    
    public void initiateSystem() {
        mainView.generateFrames();
        GlossaryController glossaryController = new GlossaryController(mainView,
        NUMBER_OF_WORDS_IN_TEST);
        glossaryController.intiateGlossaryControlerGame(mainView);
        
    }
    
}
