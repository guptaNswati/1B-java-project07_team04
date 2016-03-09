import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;

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
    private GraphView gv;
    
    /**
     * Constructor that creates a panel with the given attributes
     * @param width
     * @param height
     * @param color
     */
    public LegendPanel(int width, int height, Color color, GraphView gv){
        this.setSize(width, height);
        this.setBackground(color); 
        this.gv = gv;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        LinkedList<Legend>legends = gv.getListOfLegends();
        Iterator<Legend> l_iterator = legends.iterator();
        //Hard coded values based on 
        int x = 10; // x =650
        int y = 30; // y = 30
        int increment = 50; // i = 30
        Legend legendKey;
       
        while(l_iterator.hasNext()){
            legendKey = l_iterator.next();
            for(int counter = 0; counter < legends.size(); counter++){
                g2d.drawRect(x, y + increment, 30, 20);               
                g2d.setColor(legendKey.getLegendColor());
                g2d.fillRect(x, y + increment, 30, 20);               
                g2d.setColor(Color.BLACK);
                g2d.drawString(legendKey.getCountryName(), x + 32, y + increment + 15);  // break the name in parts 
                }
            y = y + increment;
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        // TODO Auto-generated method stub
        return this.getSize();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        return this.getWidth();
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        return this.getWidth();
    }
    


    
    

}