 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class PinballPanel extends JPanel
{
   private PinballMap map;
   private PinballScore score;
   public PinballPanel()
   {
      setLayout(new BorderLayout());
      score = new PinballScore();
      map = new PinballMap(score);
      add(map, BorderLayout.CENTER);
      add(score, BorderLayout.NORTH);
   }
}
