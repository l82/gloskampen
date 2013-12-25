/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

/**
 * The Gloskampen class is the main class for a program for starting the glos-
 * kampen programme.
 * @author Lotta Hagborg
 */
public class Gloskampen {
    
    /**
     * The main method for the memory game
     * @param args 
     */
    public static void main(String[] args) {
        gloskampen.view.ViMain mainView = new gloskampen.view.ViMain();
        gloskampen.model.MoMain mainModel = new gloskampen.model.MoMain();
        gloskampen.control.CoMain mainControl = new gloskampen.control.CoMain(mainModel, mainView);
        mainControl.initiateSystem();
    }
}
