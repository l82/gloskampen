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
public class CoMain {
   
    private final gloskampen.view.ViMain  mainView;
    private final gloskampen.model.MoMain  mainModel;
    
    /**
     * Constructor for Controler object that initiates handle to main view and
     * main model object.
     * @param inModel main model object
     * @param inViewer main view object
     */
    public CoMain(gloskampen.model.MoMain inModel, 
            gloskampen.view.ViMain inViewer) {
        mainView = inViewer;
        mainModel = inModel;
    }
    
    public void initiateSystem() {
        mainView.generateFrames();
    }
    
}
