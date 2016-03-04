
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphView extends JPanel
{
   private int width;
   private int height;
   private Font font;
   
   // constructor for GraphView
   GraphView(int width, int height, LinkedList<Country> countries)
   {
       this.width = width;
       this.height = height;
       font = new Font("Serif", Font.PLAIN, 11);      
   }
   
   public static final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax)
   {
       return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax- dataMin));
   }
   
   /**
    * @Todo complete
    */
   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       g.drawLine(20, 20, 300, 20);
   }

}
