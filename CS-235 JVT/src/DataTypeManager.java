/**
 * \file    -DataTypeManager.java
 * \author  -J. Reynolds
 * \date    -28 Feb' 2013
 * \see     -Java for everyone, Cay Horstmann, Chapter 7.5 
 *				Constructors, Page 284 - 285
 *
 *  \brief Class that stores information and is used to pass
 *  the information around to other classes.
 */
import java.util.ArrayList;

public class DataTypeManager {
	
	/**
	*	Initialize with header of column
	*
	* 	\param Header of column
	*/
	public DataTypeManager(String inputHeader) {
		m_header = inputHeader;
		m_columnList = new ArrayList<Object>();
	}

	/**
	*	Initialize with blank header
	*/	
	public DataTypeManager() {
		m_header = "";
		m_columnList = new ArrayList<Object>();
	}
	
	/**
	*	Gets an element at an index
	*
	*	\param index of row to look at
	*
	*	\return Object at index
	*
	*	No side-effects.
	* 	Not referentially transparent as it reads from 
	*	an array which could contain differnet things
	*	at different times.
	*/
	public Object GetElement (int index) {
		/* gets element if index is in range */
		if (index >= ReturnSize()) {
		/* returns nothing if index is not in range */
			return null;
		} else {
		/* returns element if index is in range */
			return m_columnList.get(index);
		}
	}
	
	/**
	*	Sets an element at an index to an object
	*
	*	\param index of row to look at
	*	\param Object wanted to store at index
	*
	*	\return True if element is set successfully.
	*
	*	Has side-effects, changes a value in the array.
	* 	Not referentially transparent as it reads from 
	*	an array and the size may vary.
	*/
	public Boolean SetElement (int index, Object i) {
		/* sets element if index is in range */
		if (index >= ReturnSize()) {
		/* returns false if index is out of range */
			return false;
		} else {
		/* sets element in list at index to given object */
			m_columnList.set(index, i);
			return true;
		}
	}
	
	/**
	*	Gets the header of the column
	*
	*	\return the current header
	*
	*	No side-effects.
	* 	Not referentially transparent as current
	*	header can be changed.
	*/
	public String GetHeader() {
		return m_header;
	}
	
	/**
	*	Sets the header of the column
	*
	*	\param the new header
	*
	*	\return True if header is set successfully.
	*
	*	Has side-effects as it changes the header.
	* 	Referentially transparent.
	*/
	public boolean SetHeader(String input) {
		m_header = input;
		return true;
	}
	
	/**
	*	gets the current list
	*	
	*	\return the current list in it's current state
	*
	*	No side-effects.
	*	Not referentially transparent as the current list
	*	could change.
	*/
	public ArrayList<Object> GetList() {
		return m_columnList;
	}
	
	/**
	*	sets the current list
	*
	*	\param the new list
	*	
	*	\return true if the list is updated
	*
	*	Has side-effects, changes the list to input
	*	Referentially transparent.
	*/
	public boolean SetList(ArrayList<Object> newList) {
		m_columnList = newList;
		return true;
	}
	
	/**
	*	Adds object to end of list
	*
	*	\param object you want to add
	*
	*	\return True if element is added successfully.
	*
	*	Has side-effects, adds object to list.
	* 	Referentially transparent.
	*/
	public boolean AddObj (Object i) {
		m_columnList.add(i);
		return true;
	}
	
	/**
	*	Removes first intance of an object from list
	*
	*	\param object wanted to be removed from list.
	*
	*	\return True if object is removed successfully.
	*
	*	Has side-effects as it changes the list.
	* 	Not referentially transparent the list may or
	*	may not contain the object.
	*/
	public boolean RemoveObj (Object i) {
		return m_columnList.remove(i); 
	}
	
	/**
	*	returns size of list
	*
	*	\return size of the list
	*
	*	No side-effects.
	* 	Not referentially transparent as size of the
	*	list may vary.
	*/
	public int ReturnSize() {
		return m_columnList.size();
	}
	
	private ArrayList<Object> m_columnList; /* List of elements */
	private String m_header; /* header for elements */
	
}