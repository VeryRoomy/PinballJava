 //Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 6.17.2003

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class PinballPanel extends JPanel
{
   private PinballMapTest map;
   private PinballScore score;
   public PinballPanel()
   {
      setLayout(new BorderLayout());
      score = new PinballScore();
      map = new PinballMapTest(score);
      add(map, BorderLayout.CENTER);
      add(score, BorderLayout.NORTH);
   }
}
