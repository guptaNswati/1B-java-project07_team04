
import java.awt.Color;
import java.awt.Point;
/**
 * @author Shiva
 */

public class ColoredPoint extends Point {
    private Color color;
    private double originalX;
    private double originalY;
    
//    public ColoredPoint(java.awt.Color color, double originalX, double originalY, double mappedX, double mappedY){
////        super(mappedX, mappedY);
//        // alternative
//        // super.x = mappedX;
//        // super.y = mappedY;
//        super.setLocation(mappedX, mappedY);
//        this.color = color;
//        this.originalX = originalX;
//        this.originalY = originalY;
//    }

    public ColoredPoint(Color randomColor, double originalX, double originalY, double mappedX, double mappedY)
    {
        // TODO Auto-generated constructor stub
        super.setLocation(mappedX, mappedY);
        this.color = randomColor;
        this.originalX = originalX;
        this.originalY = originalY;
    }

    public Color getColor() {
        return this.color;
    }

    public String getLabel(){
        return super.toString();
    }
}
