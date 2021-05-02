
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.*;
import java.util.Random;
import java.util.ArrayList;
   
import javax.swing.JFrame;
@SuppressWarnings("unchecked")
public class GeneralGUIBuilding extends JFrame{
   private String[] description = { "Words with Definitions Huge", "Words with Definitions small", "decent size word Lexicon",
      "A medium size word Lexicon", "A small sized word Lexicon", "A really small word Lexicon", "A word Lexicon with many English words including bad ones" };
      
   private String[] description2 = { "Default Board Layout", "2 x 2 Board size", "3 x 3 Board size",
      "4 x 4 Board size", "5 x 5 Board size", "6 x 6 Board size", "7 x 7 Board size", "8 x 8 Board size",
      "9 x 9 Board size", "10 x 10 Board size" };
      
      
   private Object[] columns = {"1", "2", "3", "4", "5"};
   private Object[][] objects = {
   {new String (""), "0", "1", "2", "3"},
   {new Integer(0),"E", "E", "C", "A"},
   {new Integer(1), "A", "L", "E", "P"},
   {new Integer(2), "H", "N", "B", "O"},
   {new Integer(3), "Q", "T", "T", "Y"},
   
   };
   private JButton buttonCounter, buttonReset;
   private JButton labelMessage;
   private JButton labelMessage2;
   private JButton boardSelected;

   // private JTextField fieldName;
   
   private JLabel labelCount;
   private JLabel LexiconError;
   private JLabel textBoxLabelMessage;
   
   private JComboBox c = new JComboBox();
   private JComboBox d = new JComboBox();
   
   private WordSearchGame game = WordSearchGameFactory.createGame();
   
   
   private String choice;
   private String lexiconChoice = "wordfiles/CSW12.txt";


   private int clicks = 0;
   private int count = 0;
   private int matrixCount = 0;
   public int tableRowSize = 4;
   private boolean loadedLexicon = false;
   
   
   private SoundEffect invalidQuestion = new SoundEffect("soundEffects/smb_bowserfalls.wav");
   private SoundEffect invalidQuestion2 = new SoundEffect("soundEffects/Slap.wav");
   private SoundEffect invalidQuestion3 = new SoundEffect("soundEffects/ComputerError.wav");
   private SoundEffect invalidQuestion4 = new SoundEffect("soundEffects/ComputerError2.wav");
            
            // Creates an array of possible sound Effects
   private ArrayList<SoundEffect> sounds = new ArrayList<SoundEffect>();
            
   public GeneralGUIBuilding() {
      createView();
      
      setTitle("Word Search Game");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pack();
      //set display size
      setSize(1200, 700);
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      
   }
   
   private void createView() {
      JPanel panel = new JPanel();
      getContentPane().add(panel);
      panel.setBackground(Color.WHITE);
      
      labelCount = new JLabel();
      
      // fieldName = new JTextField();
      //fieldName.setPreferredSize(new Dimension(200, 40));
      // panel.add(fieldName);
      labelCount.setPreferredSize(new Dimension(200, 30));
         // This add a drop down menu for selecting the Lexicon to load
      panel.add(labelCount);
      panel.add(c);
      
      for (int i = 0; i < 7; i++){
         c.addItem(description[count++]);
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  if (count < description.length)
                     c.addItem(description[count++]);
               }
            };
         c.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  int action = c.getSelectedIndex();
                  if (action == 0) {
                     lexiconChoice = "wordfiles/CSW12.txt";
                  }
                  if (action == 1) {
                     lexiconChoice = "wordfiles/OWL.txt";
                  }
                  if (action == 2) {
                     lexiconChoice = "wordfiles/words.txt";
                  }
                  if (action == 3) {
                     lexiconChoice = "wordfiles/words_medium.txt";
                  }
                  if (action == 4) {
                     lexiconChoice = "wordfiles/words_small.txt";
                  }
                  if (action == 5) {
                     lexiconChoice = "wordfiles/words_tiny.txt";  
                  }
                  if (action == 6) {
                     lexiconChoice = "wordfiles/EnglishWords.txt";
                  }
               
                  
               
               
               
               }
            });
      }
      updateCounter();
      buttonCounter = new JButton("Selected Lexicon choice");
      buttonCounter.addActionListener(
         new ButtonCounterActionListener());
      panel.add(buttonCounter);
       
      buttonReset = new JButton("Reset");
      buttonReset.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  clicks = 0;
                  updateCounter();
               }
            
            }
         );
      panel.add(buttonReset);
      
         // This add a drop down menu for selecting the Lexicon to load
      panel.add(labelCount);
      panel.add(d);
      
      for (int i = 0; i < 10; i++){
         d.addItem(description2[matrixCount++]);
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  if (matrixCount < description2.length)
                     d.addItem(description2[matrixCount++]);
               }
            };
         d.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  int action = d.getSelectedIndex();
                  
                  if (action == 0) {
                     tableRowSize = 1;
                  }
                  if (action == 1) {
                     tableRowSize = 2;
                  }
                  if (action == 2) {
                     tableRowSize = 3;
                  }
                  if (action == 3) {
                     tableRowSize = 4;
                  }
                  if (action == 4) {
                     tableRowSize = 5;
                  }
                  if (action == 5) {
                     tableRowSize = 6;
                  }
                  
                  if (action == 6) {
                     tableRowSize = 7;
                  }
                  if (action == 7) {
                     tableRowSize = 8;
                  }
                  if (action == 8) {
                     tableRowSize = 9;
                  }
                  if (action == 9) {
                     tableRowSize = 10;
                  }
                  
               
               
               
               }
            });
      }
      
      boardSelected = new JButton("Selected Board Size");
      boardSelected.addActionListener(
         new boardSelectedActionListener());
      panel.add(boardSelected);
   
   
      panel.add(boardSelected);
      
      // This adds the text box for inputing the board Layout //
      JLabel labelField = new JLabel("Please select the board you want to use. Your" 
         + " answer needs to be written as a perfect square. If you type nothing a default"
         + "4X4 board will be created with values of:");
      panel.add(labelField);
      
      JTable t = new JTable(objects,columns);
      t.getColumnModel().getColumn(0).setPreferredWidth(50);
      t.setRowHeight(0,50);
      t.setRowHeight(1,70);
      t.setRowHeight(2,70);
      t.setRowHeight(3,70);
      t.setRowHeight(4,70);
      t.setFont(new Font("Arial", Font.BOLD,60));
      
      //t.setFont(new Font("Courier", Font.BOLD,75));
   
      panel.add(t);
   
      LexiconError = new JLabel();
      panel.add(LexiconError);
   
   }
   
   
   
   
   private void updateCounter() {
      labelCount.setText("Clicked " + clicks + " times");
   }
   

   public static void main (String[] args) {
         
         // Opens a new Instance of JFrame
      SwingUtilities.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               new GeneralGUIBuilding().setVisible(true);
            }
         
         
         });
   
   
   }
   // This button loads the Lexicon that was selected //
   private class ButtonCounterActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         loadedLexicon = true;
         game.loadLexicon(lexiconChoice);
         
         
         // String boardLayout = fieldName.getText();
         // String[] result = boardLayout.split("(?!^)");  
         // game.setBoard(result);
         
         
         
         // JFrame frame2 = new JFrame();
      //    
         // frame2.setTitle("Word Search Game");
      // //Make window exit application on close
         // frame2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         // frame2.pack();
      // //set display size
         // frame2.setSize(1200, 700);
      // //Center the frame to middle of screen
         // frame2.setLocationRelativeTo(null);
      // //Disable resize
         // frame2.setResizable(false);
         // frame2.setVisible(true);
      
      }
   }
   
   // This action happens once a custom board size has been selected //
   private class boardSelectedActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         if (loadedLexicon == true) {
         
         
            setVisible(false);
            String[] colHeadings = new String[tableRowSize];            
            for (int i = 0;  i < tableRowSize; i++) {
               String letter = String.valueOf(i);
               colHeadings[i] = letter;
            }
            BoardSelectedFrame frame2;
            frame2 = new BoardSelectedFrame(tableRowSize, colHeadings, game);
         
         }
         else {
            LexiconError.setText("** You need to load a Lexicon before you can create a custom board **");
            sounds.add(invalidQuestion);
            sounds.add(invalidQuestion2);
            sounds.add(invalidQuestion3);
            sounds.add(invalidQuestion4);
         
            
            Random rand = new Random();
            int temporary;
            temporary = rand.nextInt(sounds.size());
            sounds.get(temporary).play();
            
            LexiconError.setForeground (Color.red);
            LexiconError.setFont(new Font("Arial", Font.PLAIN, 30));   
         }         
         
         
            
      }
      
   }
   // This action happens once a custom board has been filled in //
   private class selectedBoardPositionsActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      }
      
      
   }
      
   
   
}