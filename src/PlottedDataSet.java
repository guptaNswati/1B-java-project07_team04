import java.awt.Color;
import java.util.Random;

/**
 *  a class which holds a list of ColoredPoints
 * @author swati
 *
 */
public class PlottedDataSet
{
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
    }
 
    public void addDataPoints(double originalX, double originalY, double mappedX, double mappedY)
    {     
        ColoredPoint currentDataPoint = new ColoredPoint(this.randomColor.darker(), originalX,originalY, mappedX, mappedY);
        
        this.dataPoints.add(currentDataPoint);
        count++;
    }
    
    public Color getRandomColor() {
        return this.randomColor;
    }
    
    public LinkedList<ColoredPoint> getDataPoints() { return dataPoints; } 

}
