    // Phil Ero 15JUL08
    
    public class BumperCollisionNot
   {
      private static double nearestX;	// used to approximate what point of the bumper  
      private static double nearestY;  // a ball collided with
   
       public static boolean collide(PinballBumper bumper, Ball ball)
      {
         // see if the ball hit the bumper!
         if(bumper.inBumper(ball))
         {	   	
            // back the ball up until it is just outside the bumper
            while(bumper.inBumper(ball))
            {
               ball.setX(ball.getX() - ball.getdx()/10.0);
               ball.setY(ball.getY() - ball.getdy()/10.0);
            }
            
            // find the point on the edge of the bumper closest to the ball
            findImpactPoint(bumper, ball);
         	
            /*double ux=nearestX-ball.getX();
            double uy=nearestY-ball.getY();
            double ur=Math.sqrt(ux*ux+uy*uy);
            ux/=ur;
            uy/=ur;
            int dx=(int)ball.getdx();
            int dy=(int)ball.getdy();
            double dot_1= ux*dx+uy*dy;
            double dot_2=-uy*dx+ux*dy;
            dot_1*=-1.2; // this is the actual "bounce"
            double[] d = new double[2];
            d[0]=dot_1*ux-dot_2*uy;      //vector math
            d[1]=dot_1*uy+dot_2*ux;      //vector math
            dx=(int)Math.round(d[0]);
            dy=(int)Math.round(d[1]);
            ball.setdx(dx);
            ball.setdy(dy);*/
            return true;
         }
         return false;
      }
      
       private static void findImpactPoint(PinballBumper bumper, Ball ball)
      {
          // first assume the nw corner is closest
         nearestX = bumper.getX();  
         nearestY = bumper.getY();
         
         //loop over every point on the edge of bumper and updateIfCloser
         
         for (int theta = 0; theta < 360; theta += 5){
            int x1 = (int)(bumper.getX() + bumper.getRadius() * Math.cos(theta * Math.PI /180));
            int y1 = (int)(bumper.getY() + bumper.getRadius() * Math.sin(theta * Math.PI /180));
            updateIfCloser(x1, y1, ball);
         }
         
         /*    FOR RECTANGLES !!!
         // now work around the edge of the bumper looking for a closer point
         for (double x = bumper.getX(); x <= bumper.getX() + bumper.getRadius(); x++)  // top & bottom edges
         {
            updateIfCloser(x, bumper.getY(), ball);
            updateIfCloser(x, bumper.getY() + bumper.getRadius(), ball);
         }
         for (double y = bumper.getY(); y <= bumper.getY() + bumper.getRadius(); y++)  // right & left edges
         {
            updateIfCloser(bumper.getX(), y, ball);
            updateIfCloser(bumper.getX() + bumper.getRadius(), y, ball);
         }*/
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