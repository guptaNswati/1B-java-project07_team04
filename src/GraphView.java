
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class GraphView extends JPanel
{
   private static final int POINT_SIZE = 5;
   private int width;
   private int height;
   private Font font;
   private int minYearX;
   private int maxYearX;
   private double dataMinY;
   private double dataMaxY;
  
   
   // constructor for GraphView
   GraphView(int width, int height, LinkedList<Country> countries)
   {
       this.width = width;
       this.height = height;
       font = new Font("Serif", Font.PLAIN, 11); 
       
       this.minYearX = countries.getNodeAtIndex(0).getData().getMinYear();
       this.maxYearX = countries.getNodeAtIndex(0).getData().getMaxYear();
       
       dataMinY = 0.0;
       dataMaxY = 0.0;
       
       Iterator <Country> iterator = countries.iterator();
       
       Country current = null;
       
       while(iterator.hasNext())
       {
           current = iterator.next();
           
           if(current.getMaxSubscription() > dataMaxY)
               dataMaxY = current.getMaxSubscription();        
       }
       
//       while(iterator)
    
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
       ColoredPoint current = new ColoredPoint(Color.BLUE, this.minYearX, this.dataMinY, 160 , 140);
       g2d.setColor(current.getColor());    
       g2d.fillOval((int)current.getX(), (int)current.getY(), POINT_SIZE, POINT_SIZE);
       g2d.drawString(current.getLabel(), (int)current.getX(),(int)current.getY()); 
   }

}
