/**
 * \file    -Representation.java
 * \author  -X. Zhao
 * \date    -20 Feb 2013
 * \see     -
 *
 *  \brief A class that accepts an arraylist of 
 *	type DataTypeManager.
 */
import java.util.ArrayList;

public class Representation {
	/**
	* Constructor that initializes the dataset.
	*/
	public Representation() {
		m_dataset = new ArrayList<DataTypeManager>();
	}
	
	/**
	* Returns the current dataset
	*
	* \return ArrayList of type DataTypeManager 
	*
	* No side-effects.
	* Not referentially transparent as the dataset 
	* is not always the same.
	*/
	public ArrayList<DataTypeManager> GetDataset() {
		return m_dataset;
	}
	
	/**
	* Set the current dataset
	*
	* \param ArrayList of type DataTypeManaget to be new list
	*
	* \return boolean True if the list has been changed
	*
	* Has side-effects, it changes the list.
	* Referentially transparent.
	*/
	public boolean SetDataset(ArrayList<DataTypeManager> data) {
		m_dataset = data;
		return true;
	}
	
	/* Current dataset that needs to be represented. */
	private ArrayList<DataTypeManager> m_dataset;
	
}