import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.border.Border;

/**
 * One object of class LegendPanel provides a visual guide to viewing the list of 
 * random countries.
 * @author Shiva
 *
 */
public class LegendPanel extends JPanel implements Scrollable {

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
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		LinkedList<Legend>legends = GraphView.getListOfLegends();
		Iterator<Legend> l_iterator = legends.iterator();
		//Hard coded values based on 
		int x = 173;
		int y = 27;
		int increment = 20;
		Legend legendKey;
		while(l_iterator.hasNext()){
			legendKey = l_iterator.next();
			for(int counter = 0; counter < legends.size(); counter++){
				g2d.drawRect(x, y, 30, 30);
				g2d.setColor(legendKey.getLegendColor());
				y = y + increment;
			}
		}
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	


	
	

}


