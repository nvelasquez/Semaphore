/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Nestor_Velasquez
 */
public class Intersection {
    
    private int timer;//a timer to handle semaphore time.
    private String state;//A color to indicate state.
    private boolean running = false;//This is to make run the first thread.
    
    
    public synchronized boolean putWating(){
        
        //If there is a Semapahore running then wait.
        while(running==true){
            
            try{
                wait();
            }
            catch(InterruptedException e){
                e.getStackTrace();
            }
            return true;
        }
        //Else set the timer an get run with green state.
        setTimer(50);//intialize the timer in every call.
        setRunning(true);
        setState("green");
        return false;
    }
    
    /*
     * @putRunning this method put indicate what color have the sem in the exacly time.
     */
    public  synchronized void putRunning(){        
        
        if( timer == 5 ){
            setState("yellow");//at minus 5 indicate yellow state.
        }
        if ( timer == 0 ){
            setState("red");//when it finish set red and exit loop.            
        }
        //In case of the timer gets less than 0 then wait, notify the other and set the timer 0
        if( timer < 0 ){
            setRunning(false);
            notify();
            try{
                wait();
                setTimer(0);
            }
            catch(InterruptedException e){
                e.getStackTrace();
            }
        }        
    }
    
    /**
     * @return the timer
     */
    public int getTimer() {
        return timer;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * @param state the state to set
     */    
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    } 
}
