
import java.awt.Color;
import java.awt.Point;
/**
 * @author Shiva
 */

public class ColoredPoint extends Point {
    private Color color;
    private double originalX;
    private double originalY;

    public ColoredPoint(Color randomColor, double originalX, double originalY, double mappedX, double mappedY)
    {
        // TODO Auto-generated constructor stub
//        super.setLocation(mappedX, mappedY);
        super.x = (int) mappedX;
       super.y = (int) mappedY;
        this.color = randomColor;
        this.originalX = originalX;
        this.originalY = originalY;
    }

    public Color getColor() {
        return this.color;
    }

    public String getLabel(){
        // formating y values i.e subscription data to two decimal places
        return "(" + (int)originalX + "," + String.format("%.2f", originalY)  + ")";
    }
}
