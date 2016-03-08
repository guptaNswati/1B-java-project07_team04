import java.awt.Color;

import javax.swing.JLabel;

/**
 * One object of type legend stores a label for the name of country and an
 * object of type color for holding the color
 * 
 * @Shiva
 */
public class Legend {

	private String countryName;
	private JLabel countryLabel;
	private Color legendColor;

	public Legend(String countryName, Color legendColor) {
		this.countryLabel = new JLabel(countryName);
		this.legendColor = legendColor;
	}

	public Color getLegendColor() {
		return legendColor;
	}

	public void setLegendColor(Color legendColor) {
		this.legendColor = legendColor;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}