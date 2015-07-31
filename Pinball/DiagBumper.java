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
      System.out.println(xss[0]);
      myBuffer.fillPolygon(xss,yss, 4);
   }
   public boolean inBumper(Polkadot dot/*, Graphics buff*/)
   {
      // top right edg
      for(double x = 0; x <= myXSize*Math.cos(myAngle) ; x+=1.0)
      {
         double y = x * Math.tan(myAngle);
        /* buff.setColor(Color.RED);
         buff.drawOval((int)(x+xs[0]), (int)(y+ys[0]), 1, 1); */
         if(distance(x+xs[0], y+ys[0], dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;            
      }
      // bottom left edge
      for(double x = 0; x <= myXSize*Math.cos(myAngle) ; x+=1.0)
      {
         double y = x * Math.tan(myAngle);
        /* buff.setColor(Color.GREEN);
         buff.drawOval((int)(xs[2]-x), (int)( ys[2]-y), 1, 1);*/
         if(distance(xs[2]-x, ys[2]-y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;  
      }
      // top left edge
      for(double k = 0; k <= Math.abs(xs[0]-xs[3]); k+=1.0)
      {
         double x = k*sign(myAngle);
         double y = x/Math.tan(myAngle);
        /* buff.setColor(Color.blue);
         buff.drawOval((int)(xs[0]-x), (int)( ys[0]+y), 1, 1); */
         if(distance(xs[0]-x, ys[0]+y, dot.getX(), dot.getY()) <= dot.getRadius() )
            return true;
      }
      // top right edge
      for(double k = 0; k <= Math.abs(xs[1]-xs[2]); k+=1.0)
      {
         double x = k*sign(myAngle);
         double y = x/Math.tan(myAngle);
        /* buff.setColor(Color.blue);
         buff.drawOval((int)(xs[1]-x), (int)( ys[1]+y), 1, 1);*/
         if(distance(xs[1]-x, ys[1]+y, dot.getX(), dot.getY()) <= dot.getRadius() )
         {
            System.out.println("DFHADVHVADFHADFDHADFHADFHAGHFBFEHAAFHBFH");
         
            return true;  
         }
            
                           
      }
      return false; 
   }
   
      // returns distance between (xs[0], ys[0]) and (x2, y2)
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }
   
   public int sign(double d){
      return(int)( Math.abs(d)/d	);
   }
}