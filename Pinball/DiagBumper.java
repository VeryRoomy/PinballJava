//Name:              Date:
import java.awt.*;
public class DiagBumper
{

   public DiagBumper(int x1, int y2, int x2, int y2, int x3, int y3, int x4, int y4)
      {
      int xs[] = {x1, x2, x3, x4};
      int ys[] = {y1, y2, y3, y4};     
      }
public void draw(Graphics myBuffer) 
   {
      myBuffer.fillPolygon(xs[], ys[], 4);
   }