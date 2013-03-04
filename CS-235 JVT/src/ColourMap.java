/**
 * \file    -ColourMap.java
 * \author  -D. Sherratt
 * \date    -18 Feb 2013
 * \see     -Program Development in Java, Barbara Liskov, Page 84, Figure 5.4
 *
 *  \brief A class allows a user to select a colour from
 * a colour map and stores the current selected colour.
 *
 */

import java.awt.Color; /* for object Color */
import javax.swing.JColorChooser; /* for the colour chooser */
public class ColourMap {
	
	/**
	* No-argument constructor. 
	* Sets initial colour to white.
	*/
	public ColourMap() {
		m_colour = ( Color.WHITE );
	}
	
	/**
	* Method that returns current colour value
	*
	* \return current colour value
	*
	* No side-effects.
	* Not referentially transparent as current 
	* colour can be different colours.
	*/
	public Color GetColour() {
		return m_colour;
	}
	
	/**
	* Method that allows user to change current colour
	*
	* \return boolean True if a colour is chosen otherwise False
	*
	* Has side-effects, it changes the current colour to a new colour
	* Referentially transparent.
	*/
	public boolean SetColour() {
		m_colour = JColorChooser.showDialog(null, "Pick your colour", m_colour);
		return true;
	}


	/* variable storing current colour value */
	private Color m_colour;
}