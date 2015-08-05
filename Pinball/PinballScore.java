 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
public class PinballScore extends JPanel
{
   private JLabel label;
   private int score;
   private int lives;
   private JLabel labellives;
   private JLabel highscore;
   public PinballScore()
   {
      lives = 5;
      label = new JLabel("Your score: " + 0);
      add(label);
      labellives = new JLabel("Lives: " + lives);
      add(labellives);
      Scanner infile = null;
      try{
         infile = new Scanner(new File("highscores.txt"));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be found.");
         System.exit(0);
      }
      highscore = new JLabel("Highscore: " + infile.nextInt());
      add(highscore);
   }
   public void update(int x, int mult)
   {
      if(mult == 0)
      {
         score += x;
      }
      else
      {
         score += x*mult;
      }
      label.setText("Your score: " + score);
   }
   public void checklives(double y)
   {
      if(y > 1000)
      {
         lives--;
         labellives.setText("Lives: " + lives);
         if(lives < 1)
         {
            Scanner infile = null;
            try{
               infile = new Scanner(new File("highscores.txt"));
            }
            catch(FileNotFoundException e)
            {
               JOptionPane.showMessageDialog(null,"The file could not be found.");
               System.exit(0);
            }
            
            if(infile.nextInt() < score)
            {
               PrintStream outfile = null;
               try{
                  outfile = new PrintStream(new FileOutputStream("highscores.txt"));
               }
               catch(FileNotFoundException e)
               {
                  JOptionPane.showMessageDialog(null,"The file could not be created.");
               }
               outfile.println("" + score);
            }
            System.exit(0);
         }
      }
   }
}
