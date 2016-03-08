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

	private int height;
	private int width;
	private Color backgroundColor;

	/**
	 * Constructor that creates a panel with the given attributes
	 * @param width
	 * @param height
	 * @param color
	 */
	public LegendPanel(int width, int height, Color color){
		this.setSize(width, height);
		this.setBackground(color); 
		
	}
	
	/**
	 * Nested class.
	 * One object of type legend stores a label for the name of country
	 * and an object of type color for holding the color
	 */
	public class Legend{
		
		private String countryName;
		private JLabel countryLabel;
		private Color legendColor;
		
		
		public Legend(String countryName, Color legendColor){
			this.countryLabel = new JLabel(countryName);
			this.legendColor = legendColor;
		}


		public Color getLegendColor() {
			return legendColor;
		}


		public void setLegendColor(Color legendColor) {
			this.legendColor = legendColor;
		}
		
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		 
		
	
	}
	


	
	

}


