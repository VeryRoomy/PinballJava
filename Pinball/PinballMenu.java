import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;

public class PinballMenu extends JPanel
{
   static JFrame menuFrame, contentFrame;
   
   public PinballMenu()
   {
      
       ImageIcon image = new ImageIcon("pinballwizard.png");
       
      setLayout(new BorderLayout());
      JLabel picture = new JLabel();
      picture.setIcon(image);
      add(picture, BorderLayout.NORTH);
      JButton play = new JButton("Play Pinball");
      play.addActionListener(new playListener());
      add(play, BorderLayout.SOUTH);
      
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
            contentFrame.setLocation(600, 0);
            contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            contentFrame.setContentPane(new PinballPanel(menuFrame, contentFrame));
            contentFrame.setSize(700, 1080);
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
      menuFrame.setSize(400, 400);
      menuFrame.setLocation(800, 500);
      menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      menuFrame.setContentPane(new PinballMenu());
      menuFrame.setVisible(true);
   }
}
