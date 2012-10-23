/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nestor_Velasquez
 */
public class MainWindow {
    
    private Intersection in = new Intersection();
    private Semaphore a = new Semaphore("Av. 27 Feb. Este/Oeste", in);
    private Semaphore b = new Semaphore("Av. Lincoln Norte", in);
    private Semaphore c = new Semaphore("Av. Lincoln Sur", in);
    
    public MainWindow(){
        //we initializied all the non-statico componens here.
        init();
    }
    
    public void init(){
        //Declaring containers and components.
        JFrame f = new JFrame("Semaforo");
        JPanel p = new JPanel();
        JButton bt = new JButton("Iniciar");
        
        //Configured a mouse listener on click button to perform an action.
        bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btMouseClicked(evt);
            }
        });
        
        //Setting components atributes.
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        bt.setVisible(true);
        bt.setAlignmentY(0.2f);
        bt.setToolTipText("Este boton inicia el software Semaforo");
        
        //Adding components to the panel
        p.add(bt);
        p.add(a);
        p.add(b);
        p.add(c);        
        
        //If we close the window it close the process
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //We set the content Pane as the panel we declare before.
        f.setContentPane(p);
       
        f.setSize(1000, 300);
        f.setVisible(true);  
    }
    
    private void btMouseClicked(java.awt.event.MouseEvent evt) {
        
        //Here we create threads to coordinate the semaphores
        Thread sem1 = new Thread(a);
        Thread sem2 = new Thread(b);
        Thread sem3 = new Thread(c);
        
        //Here we start them.
        sem1.start();
        sem3.start();
        sem2.start();
        
    }
    
    public static void main(String args[]){
        new MainWindow();
    }
}
