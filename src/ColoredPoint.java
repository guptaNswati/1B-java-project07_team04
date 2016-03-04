
import java.awt.Color;
import java.awt.Point;
/**
 * @author Shiva
 */

public class ColoredPoint extends Point {
    private Color color;
    private double originalX;
    private double originalY;
    
    public ColoredPoint(Color color, double originalX, double originalY, int mappedX, int mappedY){
        super(mappedX, mappedY);
        this.color = color;
        this.originalX = originalX;
        this.originalY = originalY;
    }

    public Color getColor() {
        return color;
    }

    public String getLabel(){
        return super.toString();
    }
}
