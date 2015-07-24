 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class PinballMap extends JPanel
{
   private Timer t1, t2;
   private static final ImageIcon venustoise = new ImageIcon("2aa.png");
   private BufferedImage myImage;
   private Graphics2D myBuffer;
   private PinballBumper bumper1;
   private Ball ball;
   private int FRAME = 900;
   public PinballMap()
   {
      myImage =  new BufferedImage(FRAME-300, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = (Graphics2D)myImage.getGraphics();
      t1 = new Timer(2, new Listener1());
      myBuffer.setColor(new Color(208,208,208));
      myBuffer.fillRect(0,0,600,900);
      myBuffer.setColor(Color.gray);
      
      myBuffer = (Graphics2D) (myImage.getGraphics());    
      myBuffer.setColor(Color.black);
      
   
      int x[] = {50, 50,  550, 550};
      int y[] = {700, 50, 50, 700};
      
   
      myBuffer.setStroke(new BasicStroke(20.0f));
      myBuffer.drawPolyline(x, y, 4);
      
      bumper1 = new PinballBumper ();
      bumper1.draw(myBuffer);
      
      ball = new Ball(100, 100, 25, Color.black);
      ball.draw(myBuffer);
      
      
      t1.start();
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      
   }
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         int x[] = {50, 50,  550, 550};
         int y[] = {700, 50, 50, 700};
      
         myBuffer.setColor(new Color(208,208,208));
         myBuffer.fillRect(0,0,600,900);
         myBuffer.setColor(Color.black);
         myBuffer.setStroke(new BasicStroke(20.0f));
         myBuffer.drawPolyline(x, y, 4);
         ball.move(550, 700, 50, 55);
         bumper1.draw(myBuffer);
         repaint();
         ball.draw(myBuffer);
      
      }
   
   }
}
