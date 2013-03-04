/**
 * \file    -ClassTester.java
 * \author  -D. Morgan, D. Sherratt, J. Reynold, P. Bird, K. Zheng, X. Zhao.
 * \date    -03 Mar 2013
 * \see     -
 *
 *  \brief A class that tests other classes that we have created
 *		by putting arbitrary data in and seeing the expected output.
 *
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClassTester {
	
	/**
	* A constructor that accepts a dataset and runs
	* arbitrary tests on that dataset
	*
	* \param the dataset
	*/
	public ClassTester(ArrayList<DataTypeManager> testList) {
		boolean test1 = false;
		boolean test2 = false;
		boolean test3 = false;
		boolean test4 = false;
		boolean test5 = false;
		boolean test6 = false;
		boolean test7 = true;
		/* Runs test that programmer would want it to run */
		if (test1) {
			BarTest(testList);
		}
		if (test2) {
			BarTestPreColour(testList);
		}
		if (test3) {
			BarTestPostColour(testList);
		}
		if (test4) {
			PieTest(testList);
		}
		if (test5) {
			PieTestPreColour(testList);
		}
		if (test6) {
			PieTestPostColour(testList);
		}
		if (test7) {
			TableViewTest(testList);
		}
	}
	/**
	* main method which is run when running
	* this class.
	*
	* Arbitrary data was hard-coded in so we could
	* judge the expected outcome with the actual outcome.
	*/
	public static void main(String args[]){
		/* Initializing the data */
		DataTypeManager column1 = new DataTypeManager("David");
		column1.AddObj(85); /* Arbitrary data */
		column1.AddObj(40);	/* Arbitrary data */
		column1.AddObj(67); /* Arbitrary data */
		DataTypeManager column2 = new DataTypeManager("Josh");
		column2.AddObj(67); /* Arbitrary data */
		DataTypeManager column3 = new DataTypeManager("Dan");
		column3.AddObj(91); /* Arbitrary data */
		column3.AddObj(45); /* Arbitrary data */
		DataTypeManager column4 = new DataTypeManager("Anna");
		column4.AddObj(73); /* Arbitrary data */
		column4.AddObj(91); /* Arbitrary data */
		
		ArrayList<DataTypeManager> testList = new ArrayList<DataTypeManager>();
		testList.add(column1);
		testList.add(column2);
		testList.add(column3);
		testList.add(column4);
		
		ClassTester tester = new ClassTester(testList);
		
	}
	
	/**
	* Test that shows a bar chart
	*
	* \return true when bar chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean BarTest(ArrayList<DataTypeManager> data) {
		BarChart bartest = new BarChart(data, "Bar Test");
		bartest.Show(WIDTH, HEIGHT);
		return true;
	}
	
	/**
	* Test that shows a bar chart and lets
	* the user choose colour scheme after
	* chart is shown.
	*
	* \return true when bar chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean BarTestPostColour(ArrayList<DataTypeManager> data) {
		BarChart bartest = new BarChart(data, "Bar Test Pre Colour");
		bartest.Show(WIDTH, HEIGHT);
		bartest.SetColourSet();
		bartest.Update();
		return true;		
	}
	
	/**
	* Test that shows a bar chart and lets
	* the user choose colour scheme before
	* chart is shown.
	*
	* \return true when bar chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean BarTestPreColour(ArrayList<DataTypeManager> data) {
		BarChart bartest = new BarChart(data, "Bar Test Pre Colour");
		bartest.SetColourSet();
		bartest.Show(WIDTH, HEIGHT);
		return true;
	}
	
	/**
	* Test that shows a pie chart
	*
	* \return true when pie chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean PieTest(ArrayList<DataTypeManager> data) {
		PieChart pietest = new PieChart(data, "Pie Test");
		pietest.Show(WIDTH, HEIGHT);
		return true;
	}
	
	/**
	* Test that shows a pie chart and lets
	* the user choose colour scheme after
	* chart is shown.
	*
	* \return true when pie chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean PieTestPostColour(ArrayList<DataTypeManager> data) {
		PieChart pietest = new PieChart(data, "Pie Test Pre Colour");
		pietest.Show(WIDTH, HEIGHT);
		pietest.SetColourSet();
		pietest.Update();
		return true;
	}
	
	/**
	* Test that shows a pie chart and lets
	* the user choose colour scheme before
	* chart is shown.
	*
	* \return true when pie chart is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean PieTestPreColour(ArrayList<DataTypeManager> data) {
		PieChart pietest = new PieChart(data, "Pie Test Pre Colour");
		pietest.SetColourSet();
		pietest.Show(WIDTH, HEIGHT);
		return true;
	}
	
	/**
	* Test that shows a table view
	*
	* \return true when table view is created
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public boolean TableViewTest(ArrayList<DataTypeManager> data) {
		TableView tabletest = new TableView(data);
		tabletest.Show(WIDTH, HEIGHT);
		return true;
	}
	
	final static int WIDTH = 500; /* Width of diaplyed chart */
	final static int HEIGHT = 500; /* Height of displayed chart */
}