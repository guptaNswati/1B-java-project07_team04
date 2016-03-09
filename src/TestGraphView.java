/**
 *  Tests the GraphView class by creating an object of type GraphView and adding components to it.
 *  Creates one container of type JFrame and adds an object of type GraphView.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class TestGraphView 
{
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 600;

	/**
	 * Builds a list of countries to debug.
	 */
	private void debugListOfCountries(Country [] allCountries)
	{
        // TODO: The purpose is to help you debug
        // Note: The implementation of method is optional.
    }

    /**
     * Creates a generic linked list. Then based on the user's input,
     * adds a random number of countries to the list.
     * @param allCountries      An array of Country objects
     */
	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		JFrame frame = new JFrame("Cellular Graph");

		String userInput = (String)JOptionPane.showInputDialog(
				frame,
				"Enter the number of countries to graph:\n",
				"Input",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"5");

		int requestedSize = Integer.parseInt(userInput);

		// Build the list out of a random selection of countries.
		Random random = new Random();
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}

		return selectedCountries;
	}
	
	
    /**
     * Initializes the GUI with two JPanels and populates them.
     * One panel draws the data points, the second draws the legend.
     * @param selectedCountries      A randomly selected list of countries.
     *
     * Note: You may add as many panels as you see fit.
     */
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		// TO COMPLETE: Select a layout 
		FlowLayout layout = new FlowLayout();
		frame.setLayout(layout);	

		// TO COMPLETE: Specify the size of your graph view based on your other panels
		int graph_panel_size = 600;

		// TO COMPLETE: add the GraphView object to your layout
		GraphView myPlots = new GraphView(graph_panel_size, FRAME_HEIGHT, selectedCountries);	
		myPlots.setPreferredSize(new Dimension(592, FRAME_HEIGHT)); //400
		JLabel graphLabel = new JLabel("Graph");
		myPlots.add(graphLabel);
		frame.add(myPlots);

		 // Draw the legend
        // TO COMPLETE: add the legend panel to your frame
       LegendPanel rightPanel = new LegendPanel(200, 600, Color.LIGHT_GRAY, myPlots);
       rightPanel.setLayout(new FlowLayout ());
       JLabel legendLabel = new JLabel("Legend Key");
       rightPanel.add(legendLabel);

             
       JScrollPane legendScroller = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       legendScroller.setPreferredSize(new Dimension(148, FRAME_HEIGHT-60));
       rightPanel.setAutoscrolls(true);
      
       frame.add(legendScroller);
       legendScroller.setVisible(true);
       
 
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);		
	}
	
    /**
     * Uses a CSV to parse a CSV file.
     * Adds the data for each country to an array of Country objects.
     * Adds a random selection of countries to a generic LinkedList object.
     * Draws the list of countries to a JFrame.
     */
	public static void main(String[] args) 
	{		
		// Create and set objects of type Country 
		//
		final String FILENAME = "resources/cellular.csv";	// Directory path for Mac OS X
		//final String FILENAME = "resources\\cellular.csv";	// Directory path for Windows OS (i.e. Operating System)
		final int NUM_COUNTRIES_TO_TEST = 3;			// Note: Include test cases in addition to 3


		// Parse the CSV data file
		//
		CSVReader parser = new CSVReader(FILENAME);

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Create and set objects of type Country 
		//
		Country [] countries;
		countries = new Country[NUM_COUNTRIES_TO_TEST];

		Country current;
		countries = new Country[countryNames.length];

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			current = new Country(countryNames[countryIndex]);	

			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}

		TestGraphView application = new TestGraphView();

		// Note: Initially, to test your output you may hard code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);

		//application.initializeGUI(countries);
		LinkedList<Country> listOfCountries = application.buildCountryList(countries);
		application.initializeGui(listOfCountries);
        
        // flush the error stream
        System.err.flush();
        
        System.out.println("\nDone with TestGraphView.");
	}
}