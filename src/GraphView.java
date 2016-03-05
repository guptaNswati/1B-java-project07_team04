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
      // System.out.println(minYearX);
       this.maxYearX = countries.getNodeAtIndex(0).getData().getMaxYear();
       //System.out.println(maxYearX);
       
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
           
           //System.out.println("MAX DATA VALUE FOR ENTIRE DATA " + dataMaxY);
           
           double originalX;
           double originalY;
           double mappedX;
           double mappedY;
                   
           SubscriptionYear currentSubscription;
   
           
           Iterator<SubscriptionYear> iterator_s =  current.subscriptions.iterator();
           
           while(iterator_s.hasNext())
           {
               currentSubscription = iterator_s.next();
               
               originalX = currentSubscription.getYear();
               System.out.println(originalX);
               originalY = currentSubscription.getSubscriptions();
               //System.out.println(originalY);
               
               mappedX = map(originalX, minYearX, maxYearX, MARGIN, WIDTH_MARGIN);
              // System.out.println(mappedX);
               
               mappedY = map(originalY, dataMinY, dataMaxY, HEIGHT_MARGIN, MARGIN);
               //System.out.println(mappedY);
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
    
      
//       Iterator<MappedValues>map_iterator = mappedCoordinates.iterator();
////       for(int i = 0; i < this.mappedCoordinates.size(); i++)
////       {
//       		while(map_iterator.hasNext()){
//       		MappedValues current = map_iterator.next();
     		double originalXValue = mappedCoordinates.getNodeAtIndex(0).getData().getOriginalX();
         double originalYValue = mappedCoordinates.getNodeAtIndex(0).getData().getOriginalY();
         int xCoordinates= (int)mappedCoordinates.getNodeAtIndex(0).getData().getXCoordinate();
         int yCoordinate = (int)mappedCoordinates.getNodeAtIndex(0).getData().getYCoordinate();
         System.out.println("Original Years: " + originalXValue);
         System.out.println("Original data "+ originalYValue);
         System.out.println("X-Coordinates " + xCoordinates);
         System.out.println("Y-coordinates " + yCoordinate);
//         
       	Graphics2D g2d = (Graphics2D) g;
          
         ColoredPoint current_cp = new ColoredPoint(Color.BLUE, originalXValue, originalYValue, xCoordinates, yCoordinate);
      // ColoredPoint current = new ColoredPoint(Color.BLUE, this.minYearX, this.dataMinY, 160 , 140);
         g2d.setColor(current_cp.getColor());    
         g2d.fillOval((int)current_cp.getX(), (int)current_cp.getY(), POINT_SIZE, POINT_SIZE);
         g2d.drawString(current_cp.getLabel(), (int)current_cp.getX(),(int)current_cp.getY()); 
 
    	   
      
       
       
   }

}