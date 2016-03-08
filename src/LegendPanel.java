import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * One object of class LegendPanel provides a visual guide to viewing the list of 
 * random countries.
 * @author Shiva
 *
 */
public class LegendPanel extends JPanel {
	
	private int POINT_SIZE = 10;
	private int height;
	private int width;
	private Color backgroundColor;

	/**
	 * Constructor that creates a panel with the given attributes
	 * @param width
	 * @param height
	 * @param color
	 * @param border
	 */
	public LegendPanel(int width, int height, Color color){
		this.setSize(width, height);
		this.setBackground(color); 
		
	}
	
	/**
	 * Nested class.
	 * One object of type Legend holds a label for the country name 
	 * and an object of type ColoredPoint for the key.
	 * @author Shivaa
	 *
	 */
	public class Legend {
		JLabel countryLabel;
		Graphics g;
		
		public Legend(JLabel label, Graphics g){
			setCountryLabel(countryLabel);
			
		}
		
		public JLabel getCountryLabel() {
			return countryLabel;
		}


		public void setCountryLabel(JLabel countryLabel) {
			this.countryLabel = countryLabel;
		}
			
		}
		
	public void paintComponent(Graphics g){
		
	
	}
	


	
	

}


