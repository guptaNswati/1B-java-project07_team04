
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class GraphView extends JPanel
{
   private static final int POINT_SIZE = 5;
   private final int MARGIN = 20;
   private int width; // width of JPanel
   private int height; // height of JPanel
   private Font font;
  
   // instances variables to map the data points to the drawing area 
   private int plottedXmin; 
   private int plottedXmax; 
   private int plottedYmin; 
   private int plottedYmax;
   
   //the starting and ending year as well as the minimum and maximum subscription data 
   private int dataMinX;
   private int dataMaxX;
   private double dataMinY;
   private double dataMaxY;   
   
   private LinkedList<PlottedDataSet> listOfCountryDataPoints;
   
   // constructor for GraphView
   GraphView(int width, int height, LinkedList<Country> countries)
   {
       this.width = width;
       this.height = height;
       
       this.plottedXmin = MARGIN;
       this. plottedXmax = width - MARGIN; 
       
       this.plottedYmin = MARGIN;
       this. plottedYmax = height - MARGIN; 
       
       font = new Font("Serif", Font.PLAIN, 11); 
       
       // using the subscriptions of one country to determine the starting year (dataMinX) and ending year (dataMaxX)
       this.dataMinX = countries.getNodeAtIndex(0).getData().getMinYear();
       this.dataMaxX = countries.getNodeAtIndex(0).getData().getMaxYear();
       
       // setting the minimum subscription value (dataMinY) is quick: it’s zero!       
       dataMinY = 0.0;
       
       // To set the maximum subscription value we need to run through all the subscriptions of all the countries 
       //to find the largest one
       dataMaxY = 0.0;
             
       Iterator <Country> iterator = countries.iterator();
       
       Country current;
       
       // list of plottedDataPoints holding list of coloredPoints for each country
       LinkedList<PlottedDataSet> listOfCountryDataPoints = new LinkedList<PlottedDataSet>();
     
       while(iterator.hasNext())
       {
           current = iterator.next();
           
           if (current.getMaxSubscription() > dataMaxY)
           {
               dataMaxY = current.getMaxSubscription();
           }
           
           // After determining the dataMinX, dataMaxX, dataMinY and dataMaxY, 
           // going through all the data once and saving the mapped values
           double originalX;
           double originalY;
           double mappedX;
           double mappedY;
           
           // current node of type PlottedDataSet holding a list of coloredPoints for each country 
           //to be stored in listOfCountryDataPoints 
           PlottedDataSet DataPoints = new PlottedDataSet();;
          
           SubscriptionYear currentSubscription;
   
           Iterator<SubscriptionYear> iterator_s =  current.getSubscriptions().iterator();
           
           while(iterator_s.hasNext())
           {
               currentSubscription = iterator_s.next();
               
               originalX = currentSubscription.getYear();
               
               originalY = currentSubscription.getSubscriptions();              
               
               mappedX = map(originalX, dataMinX, dataMaxX, plottedXmin, plottedXmax);
               
               mappedY = map(originalY, dataMinY, dataMaxY, plottedYmin, plottedYmax);
               
               DataPoints.addDataPoints(originalX, originalY, mappedX, mappedY);
                                           
           } 
           listOfCountryDataPoints.add(DataPoints);           
       }
 
   }
   
   public static final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax)
   {
       return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax- dataMin));
   }
   
   /**
    * @Todo complete
    */
   protected void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
    
       Iterator<PlottedDataSet> iterator_P =  this.listOfCountryDataPoints.iterator();
       
       PlottedDataSet currentDataPoints;
       
       while(iterator_P.hasNext())
       {
           currentDataPoints = iterator_P.next();
   
           for (int i = 0; i < currentDataPoints.getDataPoints().size(); i++)
           {
           g2d.setColor(currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getColor());
           g2d.fillOval((int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getX(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getY(), 
                   POINT_SIZE, POINT_SIZE);
           g2d.drawString(currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getLabel(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getX(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getY());       
           }         
       }
   }

}
