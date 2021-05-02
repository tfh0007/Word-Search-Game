import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;   
import javax.swing.JFrame;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Random;



public class BoardSelectedFrame extends JFrame implements TableModelListener{

   private JButton selectedBoardPositions, confirmValues, buttonReset, wordSelected;
   private JLabel labelCount, wordFound, tableError;
   private JTextField wordSearch;
   private int clicks = 0, tableRowSize = 0;
   private String[][] valuesOfTable;
   private String[] colHeadings, path;
   private JPanel panel;
   private WordSearchGame game;
   private SoundEffect questionRight, questionWrong; 
   private int boardFilledIn = 0;
   private Progress progress1 = new Progress();

   
   
   private SoundEffect invalidQuestion = new SoundEffect("soundEffects/smb_bowserfalls.wav");
   private SoundEffect invalidQuestion2 = new SoundEffect("soundEffects/Slap.wav");
   private SoundEffect invalidQuestion3 = new SoundEffect("soundEffects/ComputerError.wav");
   private SoundEffect invalidQuestion4 = new SoundEffect("soundEffects/ComputerError2.wav");
            
            // Creates an array of possible sound Effects
   private ArrayList<SoundEffect> sounds = new ArrayList<SoundEffect>();
   
   public BoardSelectedFrame() {
      createView();
      
      setTitle("Word Search Game Board Configuration");
      //Make window exit application on close
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      //set display size
      setSize(1200, 700);
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      
      
      
   }
   
   public BoardSelectedFrame(int tableRowSizeIn, String[] colHeadingsIn, WordSearchGame gameIn) {
      tableRowSize = tableRowSizeIn;
      colHeadings = colHeadingsIn;
      game = gameIn;
      valuesOfTable = new String[tableRowSize][tableRowSize];
      createView();
      
      setTitle("Word Search Game");
      //Make window exit application on close
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      // if (tableRowSize > 7) {
      //          // set display size of board if table is greater than a 8 x 8 board
         // setSize(1500,1500);
      // }
   //       
      // if ((tableRowSize > 6) && (tableRowSize < 7)) {
      //          // set display size of board if table is greater than a 6 x 6 board
         // setSize(1250,800);
      // }
      // else {
      //          // set display size of board if table is less than a 5 x 5 board
      setSize(1200, 1100);
      
      // }
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      setVisible(true);
      
      
      
   
   }
   
   private void createView() {
      if (boardFilledIn < 1) {
      
         wordSearch = new JTextField();
         wordSelected = new JButton("Search for this word on board");
         wordSelected.addActionListener(
            new wordSelectedActionListener());
         wordSearch.setPreferredSize(new Dimension(200, 40));
      
         panel = new JPanel();
         getContentPane().add(panel);
         panel.setBackground(Color.WHITE);
      
         try {
            DefaultTableModel model = new DefaultTableModel(tableRowSize, colHeadings.length) ;
            Object[][] objects = new Object[tableRowSize][tableRowSize];
            model.setColumnIdentifiers(colHeadings);
            JTable table = new JTable(model);
            table.getModel().addTableModelListener(this);
            selectedBoardPositions = new JButton("Selected Board Positions"); 
         
            panel.add(table);  
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
         
            table.setRowHeight(0,100);
            for (int i = 1; i < tableRowSize + 1; i++) {
               table.setRowHeight(i,100);
               if (i < tableRowSize) {
                  table.getColumnModel().getColumn(i).setPreferredWidth(100);
               
               }
            }
            table.setFont(new Font("Arial", Font.BOLD,60));
         
         }
         
         
         catch (Exception e) {
            System.out.println("Error: ");
            System.out.println("You need to run the main class for this Jframe to run");
            SoundEffect invalidAnswer = new SoundEffect("soundEffects/smb_bowserfalls.wav");
            invalidAnswer.play();
            System.exit(0);
         }
         labelCount = new JLabel();
      
      
         tableError = new JLabel();
         panel.add(tableError); 
      
         confirmValues = new JButton("Confirm board Selection");
         confirmValues.addActionListener(
            new confirmValuesActionListener());
         panel.add(confirmValues);
         wordFound = new JLabel();
      }
      else {
      // we Are Now loading the next sequence of this JFrame after board has been completed //
         if (boardFilledIn < 2) {
         
            panel.add(wordSearch);
            panel.add(wordSelected);
            confirmValues.setText("Update existing Board");
            panel.remove(tableError);
            panel.add(wordFound);
         }
      
      }
   
      
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
               new BoardSelectedFrame().setVisible(true);
            }
         
         
         });
   
   
   }
   
   private class confirmValuesActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
         try {
            path = new String[tableRowSize * tableRowSize];
         
            for (int j = 0; j < tableRowSize; j++) {  
               for (int i = 0; i < tableRowSize; i++) {
                  if (valuesOfTable[j][i] == null) {
                     throw new IllegalStateException();
                  }
                  path[j * tableRowSize + i] = valuesOfTable[j][i];
               }
            }
            game.setBoard(path);
            clicks++;
            updateCounter();
            boardFilledIn ++;
            setVisible(false);
         // Opens a new Instance of JFrame
               
            createView();
            
            // JFrame frame2 = new JFrame();
            // frame2.setTitle("Running wordsearch Engine");
         // //Make window exit application on close
            // frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
            // pack();
         // //set display size
            // frame2.setSize(300, 100);
         // //Center the frame to middle of screen
            // frame2.setLocationRelativeTo(null);
         // //Disable resize
            // frame2.setResizable(false);
         // 
            // frame2.setVisible(true);
            // JLabel computingTime = new JLabel("This process may take some time");
            // frame2.add(computingTime);
            // computingTime.setFont(new Font("Arial", Font.PLAIN, 30));            
            
            
            SortedSet<String> totalWords = new TreeSet<String>();
            totalWords = game.getAllValidWords(1);
            // frame2.setVisible(false);
            setVisible(true);
         
            for (String word: totalWords) {
               System.out.println("-------\n");
            
               System.out.println(word);
            }
            
            if (totalWords.size() > 500) {
               System.out.println("_____________________________________________________");
               System.out.print("\n   ******* Oh my palooza your board contained " + totalWords.size() + " words *******");
            }
            else if (totalWords.size() > 100) {
               System.out.println("_____________________________________________________");
               System.out.print("\n   ***** Oh my gosh your board contained " + totalWords.size() + " words *****");
            }
            
            else if (totalWords.size() > 50) {
               System.out.println("_____________________________________________________");
               System.out.print("\n   *** Wow your board contained " + totalWords.size() + " words ***");
            }
            
         }
         
         catch(Exception q) {
            tableError.setText("** You need to fill in all values of the Table to have a working board **");
            tableError.setForeground (Color.red);
            tableError.setFont(new Font("Arial", Font.PLAIN, 30));            
         }
      }
   }
   
   private class wordSelectedActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
         if (!game.isValidWord(wordSearch.getText().toUpperCase())) {
            questionWrong = new SoundEffect();
            questionWrong.setFile("soundEffects/smb_mariodie.wav");
            questionWrong.play();
            
            wordFound.setForeground (Color.blue);
            wordFound.setFont(new Font("Arial", Font.PLAIN, 25));
            
            wordFound.setText(":( " + wordSearch.getText() + " is not a valid work in the given Lexicon :(");           
         }
         
         else if (game.isOnBoard(wordSearch.getText().toUpperCase()).size() > 0) {
            questionRight = new SoundEffect();
            questionRight.setFile("soundEffects/smb_1-up.wav");
            questionRight.play();
         
            wordFound.setForeground (Color.green);
            wordFound.setFont(new Font("Arial", Font.PLAIN, 25));
            
            if (wordSearch.getText().length() > 1) {
            
               wordFound.setText("word " + wordSearch.getText() + " found on board");
            
            }
            else {
               wordFound.setText("letter " + wordSearch.getText() + " found on board");
            }
         
         }
         else {
            sounds.add(invalidQuestion);
            sounds.add(invalidQuestion2);
            sounds.add(invalidQuestion3);
            sounds.add(invalidQuestion4);
         
            
            Random rand = new Random();
            int temporary;
            temporary = rand.nextInt(sounds.size());
            sounds.get(temporary).play();
            wordFound.setForeground (Color.red);
            if (wordSearch.getText().length() > 1) {
            
               wordFound.setText("word " + wordSearch.getText() + " not located anywhere on board");
            
            }
            else {
               wordFound.setText("letter " + wordSearch.getText() + " not located anywhere on board");
            }
            wordFound.setFont(new Font("Arial", Font.PLAIN, 25));
         }
      
         
      }
   }
   
   public String[] getRawTableData() {
      return path;
   }
   
    
   @Override
      public void tableChanged(TableModelEvent e) {
      
      int row = e.getFirstRow();
      int column = e.getColumn();
      TableModel model = (TableModel)e.getSource();
      String columnName = model.getColumnName(column);
      Object data = model.getValueAt(row, column);
      String valuesOfTablePosition = data.toString().toUpperCase();
      if (valuesOfTablePosition != null) {
         valuesOfTable[row][column] = valuesOfTablePosition;
         
      }
         
   }
   
               

   
   
   
}