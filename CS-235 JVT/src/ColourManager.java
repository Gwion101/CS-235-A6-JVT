/**
 * \file    -ColourManager.java
 * \author  -D. Morgan
 * \date    -18 Feb 2013
 * \see     - Deitel P.J., Deitel H.M. - Java. How to Program, 9th Edition – 
 *				Chapter 7.7, Arrays and Array Lists 259-264
 *
 *  \brief A class allows a user to select a colour from
 * a colour map and apply the colour to a visulization.
 *
 */
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ColourManager {
	/**
	* No-argument constructor. 
	*
	* Sets the first set of colours to the default selection
	*/
	public ColourManager() {
		m_map = new ColourMap();
		m_colorList = new ArrayList<Color>();
		setDefault();
	}
	
	/**
	* Method that returns current colour list
	*
	* \return current colour list
	*
	*	No side-effects.
	*	Not refertially transparent as does not always
	*	return the same list.
	*/
	public ArrayList<Color> GetColourList(){
		return m_colorList;
	}
	
	/**
	* Method that request the user to enter more colours
	*
	* \return boolean true if they have finished adding
	*
	* Has side-effects as it changes the state of 
	* the list.
	* Referentially Transparent.
	*/
	public boolean SetColourList(){
		m_colorList = new ArrayList<Color>(); //Empty/Reset the list
		boolean continueAdding = true; /* true if user wants to add another 
											colour */
		final int YES = 0; /* used when reading user input */	
		/*	Keeps adding colours to colour scheme 
			adds a colour to the list of colours
			terminates when user doesn't wish to continute */
		while (continueAdding) {
				m_map.SetColour();
				m_colorList.add(m_map.GetColour());
				int n = JOptionPane.showConfirmDialog(
					null,
					"Continue adding colours to colour scheme?",
					"Colour Question",
					JOptionPane.YES_NO_OPTION);
				/* checks whether user wants to continue adding colours */
				if (n == YES) {
				/* lets user continue adding */
					continueAdding = true;
				} else {
				/* stops user from adding any more */
					continueAdding = false;
				}			
			}			
		return true;
	}
	
	/**
	* Method that returns current size of the colour list
	*
	* \return current size of colour list
	*
	* No Side-effects.
	* Not referentially trasnparent as the size of the
	* colour list may change.
	*/
	public int Size() {
		return m_colorList.size();
	}	
	
	/**
	* Method that sets the default colours
	*
	* \return boolean true if they have been set
	*
	* Has side-effects as adds colours to list
	* Referentially transparent.
	*/
	private boolean setDefault() {
		m_colorList = new ArrayList<Color>();
		m_colorList.add(Color.red);
		m_colorList.add(Color.yellow);
		m_colorList.add(Color.green);
		m_colorList.add(Color.blue);
		m_colorList.add(Color.black);		
		return true;		
	}
	
	private ColourMap m_map; /* colour map */
	private ArrayList<Color> m_colorList; /* List of current colour for colour 
											scheme */
}
