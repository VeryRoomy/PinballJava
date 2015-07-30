//Name:              Date:
import java.awt.*;
public class DiagBumper
{

   public DiagBumper(double x1, double y2, double myXSize, double myYSize, double myAngle)
      {
      double x2 = x1 + (myXSize* Math.cos(myAngle)); //top right x
      double y2 = y1 + (myXSize*Math.sin(myAngle)); //top right y
      double x3 = x1 + (myXSize*Math.cos(myAngle)-myYWidth*Math.sin(myAngle)); //bottom right x
      double y3 = y1 + (myXSize*Math.sin(myAngle)) + (myYSize*Math.cos(myAngle));
      double x4 = x1 - (myYSize*Math.sin(myAngle));
      double y4 = y1 + (myYSize*Math.cos(myAngle));
      double xs[] = {x1, x2, x3, x4};
      double ys[] = {y1, y2, y3, y4};     
      }
public void draw(Graphics myBuffer) 
   {
      myBuffer.fillPolygon(xs[], ys[], 4);
   }
   public boolean inBumper(Polkadot dot)
   {
      for(int x = getX(); x <= getX() + getXWidth(); x++)   //starts at upper left corner(x,y)
         for(int y = getY(); y <= getY() + getYWidth(); y++)
            if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() ) //checks every point on the bumper
               return true;            
      return false;
   }  
      // returns distance between (x1, y1) and (x2, y2)
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }	
}