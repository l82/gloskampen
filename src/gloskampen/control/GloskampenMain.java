/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

/**
 * The GloskampenMain class is the main class for a program for 
 * starting the Gloskampen program.
 * @author Lotta Hagborg
 */
public class GloskampenMain 
{    
    /**
     * The main method for the memory game
     * @param args 
     */
    public static void main(String[] args) 
    {        
        gloskampen.view.MainView mainView = new gloskampen.view.MainView();
        mainView.setVisible(true);
        gloskampen.model.Main mainModel = new gloskampen.model.Main();        
        gloskampen.control.MainController mainControl = new gloskampen.control.MainController(mainModel, mainView);
        mainControl.initiateSystem();
    }
}
