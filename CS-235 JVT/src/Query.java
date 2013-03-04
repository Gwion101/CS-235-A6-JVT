/**
 * \file    -Query.java
 * \author  -D. Sherratt
 * \date    -20 Feb 2013
 * \see     -JAVA for Everyone, Cay Horstmann, Page 127, Figure 1
 *
 *  \brief A class that works on an ArrayList<DataTypeManager>,
 *	 and allows the user to select certain columns and rows.
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Query {

	/**
	 * Initializes the dataset by assigning the dataset to the parameter value.
	 */
	public Query(ArrayList<DataTypeManager> input) {
		s_data = input;
	}

	/**
	 * Gets data stored in the query
	 * 
	 * \return dataset DataTypeManager
	 * 
	 * No side-effects. Not referentially transparent, the dataset is not always
	 * the same.
	 */
	public ArrayList<DataTypeManager> GetData() {
		return s_data;
	}

	/**
	 * Sets data of query to new data parsed through parameter
	 * 
	 * \param new dataset DataTypeManager
	 * 
	 * \return true if new dataset has been set
	 * 
	 * Has side-effects, changes data. Referentially transparent.
	 */
	public boolean SetData(ArrayList<DataTypeManager> newData) {
		s_data = newData;
		return true;
	}

	/**
	 * Gets the title stored in the query class
	 * 
	 * \return query title
	 * 
	 * No side-effects. Not referentially transparent, the title is not always
	 * the same.
	 */
	public String GetTitle() {
		return s_title;
	}

	/**
	 * Set title of query to the input
	 * 
	 * \param the new title
	 * 
	 * \return true if the new title has been set
	 * 
	 * Has side-effect, changes the stored title. Referentially transparent.
	 */
	public boolean SetTitle(String newTitle) {
		s_title = newTitle;
		return true;
	}

	/**
	 * A method that allows the user to select what columns they want the data
	 * to contain. Disregarding the remaining rows.
	 * 
	 * \return the new data
	 * 
	 * Has side-effects, changes the data. Not referentially transparent as it
	 * return is based on the the users input.
	 */
	public ArrayList<DataTypeManager> SelectColumns() {
		boolean continueSelecting = true;
		ArrayList<DataTypeManager> newList = new ArrayList<DataTypeManager>();
		boolean test = false;
		while (continueSelecting) {

			Object[] possibleValues = new Object[s_data.size()];
			/*
			 * Fill up the possible values with the headers of the
			 * DataTypeManagers.
			 */
			/* Terminates as reaches end of list of DataTypeManagers. */
			for (int i = 0; i < s_data.size(); i++) {
				possibleValues[i] = s_data.get(i).GetHeader();
			}

			Object selectedValue = JOptionPane.showInputDialog(null,
					"Choose an attribute you want to select", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
					possibleValues[0]);
			/*
			 * Goes through list of headers, checking if it is the selected
			 * value
			 */
			for (int i = 0; i < s_data.size(); i++) {
				/*
				 * Checks whether the selected value is the header of the
				 * DataTypeManager
				 */
				if (selectedValue == (Object) s_data.get(i).GetHeader()) {
					/*
					 * Adds header to new list, and removes header from old
					 * list.
					 */
					newList.add(s_data.get(i));
					s_data.remove(i);
					if (test) {
						System.out
								.println("SelectColumns(): Line 116: Heading"
										+ " removed from old list and added to new list.");
					}
				}
			}

			continueSelecting = continueAdding(
					"Continue selecting attributes?",
					" Select another attribute");

			/* If datasize is empty a.k.a. no headers chosen */
			if (s_data.size() == 0) {
				continueSelecting = false;
			}
		}

		final int EMPTY = 0;
		final int ONECOLUMN = 1;
		final int ONLYATTRIBUTE = 0;

		/* If the list is empty, returns the old data. */
		if (newList.size() == EMPTY) {
			if (test) {
				System.out.println("SelectColumns(): Line 136: No attributes"
						+ " selected, use old list");
			}
			return s_data;
			/*
			 * If there is one column, changes so that rows are columns and
			 * columns are rows
			 */
			/* Because the chart processes are based like this. */
		} else if (newList.size() == ONECOLUMN) {
			if (test) {
				System.out.println("SelectColumns(): Line 143: One column "
						+ "selected");
			}
			ArrayList<DataTypeManager> newColumnList = new ArrayList<DataTypeManager>();
			for (int i = 0; i < newList.get(ONLYATTRIBUTE).ReturnSize(); i++) {
				DataTypeManager newElement = new DataTypeManager("Row "
						+ (i + 1));
				if (i < 50) { // Adds limit
					newElement.AddObj(newList.get(ONLYATTRIBUTE).GetElement(i));
					newColumnList.add(newElement);
				}
			}
			s_title = newColumnList.get(0).GetHeader();
			s_data = newColumnList;
			return newColumnList;
		}
		s_data = newList;
		return newList;
	}

	/**
	 * A method that allows the user to select what rows they want the data to
	 * contain. Disregarding the remaining rows.
	 * 
	 * \return the new data
	 * 
	 * No side-effects. Not referentially transparent as it return is based on
	 * the the users input.
	 */
	public ArrayList<DataTypeManager> SelectRows() {
		boolean test = false;
		boolean continueSelecting = true;
		ArrayList<DataTypeManager> newList = new ArrayList<DataTypeManager>();
		
		int length = 0;
		/* Creates a copy of old data with same header but no data */
		/* calculates length of data */
		for (int i = 0; i < s_data.size(); i++) {	
			
			DataTypeManager column = new DataTypeManager();
			column.SetHeader(s_data.get(i).GetHeader());
			newList.add(column);
			if (test) {
				System.out.println("SelectRows(): Line 186: New " +
						"DataTypeManager created, with same header but no data");
			}
			/* Sets length to new value if size is bigger than length */
			if (s_data.get(i).ReturnSize() > length) {
				length = s_data.get(i).ReturnSize();
			}
		}
		
		final int ONEATTRIBUTE = 1;
		/* Cannot select rows if only one attribute is chosen */
		if (length == ONEATTRIBUTE) {
			return s_data;
		}
		
		while (continueSelecting) {
			
			Object[] possibleValues = new Object[length];
			/* Creates list of all rows */
			for (int i = 0; i < length; i++) {
				possibleValues[i] = "Row " + (i + 1);
			}
			if (test) {
				System.out.println("SelectRows(): Line 208: Row options added "
			+"to possible values");
			}
			
			Object selectedValue = JOptionPane.showInputDialog(null,
			"Select a row you want", "Input",
			JOptionPane.INFORMATION_MESSAGE, null,
			possibleValues, possibleValues[0]);
			/* Loop that checks if selected row is a row in the DataTypeManager 
				*/
			for (int i = 0; i < length; i++) {
				/* If row is selected is certain row in DataTypeManager */
				if (selectedValue == possibleValues[i]) {
					/* Creates new row in new data */
					for (int j = 0;  j < s_data.size(); j++) {
						newList.get(j).AddObj(s_data.get(j).GetElement(i));
					}
				}
			}
			continueSelecting = continueAdding("Continue selection rows",
				"Select another row");
			
		}
		
		final int EMPTY = 0;
		
		/* If empty, then return old data. */
		if (newList.size() == EMPTY) {
			return s_data;
		} 
		
		return newList;
	}

	/**
	 * A method that allows the user to select a boolean representing whether
	 * they want to continute adding.
	 * 
	 * \param display text asking user a question \param header of pop up box
	 * 
	 * \return true if input YES else return false
	 * 
	 * No side-effects. Not referentially transparent as it returns the users
	 * input.
	 */
	private boolean continueAdding(String question, String header) {
		final int YES = 0;

		int n = JOptionPane.showConfirmDialog(null, question, header,
				JOptionPane.YES_NO_OPTION);
		if (n == YES) {
			return true;
		} else {
			return false;
		}
	}

	/** variable storing title of query */
	private static String s_title;
	/** variable storing current data */
	private static ArrayList<DataTypeManager> s_data;

}