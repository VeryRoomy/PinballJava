 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.sound.sampled.*;
public class PinballMap extends JPanel
{
   private Timer t1, t2;
   private static final ImageIcon venustoise = new ImageIcon("2aa.png");
   private BufferedImage myImage;
   private Graphics2D myBuffer;
   private PinballBumper[] multipliers;
   private PinballBumper bigbumper, medbumper, smallbumper, toptrio1, toptrio2, toptrio3, sideline;
   private Ball ball;
   private int FRAME = 1080;
   private PinballScore score;
   private int multiplier;
   private int[] onof;
   private DiagBumper bump, bump2, paddle, pHelper, downbump, topbump, fix;
   private DiagBumperRight bump3, bump4, paddleR, prHelper, downbumpR, channelbump1, channelbump2, topbumpR, fix2;
   private boolean pressed, pressed2;
   private int[] cooldown;
     
   File file;
   AudioInputStream stream;
   AudioFormat format;
   DataLine.Info info;
   Clip clip;
   
   public PinballMap(PinballScore s)
   {
      score = s;
      myImage =  new BufferedImage(FRAME - 380, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = (Graphics2D)myImage.getGraphics();
      //////////////////////////////////////////////
      t1 = new Timer(8, new Listener1());
      t2 = new Timer(1, new Listener2());
   
      myBuffer.setColor(new Color(208,208,208));
      myBuffer.fillRect(0,0,700,1080);
      myBuffer.setColor(Color.gray);
      
      myBuffer = (Graphics2D) (myImage.getGraphics());    
      myBuffer.setColor(Color.black);
      
   
      int x[] = {50, 50,  650, 650};
      int y[] = {750, 50, 50, 750};
      
   
      myBuffer.setStroke(new BasicStroke(20.0f));
      myBuffer.drawPolyline(x, y, 4);
      try
      {
         file = new File("mvp.wav");
         stream = AudioSystem.getAudioInputStream(file);
         format = stream.getFormat();
         info = new DataLine.Info(Clip.class, format);
         clip = (Clip) AudioSystem.getLine(info);
         clip.open(stream);
      }
      catch(Exception e)
      {
         System.exit(0);
      }
      cooldown = new int[3];
      onof = new int[3];
      multipliers = new PinballBumper[3];
      for(int i = 0; i < multipliers.length; i++)
      {
         multipliers[i] = new PinballBumper( (i*50)+300, 100, 30, Color.black);
         multipliers[i].draw(myBuffer);
      }
      
      setFocusable(true);
      requestFocus();
      addKeyListener(new Key());
      
      ball = new Ball(570, 350, 25, Color.black);
      ball.draw(myBuffer);
      ball.setdx(3);
      ball.setdy(5);
      
      bump = new DiagBumper(55, 750, 250, 15, 75);
      bump.draw(myBuffer);
      bump2 = new DiagBumper(120, 750, 250, 15, 75);
      bump2.draw(myBuffer);
      bump3 = new DiagBumperRight(645, 750, 250, 15, 75);
      bump3.draw(myBuffer);
      bump4 = new DiagBumperRight(580, 750, 250, 15, 75);
      bump4.draw(myBuffer);
      
      fix = new DiagBumper(200, 840, 15, 20, 35);
      fix.draw(myBuffer);
      fix2 = new DiagBumperRight(495, 840, 20, 15, 35);
      fix2.draw(myBuffer);
   
      
      pHelper = new DiagBumper(133, 800, 100, 15, 25);
      pHelper.draw(myBuffer);
      prHelper = new DiagBumperRight(565, 800, 100, 15, 25);
      prHelper.draw(myBuffer);
   
      
      paddle = new DiagBumper(200, 833, 130, 20, 35);
      paddle.draw(myBuffer);
      paddleR = new DiagBumperRight(500, 833, 130, 20, 35);
      paddleR.draw(myBuffer);
      
      downbump = new DiagBumper( 50, 615, 120, 15, 45);
      downbumpR = new DiagBumperRight( 650, 615, 120, 15, 45);
      downbump.draw(myBuffer);
      downbumpR.draw(myBuffer);  
      
      channelbump1 = new DiagBumperRight( 525, 300, 250, 15, 75);
      channelbump2 = new DiagBumperRight( 590, 450, 250, 15, 60);
      channelbump1.draw(myBuffer);
      channelbump2.draw(myBuffer);
      
      topbump = new DiagBumper( 200, 100, 150, 15, 65);
      topbumpR = new DiagBumperRight( 500, 100, 150, 15, 65);
      topbump.draw(myBuffer);
      topbumpR.draw(myBuffer);
      
      bigbumper = new PinballBumper( 290,400 ,150 , Color.red);
      medbumper = new PinballBumper( 140,450 ,100 , Color.pink);
      smallbumper = new PinballBumper(120 ,570 , 50 , Color.orange );
      bigbumper.draw(myBuffer);
      medbumper.draw(myBuffer);
      smallbumper.draw(myBuffer);
      
      toptrio1 = new PinballBumper(280, 150, 45, Color.red);
      toptrio2 = new PinballBumper(350, 200, 45, Color.green);
      toptrio3 = new PinballBumper(420, 150, 45, Color.blue);
      toptrio1.draw(myBuffer);
      toptrio2.draw(myBuffer);
      toptrio3.draw(myBuffer);
      
      sideline = new PinballBumper( 565, 200, 75, Color.white);
      sideline.draw(myBuffer);
          
      t1.start();
      t2.start();
      clip.loop(0);

   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      
      
   }
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int i = 0; i < cooldown.length; i++)
         {
            cooldown[i]--;
         }
         int x[] = {50, 50,  650, 650};
         int y[] = {750, 50, 50, 750};
      
         for(int i = 0; i < multipliers.length; i++)
         {
            if(cooldown[i] < 0){
               if(onof[i] == 0){
                  if(BumperCollisionNot.collide(multipliers[i], ball))
                  {
                     multipliers[i].setColor(Color.yellow);
                     onof[i] = 1;
                     multiplier += 5;
                     cooldown[i] = 50;
                  }
               }
               else if(onof[i] == 1)
               {
                  if(BumperCollisionNot.collide(multipliers[i], ball))
                  {
                     multipliers[i].setColor(Color.black);
                     onof[i] = 0;
                     multiplier -= 5;
                     cooldown[i] = 50;
                  }
               } 
            } 
         } 
         if(BumperCollisionCircular.collide(bigbumper, ball)){
            score.update(50, multiplier);
            // try
//             {
//                file = new File("ding.wav");
//                stream = AudioSystem.getAudioInputStream(file);
//                format = stream.getFormat();
//                info = new DataLine.Info(Clip.class, format);
//                clip = (Clip) AudioSystem.getLine(info);
//                clip.open(stream);
//             }
//             catch(Exception asd)
//             {
//                System.exit(0);
//             }
         
         }
         if(BumperCollisionCircular.collide(medbumper, ball)){
            score.update(50, multiplier);
            // clip.loop(0);
          
         }
            
         if(BumperCollisionCircular.collide(smallbumper, ball)){
            score.update(75, multiplier);
            // clip.loop(0);
         
         }
         if(BumperCollisionCircular.collide(toptrio1, ball)){
            score.update(100, multiplier); 
            // clip.loop(0);
         
         }
         if(BumperCollisionCircular.collide(toptrio2, ball)){
            score.update(100, multiplier);
            // clip.loop(0);
         
         }
         if(BumperCollisionCircular.collide(toptrio3, ball)){
            score.update(100, multiplier);
            // clip.loop(0);
         
         }  
         if(BumperCollisionCircular.collide(sideline, ball)){
            score.update(125, multiplier);
            // clip.loop(0);
         
         }  
         ///////////////////////////////////    
         score.checklives(ball.getY());
         if(ball.getY() > 1000)
         {
            ball.setX(570);
            ball.setY(350);
         }
         myBuffer.setColor(new Color(208,208,208));
         myBuffer.fillRect(0,0,700,1080);
         myBuffer.setColor(Color.black);
         myBuffer.setStroke(new BasicStroke(20.0f));
         myBuffer.drawPolyline(x, y, 4);
         
         myBuffer.setColor(Color.yellow);
         myBuffer.setStroke(new BasicStroke(20.0f));
         myBuffer.drawLine(0, 1000, 700, 1000);
         
         requestFocus();
      
         ball.move(650, 1080, 50, 50);
         for(int i = 0; i < multipliers.length; i++)
         {
            multipliers[i].draw(myBuffer);
         }
         repaint();
         //////////////////////////////////
         BumperCollisionDiag.collide(bump, ball, myBuffer);
         BumperCollisionDiag.collide(bump2, ball, myBuffer);
         BumperCollisionDiagRight.collide(bump3, ball, myBuffer);
         BumperCollisionDiagRight.collide(bump4, ball, myBuffer);
         BumperCollisionDiagRight.collide(prHelper, ball, myBuffer);
         BumperCollisionDiag.collide(pHelper, ball, myBuffer);
         BumperCollisionPaddleRight.collide(paddleR, ball, myBuffer,false);/// *** CHANGED
         BumperCollisionPaddle.collide(paddle, ball, myBuffer, false);
         BumperCollisionDiag.collide(downbump, ball, myBuffer);
         BumperCollisionDiagRight.collide(downbumpR, ball, myBuffer);
         BumperCollisionDiagRight.collide(channelbump1, ball, myBuffer);
         BumperCollisionDiagRight.collide(channelbump2, ball, myBuffer);
         BumperCollisionDiag.collide(topbump, ball, myBuffer);
         BumperCollisionDiagRight.collide(topbumpR, ball, myBuffer);
         BumperCollisionDiag.collide(fix, ball, myBuffer);
         BumperCollisionDiagRight.collide(fix2, ball, myBuffer);
      
         
            
      
      
         ball.draw(myBuffer);  
         bump.draw(myBuffer);
         bump2.draw(myBuffer);
         bump3.draw(myBuffer);
         bump4.draw(myBuffer);
         //fix.draw(myBuffer);
         //fix2.draw(myBuffer);
         pHelper.draw(myBuffer);
         prHelper.draw(myBuffer);
         downbump.draw(myBuffer);
         downbumpR.draw(myBuffer);
         channelbump1.draw(myBuffer);
         channelbump2.draw(myBuffer);
         paddle.draw(myBuffer);
         paddleR.draw(myBuffer);
         topbump.draw(myBuffer);
         topbumpR.draw(myBuffer);
         bigbumper.draw(myBuffer);
         medbumper.draw(myBuffer);
         smallbumper.draw(myBuffer);
         toptrio1.draw(myBuffer);
         toptrio2.draw(myBuffer);
         toptrio3.draw(myBuffer);
         sideline.draw(myBuffer);
         
      
         repaint();
//          clip.stop();
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
            boolean hit = false;
         
            bpress = true ;
            BumperCollisionPaddle.setPressed(true);
            for (int i = 0 ; i < 25 ; i ++)
            {
               paddle.setAngle( paddle.getAngle() - 2);     
               paddle.draw(myBuffer);
               repaint();         
               if( BumperCollisionPaddle.collide(paddle, ball, myBuffer, hit) )/// *** CHANGED
                  hit = true;/// *** CHANGED
                 
               while (paddle.inBumper(ball, myBuffer))
                  
                  ball.move(650, 1080, 50, 50);    
                  
            }
            
            BumperCollisionPaddle.setPressed(false);
            
            
         }
         else if(e.getKeyCode() == KeyEvent.VK_B && !bpress2)
         {
            boolean hit2 = false;
            bpress2 = true ;
            BumperCollisionPaddleRight.setPressed(true);
            for (int i = 0 ; i < 25 ; i ++)
            {
               paddleR.setAngle( paddleR.getAngle() - 2);    
               paddleR.draw(myBuffer);
               repaint();          
               //if(!hit2)               
               if( BumperCollisionPaddleRight.collide(paddleR, ball, myBuffer, hit2) )/// *** CHANGED
                  hit2 = true;/// *** CHANGED
                 
               while (paddleR.inBumper(ball, myBuffer)){
                  ball.move(650, 1080, 50, 50);    
               }   
            }
            BumperCollisionPaddleRight.setPressed(false); 
         }
      
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode() == KeyEvent.VK_V)
         {
            bpress = false ;
            paddle.setAngle(35);
                
            BumperCollisionPaddle.setPressed(false);
         
         }
         if(e.getKeyCode() == KeyEvent.VK_B)
         {
            bpress2 = false ;
            paddleR.setAngle(35);
         
            BumperCollisionPaddleRight.setPressed(false);
         
         }
      
      }
   
   
   }
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         repaint();
      }
   }


}

