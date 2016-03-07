
import java.awt.Component;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ContainerEvent;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
       
       this.plottedYmin = height - MARGIN;
       this. plottedYmax = MARGIN; 
       
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
      listOfCountryDataPoints = new LinkedList<PlottedDataSet>();
     
       int count = 0;
       
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
           
           System.out.println(count);
           PlottedDataSet dataPoints = new PlottedDataSet();
           
           SubscriptionYear currentSubscription;
   
           Iterator<SubscriptionYear> iterator_s =  current.getSubscriptions().iterator();
           
           
           int count_A = 0;
           while(iterator_s.hasNext())
           { 
               
               currentSubscription = iterator_s.next();
               
               originalX = currentSubscription.getYear();
               
               originalY = currentSubscription.getSubscriptions();              
               
               mappedX = map(originalX, dataMinX, dataMaxX, plottedXmin, plottedXmax);
               
               mappedY = map(originalY, dataMinY, dataMaxY, plottedYmin, plottedYmax);
               
               
               dataPoints.addDataPoints(originalX, originalY, mappedX, mappedY);
               
               
               System.out.println(dataPoints.getDataPoints().getNodeAtIndex(count_A).getData().toString());  
               count_A++;
                            
           } 
         
           this.listOfCountryDataPoints.add(dataPoints);
           count++;
      }
       System.out.println(this.listOfCountryDataPoints.size());

   }
   
   public LinkedList<PlottedDataSet> getListOfCountryDataPoints() { return this.listOfCountryDataPoints; }  
   
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
       
       
      
       
//       //Paint background if we're opaque.
//       if (isOpaque()) 
//       {
//           g2d.setColor(getBackground());
//           g2d.fillRect(0, 0, getWidth(), getHeight());
//       }
//       
//      //If user has chosen a point, paint a small dot on top.
//       for(int j = 0; j < this.listOfCountryDataPoints.size(); j++)
//       {
//           LinkedList<ColoredPoint> currentDataPoints = listOfCountryDataPoints.getNodeAtIndex(j).getData().getDataPoints();
//           
//           Iterator<ColoredPoint> iterator_C =  currentDataPoints.iterator();
//           
//           while(iterator_C.hasNext())
//           {
//               ColoredPoint currentPoint_1 = iterator_C.next();
//               ColoredPoint currentPoint_2 = iterator_C.next();
//               
//               g2d.drawLine((int)currentPoint_1.getX(), (int)currentPoint_1.getY(), (int)currentPoint_2.getX(), (int)currentPoint_2.getY());
//         
////           for (int i = 0; i < currentDataPoints.size(); i++)
////           {
////               ColoredPoint currentPoint = currentDataPoints.getNodeAtIndex(i).getData();
////               
////               if (currentPoint != null)                   
////               {           
////                   g2d.drawLine(currentPoint.getX(), currentPoint.getY(), x2, y2);
////                   g2d.setColor(currentPoint.getColor());
////                   g2d.fillOval((int)currentPoint.getX(), (int)currentPoint.getY(), POINT_SIZE, POINT_SIZE);                          
////                   g2d.drawString(currentPoint.getLabel(), (int)currentPoint.getX(), (int)currentPoint.getY());
//               }
////               Container someContainer = new Container();
////               someContainer.add(currentDataPoints, i);
//               
//           }         
//           
//       }
//    
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


