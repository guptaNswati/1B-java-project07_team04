/**
 * 
 */
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphView extends JPanel
{
   private static final int POINT_SIZE = 9;
   private final int MARGIN = 27;
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
   
   private static LinkedList<Legend> listOfLegends;
   
   // constructor for GraphView
   GraphView(int width, int height, LinkedList<Country> countries)
   {
       this.width = width;
       this.height = height;
       
       this.plottedXmin = MARGIN;
       this. plottedXmax = width - MARGIN; 
       
       this.plottedYmin = height - MARGIN;
       this. plottedYmax = MARGIN; 
       
       font = new Font("Serif", Font.PLAIN, 9); 
       
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
      listOfCountryDataPoints = new LinkedList<PlottedDataSet>();
      listOfLegends = new LinkedList<Legend>();
      
       int counter_1 = 0;
       
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
           PlottedDataSet dataPoints = new PlottedDataSet();
           
           Legend legendKey = new Legend(current.getName(), dataPoints.getRandomColor());
           
//           dataPoints.setLabel(new JLabel());
//           System.out.println(dataPoints.getLabel());
           SubscriptionYear currentSubscription;
   
           Iterator<SubscriptionYear> iterator_s =  current.getSubscriptions().iterator();
                     
           int counter_2 = 0;
           
           while(iterator_s.hasNext())
           {                
               currentSubscription = iterator_s.next();
               
               originalX = currentSubscription.getYear();
               
               originalY = currentSubscription.getSubscriptions();              
               
               mappedX = map(originalX, dataMinX, dataMaxX, plottedXmin, (plottedXmax-MARGIN));
               
               mappedY = map(originalY, dataMinY, dataMaxY, (plottedYmin - 32), (plottedYmax + 32));
                              
               dataPoints.addDataPoints(originalX, originalY, mappedX, mappedY);
                              
               counter_2++;                           
           }          
           this.listOfCountryDataPoints.add(dataPoints);
           this.listOfLegends.add(legendKey);
       
           counter_1++;
      }
   }
   
   public LinkedList<PlottedDataSet> getListOfCountryDataPoints() { return this.listOfCountryDataPoints; }  
   
   public LinkedList<Legend> getListOfLegnds() { return this.listOfLegends; }  
   
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
       
       int firstX = this.plottedXmin;       
       int firstY = this.plottedYmax;     
       int lastX = this.plottedXmax;
       int lastY = this.plottedYmin - MARGIN;       
       
       // draws horizontal line
       g2d.drawLine(firstX, lastY, firstX, firstY);       
       g2d.drawString("Year", lastX,lastY);
     
       // draws vertical line
       g2d.drawLine(firstX, lastY, lastX, lastY);       
       g2d.drawString("Number of Subscriptions (per 100 people)",firstY, firstX);
      
       Iterator<PlottedDataSet> iterator_P =  this.listOfCountryDataPoints.iterator();
       
       PlottedDataSet currentDataPoints;
       
       while(iterator_P.hasNext())
       {
           currentDataPoints = iterator_P.next();
   
           for (int i = 0; i < currentDataPoints.getDataPoints().size(); i++)
           {          
               g2d.setColor(currentDataPoints.getRandomColor());
           
               g2d.fillOval((int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getX(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getY(), 
                   POINT_SIZE, POINT_SIZE);
           
               g2d.drawString(currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getLabel(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getX(), 
                   (int)currentDataPoints.getDataPoints().getNodeAtIndex(i).getData().getY());               
           }        
       }
       
//       Iterator<Legend> iterator_L =  this.listOfLegends.iterator();
//       
//       Legend currentKey;
//      
//       while(iterator_L.hasNext())
//       {
//           currentKey = iterator_L.next();
//           
//           for (int i = 0; i < this.listOfLegends.size(); i++)
//           {
//               g2d.drawRect(10, 13, i, i);
//               g2d.setColor(currentKey.getLegendColor());
//               g2d.fillRect(10, 13, i, i);              
//           }
//           
//       }
       
   }  
}

