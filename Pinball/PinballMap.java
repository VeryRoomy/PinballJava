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
   private PinballBumper[][] bumpers;
   private Ball ball;
   private int FRAME = 900;
   private PinballScore score;
   public PinballMap(PinballScore s)
   {
      score = s;
      myImage =  new BufferedImage(FRAME-300, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = (Graphics2D)myImage.getGraphics();
      t1 = new Timer(10, new Listener1());
      myBuffer.setColor(new Color(208,208,208));
      myBuffer.fillRect(0,0,600,900);
      myBuffer.setColor(Color.gray);
      
      myBuffer = (Graphics2D) (myImage.getGraphics());    
      myBuffer.setColor(Color.black);
      
   
      int x[] = {50, 50,  550, 550};
      int y[] = {700, 50, 50, 700};
      
   
      myBuffer.setStroke(new BasicStroke(20.0f));
      myBuffer.drawPolyline(x, y, 4);
      
      bumpers = new PinballBumper[5][5];
      int n = bumpers.length;
      for(int r = 0; r < n; r++)
         for(int c = 0; c < bumpers[0].length; c++)
         {
            bumpers[r][c] = new PinballBumper( (r * 100)+100, (c*100)+100, 50, Color.red);
            bumpers[r][c].draw(myBuffer);
         }
      ball = new Ball(155, 100, 25, Color.black);
      ball.draw(myBuffer);
      ball.setdx(3);
      ball.setdy(5);
   
      
      
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
         for(int r = 0; r < bumpers.length; r++)
            for(int c = 0; c < bumpers[0].length; c++)
            {
               if(BumperCollisionCircular.collide(bumpers[r][c], ball))
               {
                  score.update(20);
               }
            }
         myBuffer.setColor(new Color(208,208,208));
         myBuffer.fillRect(0,0,600,900);
         myBuffer.setColor(Color.black);
         myBuffer.setStroke(new BasicStroke(20.0f));
         myBuffer.drawPolyline(x, y, 4);
         ball.move(550, 700, 50, 55);
         for(int r = 0; r < bumpers.length; r++)
            for(int c = 0; c < bumpers[0].length; c++)
            {
               bumpers[r][c].draw(myBuffer);
            }
         repaint();
         ball.draw(myBuffer);
      
      }
   
   }
}
