import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

  // Java Program to create a 
// simple progress bar 
public class Progress extends JFrame { 
   // create a frame 
   static JFrame f; 
 
   static JProgressBar b; 

   
 
   public Progress() {
    
   
      // create a frame 
      f = new JFrame("Computing Words"); 
      f.setLocationRelativeTo(null);
      // create a panel 
      JPanel p = new JPanel(); 
   
      // create a progressbar 
      b = new JProgressBar(); 
   
      // set initial value 
      b.setValue(0); 
   
      b.setStringPainted(true); 
   
      // add progressbar 
      p.add(b); 
   
      // add panel 
      f.add(p); 
   
      // set the size of the frame 
      f.setSize(500, 500); 
      f.setVisible(false); 
   
   } 
 
   // function to increase progress 
   public static void fill() 
   { 
      f.setVisible(true);
      int i = 0; 
      try { 
         while (i <= 100) { 
            // fill the menu bar 
            b.setValue(i + 1); 
         
            // delay the thread 
            Thread.sleep(200); 
            i += 1; 
         } 
         f.setVisible(false); 
      } 
      catch (Exception e) { 
      } 
   } 
   

         
   
}