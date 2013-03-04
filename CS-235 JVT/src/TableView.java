/**
 * \file    -TableView.java
 * \author  -X. Zhao
 * \date    -28 Feb 2013
 * \see     -JAVA for everyone, Cay Horstmann, Page 139, Figure 4.
 *
 *  \brief  -A class that allows the user to view
 *	their data in the form of a Table.
 *
 */

import javax.swing.JFrame;
import java.awt.*;
import java.awt.Container;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

public class TableView extends Representation{

	/**
	* Constructor that takes in DataTypeManager as an arraylist
	* and converts it to a dataset the JFreeChart can understand.
	*/
	public TableView(ArrayList<DataTypeManager> data) {
		
		super.SetDataset(data);
		
		m_header = new Object[GetDataset().size()];
		int length = 0;
		/*
		*	Loop that calculates the longest column
		*	and makes note of the length of the longest.
		*	Terminates when it searches every column.
		*/
		for (int i = 0; i < GetDataset().size(); i++) {
			m_header[i] = GetDataset().get(i).GetHeader();
			if (GetDataset().get(i).ReturnSize() > length) {
				length = GetDataset().get(i).ReturnSize();
			}
		}
		
		m_rows = new Object[length][super.GetDataset().size()];
		/*
		*	Loop and nested loop that initialize the dataset
		* 	with the given data.
		*/
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < GetDataset().size(); j++) {
				m_rows[i][j] = GetDataset().get(j).GetElement(i);
			}
		}
		
	}
	/**
	 * get rows of tableview
	 *
	 * \return data held in table
	 *
	 * No side-effects.
	 * Not referentially transparent as does not
	 * always return same result.
	 */
	public Object[][] GetRows(){
		return m_rows;
	} 
	
	/**
	 * set rows of tableview
	 *
	 * \param new data of tableview
	 *
	 * \return True if new data has been set
	 *
	 * Has side-effects as it sets new row data.
	 * Referentially transparent.
	 */
	public boolean SetRows(Object[][] RowData){
		m_rows = RowData;
		return true;
	}

	/**
	 * get header of tableview
	 *
	 * \return array of names of headers
	 *
	 * No side-effects.
	 * Not referentially transparent as does not
	 * always return the same headers.
	 */
	public Object[] GetHeader(){
		return m_header;
	}

	/**
	 * set header of tableview
	 *
	 * \param headerData of tableview
	 *
	 * \return True if new header has been set
	 *
	 * Has side-effects as it sets a new header.
	 * Referentially transparent.
	 */
	public boolean SetHeader(Object HeaderData[]){
		m_header = HeaderData;
		return true;
	}

	
	/**
	* Method that displays the Table View on a JFrame 
	*
	* \param width of displayed table
	* \param height of displayed table
	*
	* \return boolean True if the tableView has been added
	*
	* Has side-effects as it creates a new table.
	* Referentially transparent.
	*/
	public boolean Show(int width, int height) {
			
		m_table = new JTable(m_rows, m_header);
 		JScrollPane scrollPane = new JScrollPane(m_table);
 		JFrame frame = new JFrame();
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.add(scrollPane, BorderLayout.CENTER);
 		frame.setSize(width, height);
 		frame.setVisible(true); 
		
		return true;
	}
	/** variable storing current the structure of the table */
	private JTable m_table;  
	/** variable storing current the data of the rows */
	private Object m_rows[][];
	/** variable storing current the data of the header */
	private Object m_header[];
}