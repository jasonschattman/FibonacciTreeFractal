package fibonaccitree;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Thread;

public class FibonacciTree extends JFrame {
    
    double maxLength = 100;
    double lengthDecayFactor = 0.85;
    double deltaTheta = Math.PI/8;
    
    int maximumLevel = 15;
    boolean animateAllLevelsUpToMaximum = true;
    int waitTimeBetweenFrames = 100;
    int currLevel = 1;
     
    Color[] branchColours = { Color.green, Color.blue, Color.red };
    
    
    public void drawFibTree( int n, double x1, double y1, double angle, double r, int age, Graphics g  ) {
        
       double x2 = x1 + r * Math.cos( angle );
       double y2 = y1 - r * Math.sin( angle );
       
       drawOneBranch( x1, y1, x2, y2, age, g ); //draw one branch from (x1,y1), no matter what n is.
       
       double r2 = r * lengthDecayFactor;
       
       if ( n > 1) { // if we're not in the base case
            
           if ( age == 0 ) { //if a baby has just been drawn
               drawFibTree( n-1, x2, y2, angle, r2, 1, g ); //sprout a kid from (x2, y2)  Note that we pass 1 as the new age argument
           }
           
           else { //if a kid or adult has just been drawn
               drawFibTree( n-1, x2, y2, angle + deltaTheta, r2, 2, g ); //sprout an adult from (x2, y2) at a new angle (age argument = 2)
               drawFibTree( n-1, x2, y2, angle - deltaTheta, r2, 0, g ); //sprout a baby from (x2, y2) at a new angle (age argument = 0)
           }            
        }
    }
    
    
    public void drawOneBranch( double x1, double y1, double x2, double y2, int age, Graphics g) {
        g.setColor( branchColours[ age ] );
        g.drawLine( (int)x1, (int)y1, (int)x2, (int)y2 );
    }
    
    
    public void paint( Graphics g ) {
         g.fillRect(0,0,1000,800);
         
         drawFibTree( currLevel, 500, 800, Math.PI/2, maxLength, 0, g );     
   }
    
    
    public static void main(String[] args) {
        
        FibonacciTree ft = new FibonacciTree();
        
        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ft.setBackground(Color.black);
        ft.setSize( 1000, 800 );
        ft.setVisible(true);
        
        if ( ft.animateAllLevelsUpToMaximum == true ) {
            
            for ( int i=1; i < ft.maximumLevel; i++ ) {
                
                sleep( ft.waitTimeBetweenFrames );
       
                ft.repaint();
                ft.currLevel++;
            }  
        }
        
        else
            ft.currLevel = ft.maximumLevel;
   }

    
    public static void sleep( int duration ) {
        try {
              Thread.sleep( duration );
            }
        catch (Exception e) {
            }
    }
    

}