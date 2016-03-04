
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
   private final double MARGIN = 20;
   private final double WIDTH_MARGIN = 800;
   private final double HEIGHT_MARGIN = 600;   
   private LinkedList<MappedValues> mappedCoordinates;
   
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
       
       LinkedList<MappedValues> mappedCoordinates = new LinkedList<MappedValues>();
       
       
       while(iterator.hasNext())
       {
           current = iterator.next();
           
           if(current.getMaxSubscription() > dataMaxY)
           {
               dataMaxY = current.getMaxSubscription();
           }
           
           double originalX;
           double originalY;
           double mappedX;
           double mappedY;
                   
           SubscriptionYear currentSubscription;
   
           
           Iterator<SubscriptionYear> iterator_s =  current.getSubscriptions().iterator();
           
           while(iterator_s.hasNext())
           {
               currentSubscription = iterator_s.next();
               
               originalX = currentSubscription.getYear();
               originalY = currentSubscription.getSubscriptions();
               
               mappedX = map(originalX, minYearX, maxYearX, MARGIN, WIDTH_MARGIN);
               
               mappedY = map(originalY, dataMinY, dataMaxY, HEIGHT_MARGIN, MARGIN);
               
               MappedValues current_mapValues = new MappedValues(originalX, originalY, mappedX, mappedY);
               
               mappedCoordinates.add(current_mapValues);               
              
           }           
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
      
       ColoredPoint current = new ColoredPoint(Color.BLUE, this.minYearX, this.dataMinY, 160 , 140);
       g2d.setColor(current.getColor());    
       g2d.fillOval((int)current.getX(), (int)current.getY(), POINT_SIZE, POINT_SIZE);
       g2d.drawString(current.getLabel(), (int)current.getX(),(int)current.getY()); 
       
       for(int i = 0; i < mappedCoordinates.size(); i++)
       {
           ColoredPoint current = new ColoredPoint(Color.GREEN, mappedCoordinates.getNodeAtIndex(i));
           
       }
       
       
       
   }

}
