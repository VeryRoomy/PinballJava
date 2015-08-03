    // Phil Ero 15JUL08
import java.awt.event.*;
import java.awt.*;
import java.awt.event.KeyAdapter;



public class BumperCollisionPaddle
{
   private static double nearestX;	// used to approximate what point of the bumper  
   private static double nearestY;  // a ball collided with
   private static boolean pressed;
      
   public static void collide(DiagBumper bumper, Ball ball, Graphics myBuffer)
   {
   
         // see if the ball hit the bumper!
      if(bumper.inBumper(ball, myBuffer) && pressed==true)
      {	   	
            // back the ball up until it is just outside the bumper
         while(bumper.inBumper(ball, myBuffer))
         {
            System.out.println("in padel FGDFSFDBGSDFGSGDFGFDS");
            ball.setX(ball.getX() - ball.getdx()/10.0);
            ball.setY(ball.getY() - ball.getdy()/10.0);
         }
            
            // find the point on the edge of the bumper closest to the ball
         findImpactPoint(bumper, ball);
         	
         double ux=nearestX-ball.getX();
         double uy=nearestY-ball.getY();
         double ur=Math.sqrt(ux*ux+uy*uy);
         ux/=ur;
         uy/=ur;
         int dx=(int)ball.getdx();
         int dy=(int)ball.getdy();
         double dot_1= ux*dx+uy*dy;
         double dot_2=-uy*dx+ux*dy;
         dot_1*=-2.5; // this is the actual "bounce"
         double[] d = new double[2];
         d[0]=dot_1*ux-dot_2*uy;      //vector math
         d[1]=dot_1*uy+dot_2*ux;      //vector math
         dx=(int)Math.round(d[0]);
         dy=(int)Math.round(d[1]);
         ball.setdx(dx);
         ball.setdy(dy);
      }
      else if(bumper.inBumper(ball, myBuffer) && pressed==false)
      
      {
         while(bumper.inBumper(ball, myBuffer))
         {
            System.out.println("in padel FGDFSFDBGSDFGSGDFGFDS");
            ball.setX(ball.getX() - ball.getdx()/10.0);
            ball.setY(ball.getY() - ball.getdy()/10.0);
         }
            
            // find the point on the edge of the bumper closest to the ball
         findImpactPoint(bumper, ball);
         	
         double ux=nearestX-ball.getX();
         double uy=nearestY-ball.getY();
         double ur=Math.sqrt(ux*ux+uy*uy);
         ux/=ur;
         uy/=ur;
         int dx=(int)ball.getdx();
         int dy=(int)ball.getdy();
         double dot_1= ux*dx+uy*dy;
         double dot_2=-uy*dx+ux*dy;
         dot_1*=-.8; // this is the actual "bounce"
         double[] d = new double[2];
         d[0]=dot_1*ux-dot_2*uy;      //vector math
         d[1]=dot_1*uy+dot_2*ux;      //vector math
         dx=(int)Math.round(d[0]);
         dy=(int)Math.round(d[1]);
         ball.setdx(dx);
         ball.setdy(dy);
      }
   
   }
      
   private static void findImpactPoint(DiagBumper bumper, Ball ball)
   {
          // first assume the nw corner is closest
      nearestX = bumper.xs[0];  
      nearestY = bumper.ys[0];
         
         // now work around the edge of the bumper looking for a closer point
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = bumper.xs[0] + k*(bumper.xs[1] - bumper.xs[0]);
         double y = bumper.ys[0] + k*(bumper.ys[1] - bumper.ys[0]);
         updateIfCloser(x, y, ball);
      }
         
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = bumper.xs[1] + k*(bumper.xs[2] - bumper.xs[1]);
         double y = bumper.ys[1] + k*(bumper.ys[2] - bumper.ys[1]);
         
         updateIfCloser(x, y, ball);
      
      }
   
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = bumper.xs[2] + k*(bumper.xs[3] - bumper.xs[2]);
         double y = bumper.ys[2] + k*(bumper.ys[3] - bumper.ys[2]);
         
         updateIfCloser(x, y, ball);
      
      }
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = bumper.xs[3] + k*(bumper.xs[3] - bumper.xs[0]);
         double y = bumper.ys[3] + k*(bumper.ys[3] - bumper.ys[0]);
         
         updateIfCloser(x, y, ball);
      
      }
   
               
               
               
   }
         
      // makes (x,y) the nearest point if it is closer to the ball
   private static void updateIfCloser(double x, double y, Ball b)
   {
      if(distance(x, y, b.getX(), b.getY()) < distance(nearestX, nearestY, b.getX(), b.getY()))
      {
         nearestX = x;
         nearestY = y;
      }
   }
          
      // returns distance between (x1, y1) and (x2, y2)
   private static double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
   public static void setPressed(boolean x)
   {
      pressed = x;
   }
}