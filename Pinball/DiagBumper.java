//Name:              Date:
import java.awt.*;
public class DiagBumper
{
   public double[] xs = new double[4];
   public double[] ys = new double[4];
   public double myXSize, myYSize, myAngle;

   public DiagBumper(double x1, double y1, double s, double y, double a)
   {
      this.xs[0] = x1;
      this.ys[0] = y1;
      myXSize = s;
      myYSize = y;
      myAngle = a;
      
      xs[1] =  x1 + (myXSize* Math.cos(myAngle)); //top right x
      ys[1] =  y1 + (myXSize*Math.sin(myAngle)); //top right y
      xs[2] =  x1 + (myXSize*Math.cos(myAngle)-myYSize*Math.sin(myAngle)); //bottom right x
      ys[2] =  y1 + (myXSize*Math.sin(myAngle)) + (myYSize*Math.cos(myAngle));
      xs[3] =  x1 - (myYSize*Math.sin(myAngle));
      ys[3] =  y1 + (myYSize*Math.cos(myAngle));
   }
   public void draw(Graphics myBuffer) 
   {
      int[] xss = new int[4];
      int[] yss = new int[4];
      for (int k=0; k<4; k++)
      {
         xss[k]=(int)(xs[k]);
         yss[k]=(int)(ys[k]);
      }
      myBuffer.setColor(Color.black);
      myBuffer.fillPolygon(xss,yss, 4);
   }
   public void setX(int x , double n)
   {
      xs[x] = n;
   }
   public void setY(int y , double n)
   {
      ys[y] = n;
   }
   public double getX(int x)
   {
      return xs[x];
   }
   public double getY(int y)
   {
      return ys[y];
   }
   public void setAngle(double n)
   {
      myAngle = n;
      xs[1] =  xs[0] + (myXSize* Math.cos(myAngle)); //top right x
      ys[1] =  ys[0] + (myXSize*Math.sin(myAngle)); //top right y
      xs[2] =  xs[0] + (myXSize*Math.cos(myAngle)-myYSize*Math.sin(myAngle)); //bottom right x
      ys[2] =  ys[0] + (myXSize*Math.sin(myAngle)) + (myYSize*Math.cos(myAngle));
      xs[3] =  xs[0] - (myYSize*Math.sin(myAngle));
      ys[3] =  ys[0] + (myYSize*Math.cos(myAngle));
   }
   public double getAngle()
   {
      return myAngle;
   }



   public boolean inBumper(Polkadot dot, Graphics buff)
   {
      // top right edg
       for(double k = 0; k<1.0; k+=.01)
      {
         double x = xs[0] + k*(xs[1] - xs[0]);
         double y = ys[0] + k*(ys[1] - ys[0]);
         // buff.setColor(Color.BLUE);
      //    buff.drawOval((int)(x), (int)(y), 1, 1);
         if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;
      }
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = xs[1] + k*(xs[2] - xs[1]);
         double y = ys[1] + k*(ys[2] - ys[1]);
         // buff.setColor(Color.BLUE);
      //    buff.drawOval((int)(x), (int)(y), 1, 1);
         if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;
      }
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = xs[2] + k*(xs[3] - xs[2]);
         double y = ys[2] + k*(ys[3] - ys[2]);
        // buff.setColor(Color.BLUE);
      //    buff.drawOval((int)(x), (int)(y), 1, 1);
         if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;
      }
      for(double k = 0; k<1.0; k+=.01)
      {
         double x = xs[3] + k*(xs[0] - xs[3]);
         double y = ys[3] + k*(ys[0] - ys[3]);
        // buff.setColor(Color.BLUE);
      //    buff.drawOval((int)(x), (int)(y), 1, 1);
         if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;
      }      
      return false; 
   }
   
   
      // returns distance between (xs[0], ys[0]) and (x2, y2)
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
   
   public int sign(double d)
   {
      return(int)( Math.abs(d)/d	);
   }
}