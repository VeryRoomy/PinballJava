    // Phil Ero 15JUL08
    
public class BumperCollisionDiag
{
   private static double nearestX;	// used to approximate what point of the bumper  
   private static double nearestY;  // a ball collided with
   
   public static void collide(DiagBumper bumper, Ball ball)
   {
         // see if the ball hit the bumper!
      if(bumper.inBumper(ball))
      {	   	
            // back the ball up until it is just outside the bumper
         while(bumper.inBumper(ball))
         {
            System.out.println("in bumper");
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
         dot_1*=-0.8; // this is the actual "bounce"
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
      for(double x = 0; x <=bumper.myXSize*Math.cos(bumper.myAngle) ; x+=1.0)
      {         
      double y = x * Math.tan(bumper.myAngle);

         double y1 = bumper.ys[0] + y;
         double x1 = bumper.xs[0] + x;        
         updateIfCloser(x1, y1, ball);
      
      }
      // bottom left edge
      for(double x = 0; x <= bumper.myXSize*Math.cos(bumper.myAngle) ; x+=1.0)
      {
         double y = x * Math.tan(bumper.myAngle);
      
         double x1 = bumper.xs[2]-x;
         double y1 = bumper.ys[2] - y;
         updateIfCloser(x1, y1, ball);
      
      }
               // top left edge
      for(double k = 0; k <= Math.abs(bumper.xs[0]-bumper.xs[3]); k+=1.0)
      {
         double x1 = k*bumper.sign(bumper.myAngle);
         double y1 = x1/Math.tan(bumper.myAngle);
         updateIfCloser(bumper.xs[0]-x1,bumper.ys[0] + y1, ball);
      
      }
      // top right edge
      for(double k = 0; k <= Math.abs(bumper.xs[1]-bumper.xs[2]); k+=1.0)
      {
         double x1 = k*bumper.sign(bumper.myAngle);
         double y1 = x1/Math.tan(bumper.myAngle);
         updateIfCloser(bumper.xs[1]-x1, bumper.ys[1] + y1, ball);
      
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
}