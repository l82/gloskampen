/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

import static java.lang.Thread.sleep;

/**
 * The timer object. Draws the clock at certain intervals
 * @author lotta
 */

public class Timer extends Thread {
    
    private final int timerDelay;
    private Boolean timeout;
    private Callback endSleepFunction; 
    
    /**
     * Sleeps and draws the clock at certain times
     * @param delay How long to sleep before returning
     */
    public Timer(int delay, Callback functionCallback) {
	super();
        timerDelay = delay;
        endSleepFunction = functionCallback;
    }
    
    @Override
    public void run() {
        try {
            sleep(timerDelay);
            endSleepFunction.callback();
	} catch (InterruptedException | IllegalThreadStateException e) {
            //System.out.println("Error: " + e.getMessage());
        }
    }
}
