
public class MappedValues {
 
	private double xCoordinate;
	private double yCoordinate;
	
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

	public MappedValues(){
		setxCoordinate(xCoordinate);
		setyCoordinate(yCoordinate);
	}
}
