/**
 * \file    -Visualization.java
 * \author  -K. Zheng
 * \date    -20 Feb 2013
 * \see     -
 *
 *  \brief A class that extends Representation and allows
 *	the user to create a colour scheme.
 */
import java.util.ArrayList;
import java.awt.Color;
import org.jfree.chart.JFreeChart;

public class Visualization extends Representation {
	
	/**
	* Initializes member variables.
	*/
	public Visualization() {
		m_title = "";
		m_colours = new ColourManager();
	}
	
	/**
	* Returns the current chart
	*
	* \return JFreeChart chart 
	*
	* No side-effects.
	* Not referentially transparent as it relies on
	* the value of the member variable.
	*/
	public JFreeChart GetChart() {
		return m_chart;
	}
	
	/**
	* Set the current chart
	*
	* \param chart to set to
	*
	* \return True if chart has been set
	*
	* Has side-effects as it changes the chart
	* Referentially transparent.
	*/
	public boolean SetChart(JFreeChart inputChart) {
		m_chart = inputChart;
		return true;
	}
	
	/**
	* Returns the colour set
	*
	* \return ArrayList of colours, the colour scheme 
	*
	* No side-effects.
	* Not referentially transparent as it relies on
	* the value of the colour scheme.
	*/
	public ArrayList<Color> GetColourSet() {
		return m_colours.GetColourList();
	}
	
	/**
	* Method that sets the colourset to a new parameter value
	*
	* \param a colourmanager to be the new colourmanager
	*
	* \return True if new colour manager has been set
	*
	* Has side-effects, changes the colour manager.
	* Referentially transparent.
	*/
	public boolean SetColourSet(ColourManager newColours) {
		m_colours = newColours;
		return true;
	}
	
	/**
	* Set the current colour scheme
	*
	* \return True if colour scheme has been set
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean SetColourSet() {
		m_colours = new ColourManager();
		m_colours.SetColourList();
		return true;
	}

	/**
	* Returns the title
	*
	* \return The title
	*
	* No side-effects.
	* Not referentially transparent as does not
	* always return the same value for the title.
	*/
	public String GetTitle() {
		return m_title;
	}
	
	/**
	* Set the title
	*
	* \param new title
	*
	* \return True if title has been changed
	*
	* Has side-effects as it changes the title
	* Referentially transparent.
	*/
	public boolean SetTitle(String title) {
		m_title = title;
		return true;
	}
	

	/* colour manager, creates colour schemes */
	private ColourManager m_colours;  
	/* the chart */
	private JFreeChart m_chart;
	/* title of the chart */
	private String m_title; 
}