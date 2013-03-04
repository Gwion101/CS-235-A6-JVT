/**
 * \file    -FileChooser.java
 * \author  -P. Bird
 * \date    -02 Mar 2013
 * \see     -Deitel P.J., Deitel H.M. - Java. How to Program, 9th Edition 
 *				chapter 7 all.
 *
 *  \brief A class allows a user to take in a cvs file, check it and 
 *	split it into individual data to be passed to classes.
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {	
	
	/** 
	*	a file selector method to allow the user to browse and get their file
	*	If no file is selected, it returns null.
	*
	* 	\return Selected File
	*
	*	No Side-effects.
	*	Not referentially transparent, as this method does not
	*	always return the same file.
	*/
    public File GetFile(){
        boolean test = false; /* local testing variable */
		File f = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
        "CSV file", "CSV"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (test) {
				System.out.println("GetFile(): Line 40: File chosen");
			}
			f = fileChooser.getSelectedFile();
        } else {
			if (test) {
				System.out.println("GetFile(): Line 42: File not chosen," +
						"return null.");
			}
			f = null;
		}
        return f;
    }
    /** The method for file chooser, takes in the cvs file
	* Reads the csv file using a delimiter  and stores it in a 2d array.

	* Then using the 2d array it passes the data in a column to a data type 
	* manager class
	* the data type managers are then stored in a array 
    * the method returns an array so to the class that calls it.
	*
	*	\return the data stored in DataTypeManagers
	*
	* No side-effects.
	* Not referentially transparent, returns different data
	* depending on the file chosen.
    */
    public ArrayList<DataTypeManager> SelectFile() throws IOException{
    	boolean test = false; /* local test variable */
        File inputFile = GetFile();
		if (inputFile == null) {
			if (test) {
				System.out.println("SelectFile(): Line 68: No file chosen. ");
			}
			return null;
		}
        //creates scanner to take in file
        Scanner fileIn = new Scanner(inputFile);
        
        //variables to store the number of columns and rows
        int columnNum=0;
        int rowNum=0;
       // string to take in the headings
        String firstline = fileIn.nextLine();
        // creates a scanner for the first line
        Scanner j = new Scanner(firstline);
      // gets number of columns
        while (j.hasNext()){
		j.useDelimiter(",");
        	columnNum++;
            j.next();
        }
		if (test) {
			System.out.println("SelectFile(): Line 89: Number of columns = " + 
				columnNum);
		}
        // gets number of rows
        while (fileIn.hasNext()){
        	rowNum++;
            fileIn.next();
        }
		if (test) {
			System.out.println("SelectFile(): Line 97: Number of rows = " + 
				rowNum);
		}
        // string array to store the headings
        String [] headings = new String[columnNum];
        //2d object array to store data
        Object[][] data = new Object[rowNum][columnNum];   
		// loop takes the headings and stores them in array headings
		fileIn.close();
		j.close();
		if (test) {
			System.out.println("SelectFile():Line 107: Both scanners closed");
		}
		return populateArrays(inputFile, headings, data);
		
	}	
	
	/**
	*	A method that populates 2 arrays, one being the header and one being 
	*	the data, based on a file that has been selected.
	*
	*	\param CSV File
	*	\param Array of headings
	*	\param 2D Array for data
	*
	*	\return a list of DataTypeManagers, filled with the information in the 
	*		File.
	*
	*	No side-effects.
	*	Referentially transparent.
	*/
	private ArrayList<DataTypeManager> populateArrays (File file,
		String[] headings, Object[][] data) throws IOException {
		Scanner fileIn = new Scanner(file);
		boolean test = false;
		String firstLine = fileIn.nextLine();
		/* Splits the headings into an array */	
		headings = firstLine.split(",");
		int width = headings.length;
		int z = 0;
		while (fileIn.hasNextLine()){
		
			String newline = fileIn.nextLine();
			/* Splits the headings into an array */
			Object[] newData = newline.split(",");
			/* Populates 2D array with array */
			for (int n = 0; n < newData.length; n++) {
				if (newData.length > width) {
				} else {
					data[z][n] = newData[n];
				}

			}
			
			z++;
		}
		fileIn.close();
		if (test) {
			System.out.println("populateArrays(): Line150: Scanner closed");
		}
		return processData(headings, data);
	}
		
		
	/** creates new data type managers and stores the data from the 2d array in 
	*	them is the array storing all the data type managers, this allows the 
	*	class to pass them on to the class that calls them.
	*
	*	\param an array of headers
	*	\param a 2D array of data
	*
	*	\return A list of filled DataTypeManagers
	*
	*	No side-effects.
	*	Referentially transparent.
    */ 
	private ArrayList<DataTypeManager> processData (String[] headers,
		Object[][] data) {
		ArrayList<DataTypeManager> processedData =
			new ArrayList<DataTypeManager>();
		boolean test = false;
		int width = headers.length;
		int height = data.length;
		/* Process the header array */
		for (int column = 0; column < width; column++) {
			/* New DataTypeManager */
			DataTypeManager inputData = new DataTypeManager();
			if (width <= column) {
				/* Sets header to column num if width is smaller than or equal 
					to column*/
				inputData.SetHeader("Column " + column);
			} else {
				/* Sets header to data in array if in range */
				inputData.SetHeader(headers[column]);
			}
			processedData.add(inputData);
			if (test) {
				System.out.println("processData(): Line 187: new DataTypeManager" +
						"created");
			}
		}
		/* Loop that goes through each DataTypeManager in the ArrayList */
		for (int i = 0; i < processedData.size(); i++) {
			/* Loop that goes through the length of the 2D array */
			for (int k = 0; k < height; k++) {
				Object input = data[k][i];
				if (input == null) {
					processedData.get(i).AddObj(null);
				} else if (input.toString().trim().length() == 0) {
					processedData.get(i).AddObj(null);
				} else {
					processedData.get(i).AddObj(input);
				}
				if (test) {
					System.out.println("processData(): Line 203: Object added");
				}
			}
		}
		
		return processedData;
	}
 
}

