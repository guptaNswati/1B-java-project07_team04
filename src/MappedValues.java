/**
 * An object of class MappedValues stores the values of the x and y coordinates.
 * @author Shiva
 *
 */
public class MappedValues {
 
	private double originalX;
	private double originalY;
	private double xCoordinate;
	private double yCoordinate;
	
	
	public double getOriginalX() {
		return originalX;
	}

	public void setOriginalX(double originalX) {
		this.originalX = originalX;
	}

	public double getOriginalY() {
		return originalY;
	}

	public void setOriginalY(double originalY) {
		this.originalY = originalY;
	}


	public double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public MappedValues(double originalX, double originalY, double mappedX, double mappedY){
		setOriginalX(originalX);
		setOriginalY(originalY);
		setxCoordinate(xCoordinate);
		setyCoordinate(yCoordinate);
	}
}
