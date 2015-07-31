import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinballMenu extends JPanel
{
   static JFrame menuFrame, contentFrame;
   
   public PinballMenu()
   {
      JButton play = new JButton("Play Pinball");
      play.addActionListener(new playListener());
      add(play);
   }
   private class playListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         menuFrame.setVisible(false);
         if(true/*contentFrame == null*/)
         {
            contentFrame = new JFrame("Pinball");
            contentFrame.setVisible(true);
            contentFrame.setLocation(300, 200);
            contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            contentFrame.setContentPane(new PinballPanel(menuFrame, contentFrame));
            contentFrame.setSize(600, 900);
         }
         else
         {
            contentFrame.setVisible(true);
         }
      }
   }
   public static void main(String[] args)
   {
      menuFrame = new JFrame("MAIN MENU");
      menuFrame.setSize(250, 100);
      menuFrame.setLocation(800, 50);
      menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      menuFrame.setContentPane(new PinballMenu());
      menuFrame.setVisible(true);
   }
}
          