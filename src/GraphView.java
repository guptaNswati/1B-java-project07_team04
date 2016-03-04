
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class GraphView extends JPanel
{
   private static final int POINT_SIZE = 1;
   private int width;
   private int height;
   private Font font;
   
   // constructor for GraphView
   GraphView(int width, int height, LinkedList<Country> countries)
   {
       this.width = width;
       this.height = height;
       font = new Font("Serif", Font.PLAIN, 11);  
      
     
      Iterator<SubscriptionYear> iterator = countries.getNodeAtIndex(0).getData().getSubscriptions().iterator();
      
      int dataMinX = 9999;
      int dataMaxX = 0;
      
      double dataMinY = 9999;
      double dataMaxY = 0.0;
      
      
      SubscriptionYear current = null;
      
      while (iterator.hasNext())
      {
          current = iterator.next();
       // update the minYear as minimum year
          if (current.getYear() < dataMinX)        
              dataMinX = current.getYear();
       
          // update the maxYear as the maximum year, each time new node is added to list
          else if (current.getYear() > dataMaxX)        
              dataMaxX = current.getYear();
      }
      
      Country currentCountry;
      Iterator<Country> iterator_c = countries.iterator();
      
      while (iterator_c.hasNext())
      {
          currentCountry = iterator_c.next();
       // update the minYear as minimum year
          if (currentCountry.getSubscriptions(). < dataMinY)        
              dataMinX = current.getYear();
       
          // update the maxYear as the maximum year, each time new node is added to list
          else if (current.getYear() > dataMaxX)        
              dataMaxX = current.getYear();
      }
      
   }
   
   public static final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax)
   {
       return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax- dataMin));
   }
   
   /**
    * @Todo complete
    */
   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       ColoredPoint current;
       g2d.setColor(current.getColor());    
       g2d.fillOval((int)current.getX(), (int)current.getY(), POINT_SIZE, POINT_SIZE);
       g2d.drawString(label, (int)current_point.getX(),(int)current_point.getY()); 
   }

}
