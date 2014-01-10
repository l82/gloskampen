/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gloskampen.control;

import static java.lang.Thread.sleep;

/**
 * The timer class. This class is responsible to run a timer thread
 * @author Lotta Hagborg
 */

public class Timer extends Thread {
    
    /** Time that the thread should be running */
    private final int timerDelay;
    /** Callback function to call when timerDelay has elapsed */
    private final Callback endSleepFunction; 
    
    /**
     * Sleeps the delay time and calls the callback function when finished
     * @param delay How long to sleep before returning
     * @param functionCallback to use as callback function
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
	} catch (InterruptedException e) {
            //Do nothing
        } catch (IllegalThreadStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
