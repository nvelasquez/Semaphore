/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Nestor_Velasquez
 */
public class Semaphore extends Component implements Runnable{

    private Shape circleGreen = new Ellipse2D.Double(100.0f, 50.0f, 50.0f, 50.0f);
    private Shape circleYellow = new Ellipse2D.Double(100.0f, 100.0f, 50.0f, 50.0f);
    private Shape circleRed = new Ellipse2D.Double(100.0f, 150.0f, 50.0f, 50.0f);
    private Shape rec         = new Rectangle2D.Double(95.0f, 50.0f, 60.0f, 150.0f);
    private Color colorYellow = Color.black;
    private Color colorRed    = Color.red;
    private Color colorGreen  = Color.black;
    private String name;
    private Intersection in;
    //private int type;
    
    public Semaphore(String name, Intersection in){
        this.name = name;
        this.in = in;
        //this.type = type;
    }
    
    public void updateColors(Color a, Color b, Color c){
        colorGreen = a;
        colorYellow = b;
        colorRed = c;
        //this part repaint the Semaphore
        repaint();
    }
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D ga = (Graphics2D) grphcs;
        //Draw Semaphore Name.
        ga.drawString(name, 100.0f, 20.0f);
        //Draw timing
        ga.drawString("Time: "+String.valueOf(in.getTimer()), 105.0f, 35.0f);
        //Draw encapsulating
        ga.draw(rec);
        ga.setColor(Color.orange);
        ga.fill(rec);
        //Draw and fill green circle        
        ga.draw(circleGreen);
        ga.setColor(colorGreen);
        ga.fill(circleGreen);
        //Draw and fill yellow circle
        ga.draw(circleYellow);
        ga.setColor(colorYellow);
        ga.fill(circleYellow);
        //Draw and fill red circle
        ga.draw(circleRed);
        ga.setColor(colorRed);
        ga.fill(circleRed);
    }

    @Override
    public void run() {
        while(true){
            if(in.putWating()){
              updateColors(Color.black, Color.black, Color.red);  
            }
            //while putRunning doeas not indicate to stop, will be running the actual thread. 
            while(in.isRunning()){            
                in.putRunning();
                switch(in.getState().toUpperCase()){
                    case "GREEN":{
                        updateColors(Color.green, Color.black, Color.black);
                        break;
                    }
                    case "YELLOW":{
                        updateColors(Color.black, Color.yellow, Color.black); 
                        break;
                    }
                    case "RED":{
                        updateColors(Color.black, Color.black, Color.red);
                        break;
                    }
                    default: {
                        updateColors(Color.black, Color.black, Color.red);                        
                        break;
                    }
                }
                try{
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));//Sleep for a second.
                    in.setTimer(in.getTimer()-1);
                }
                catch(InterruptedException e){
                    e.getStackTrace();
                }            
            }            
        }
    }
    
}
