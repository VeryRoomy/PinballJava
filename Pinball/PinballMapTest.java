 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.sound.sampled.*;
import java.io.File;
public class PinballMapTest extends JPanel
{
   private Timer t1, t2;
   private static final ImageIcon venustoise = new ImageIcon("2aa.png");
   private BufferedImage myImage;
   private Graphics2D myBuffer;
   private PinballBumper[][] bumpers;
   private PinballBumper[] multipliers;
   private Ball ball;
   private int FRAME = 900;
   private PinballScore score;
   DiagBumperRight diag;
   DiagBumper diag2;

   private int multiplier;
   private int[] onof;
   
      
   public PinballMapTest(PinballScore s)
   {
      score = s;
      myImage =  new BufferedImage(FRAME-300, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = (Graphics2D)myImage.getGraphics();
      t1 = new Timer(8, new Listener1());
   
      myBuffer.setColor(new Color(208,208,208));
      myBuffer.fillRect(0,0,600,900);
      myBuffer.setColor(Color.gray);
      
      myBuffer = (Graphics2D) (myImage.getGraphics());    
      myBuffer.setColor(Color.black);
      
   
      int x[] = {50, 50,  550, 550};
      int y[] = {700, 50, 50, 700};
      ///////
      setFocusable(true);
      requestFocus();
      addKeyListener(new Key());
   //////////////
      myBuffer.setStroke(new BasicStroke(20.0f));
      myBuffer.drawPolyline(x, y, 4);
      
            
      onof = new int[3];
      for(int i = 0; i < onof.length; i++)
         onof[i] = 0;
      multipliers = new PinballBumper[3];
      for(int i = 0; i < multipliers.length; i++)
      {
         multipliers[i] = new PinballBumper( (i*50)+250, 100, 30, Color.black);
         multipliers[i].draw(myBuffer);
      }
      bumpers = new PinballBumper[5][4];
      int n = bumpers.length;
            
      ball = new Ball(155, 100, 25, Color.black);
      ball.draw(myBuffer);
      ball.setdx(3);
      ball.setdy(5);
      
      diag = new DiagBumperRight(400, 350, 200, 25, .5);
      diag.draw(myBuffer);
      diag2 = new DiagBumper(200, 500, 200, 25, .5);
      diag2.draw(myBuffer);
      
      
   
      
      
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
      
         for(int i = 0; i < multipliers.length; i++)
         {
            if(onof[i] == 0)
               if(BumperCollisionNot.collide(multipliers[i], ball))
               {
                  multipliers[i].setColor(Color.yellow);
                  onof[i] = 1;
                  multiplier += 5;
               }
               else if(onof[i] == 1)
               {
                  if(BumperCollisionNot.collide(multipliers[i], ball))
                  {
                     multipliers[i].setColor(Color.black);
                     onof[i] = 0;
                     multiplier -= 5;
                  }
               }  
         }
         myBuffer.setColor(new Color(208,208,208));
         myBuffer.fillRect(0,0,600,900);
         myBuffer.setColor(Color.black);
         myBuffer.setStroke(new BasicStroke(20.0f));
         myBuffer.drawPolyline(x, y, 4);
      
         
         ball.move(550, 700, 50, 55);
         BumperCollisionPaddleRight.collide(diag, ball, myBuffer); 
         BumperCollisionPaddle.collide(diag2, ball, myBuffer);
      
               
      
         for(int i = 0; i < multipliers.length; i++)
         {
            multipliers[i].draw(myBuffer);
         }
         ball.draw(myBuffer);   
         diag2.draw(myBuffer);
         diag.draw(myBuffer);
         repaint();
      
      }
   
   }
   private class Key extends KeyAdapter
   {
      private boolean bpress = false ;
      private boolean bpress2 = false ;

      
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_V && !bpress)
         {
            bpress = true ;
            BumperCollisionPaddle.setPressed(true);
            diag2.setAngle( diag2.getAngle() - .25);
            diag2.setAngle( diag2.getAngle() - .25);
         
            
         }
         else if(e.getKeyCode() == KeyEvent.VK_B && !bpress2)
         {
            bpress2 = true ;
            BumperCollisionPaddleRight.setPressed(true);
            diag.setAngle( diag.getAngle() - .25);
            diag.setAngle( diag.getAngle() - .25);
         
            
         }

      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_V)
         {
            bpress = false ;
            diag2.setAngle( diag2.getAngle() + .25);
            
            diag2.setAngle( diag2.getAngle() + .25);
         
            BumperCollisionPaddle.setPressed(false);
         
         }
         if(e.getKeyCode() == KeyEvent.VK_B)
         {
            bpress2 = false ;
            diag.setAngle( diag.getAngle() + .25);
            
            diag.setAngle( diag.getAngle() + .25);
         
            BumperCollisionPaddleRight.setPressed(false);
         
         }

      }
   
   
   }
}
