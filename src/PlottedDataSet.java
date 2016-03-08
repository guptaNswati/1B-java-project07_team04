import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

/**
 *  a class which holds a list of ColoredPoints
 * @author swati
 *
 */
public class PlottedDataSet
{
    private JLabel label; // add the name of country
    private LinkedList<ColoredPoint> dataPoints;
    Color randomColor;
    
    private int count = 0;
    
    public PlottedDataSet()
    {
        this.dataPoints = new LinkedList<ColoredPoint>();
        
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);
        int purple = randomGenerator.nextInt(256);        
        
        this.randomColor  = new Color(red,green,blue, purple);   // darker hues
        
        this.label = new JLabel();
    }
    
//    public Color randomColor()
//    {
//        Random randomGenerator = new Random();
//        int red = randomGenerator.nextInt(256);
//        int green = randomGenerator.nextInt(256);
//        int blue = randomGenerator.nextInt(256);
//        int purple = randomGenerator.nextInt(256);
//        
//        Color randomColor = new Color(red,green,blue, purple);
//        
//        return randomColor;                 
//    }
    
    public void addDataPoints(double originalX, double originalY, double mappedX, double mappedY)
    {     
        ColoredPoint currentDataPoint = new ColoredPoint(this.randomColor, originalX,originalY, mappedX, mappedY);
        
        this.dataPoints.add(currentDataPoint);
        count++;
    }
    
    public void setLabel(JLabel labelName)
    {
        this.label = labelName;        
        
    }
    
    public JLabel getLabel() { return this.label; }
    
    public Color getRandomColor() {
        return this.randomColor;
    }
    
    public LinkedList<ColoredPoint> getDataPoints() { return dataPoints; } 

}
