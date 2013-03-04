/**
* \file 	-GUIClass.java
* \author 	-P. Bird
* \date 	-25/02/2013
* \see		- JAVA for everyone, Cay Horstmann, Page 383, Syntax 9.3
* 
* \brief The GUI for the visualization program. 
* Links all other required classes together.
* 
*/

import javax.swing.JFrame; /* for JFrame */
import javax.swing.JButton; /* for JButton */
import java.awt.FlowLayout; /* for FlowLayout */
import java.awt.Toolkit; /* for Toolkit */
import java.awt.Container; /* for Container */
import java.awt.event.ActionListener; /* for ActionListener */
import java.awt.event.ActionEvent; /* for ActionEvent */
import javax.swing.ImageIcon; /* Image Icon */
import java.util.ArrayList; /* ArrayList */
import javax.swing.JOptionPane; /* JOptionPane */

public class GUIClass extends JFrame {
	/**
	* A constructor that creates the interface
	* and adds handlers to the interface.
	*/
	public void GUIClass() {
	
		//Set up the GUI
		//First the container
		JFrame frame = new JFrame("Partial Implementation - Version 1.0");
		Container container = getContentPane();
		Toolkit toolkit = container.getToolkit();
		container.setLayout(new FlowLayout());
	
		//add import button
		m_import_button = new JButton("Import");
        container.add(m_import_button);
		
		m_query_button = new JButton("Query");
		container.add(m_query_button);
		
		//add table view button
		
		ImageIcon tableIcon = new ImageIcon("img/TableViewButton.png");
		m_table_view_button = new JButton(tableIcon);
		container.add(m_table_view_button);
		
		//add import_button
		ImageIcon pieIcon = new ImageIcon("img/PieChartButton.png");
		m_pie_chart_button = new JButton(pieIcon);
        container.add(m_pie_chart_button);
		
		//add import_button
		ImageIcon barIcon = new ImageIcon("img/BarChartButton.png");
		m_bar_chart_button = new JButton(barIcon);
        container.add(m_bar_chart_button);
		
		//add import_button
		m_change_colour_button = new JButton("Change Colour");
        container.add(m_change_colour_button);
		
		//add import_button
		m_save_graph_button = new JButton("Save");
        container.add(m_save_graph_button);
	
		//Handlers
		guieventHandler handler = new guieventHandler();
		
		m_import_button.addActionListener(handler);
		m_query_button.addActionListener(handler);
		m_table_view_button.addActionListener(handler);
		m_pie_chart_button.addActionListener(handler);
		m_bar_chart_button.addActionListener(handler);
		m_change_colour_button.addActionListener(handler);
		m_save_graph_button.addActionListener(handler);
		
		// ... and display everything
        pack();
        setLocationRelativeTo(null);
		setSize(WIDTH, HEIGHT);
				
		frame.add(container);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enabledButton(m_import_button, true);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, false);
		enabledButton(m_save_graph_button, false);
		
	}
	
	/**
	* main method which is run when running
	* this class.
	*/
	public static void main(String[] args) {
 
       GUIClass e = new GUIClass();
       e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       e.GUIClass();
    }
	
	/**
	* Inner Class that links buttons on
	* interface to methods to be executed
	*/
	private class guieventHandler implements ActionListener {
		/**
		* Method that runs appropriate execution
		* depending what event has occured.
		*
		* @param event that has been performed
		*/
		public void actionPerformed(ActionEvent event) {
			boolean test = false;
			if (test) {
				System.out.println("Event is " + event.toString());
			}
			/* runs a method if source of event is from the import button */
			if (event.getSource() == m_import_button) {
				importFile();
			} else if (event.getSource() == m_query_button) {
				showQuery();
			/* runs a method if source of event is from the table view button */
			} else if (event.getSource() == m_table_view_button) {
				showTableView();
			/* runs a method if source of event is from the pie chart button */
			} else if (event.getSource() == m_pie_chart_button) {
				showPieChart();
			/* runs a method if source of event is from the bar chart button */
			} else if (event.getSource()== m_bar_chart_button) {
				showBarChart();
			/* runs a method if source of event is from the change colour button
				*/
			} else if (event.getSource() == m_change_colour_button) {
				changeColour();
			/* runs a method if source of event is from the save graph button */
			} else if (event.getSource() == m_save_graph_button) {
				saveGraph();
			}
		}
		
	}
	
	/**
	* Method that allows the user to change the colour
	* scheme of the chart on show.
	*
	* @return true when colour scheme is changed.
	*
	* Has side-effects, changes the state of the data.
	* Referentially transparent.
	*/
	private boolean changeColour() {
	
		m_chart.SetColourSet();
		
		if (m_chart instanceof PieChart) {
			PieChart piechart = (PieChart) m_chart;
			piechart.ChangeColour();
			piechart.Update();
		} else if (m_chart instanceof BarChart) {
			BarChart barchart = (BarChart) m_chart;
			barchart.ChangeColour();
			barchart.Update();
		}
	
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, true);
		enabledButton(m_save_graph_button, true);
		return true;
	}
	
	/**
	* Method that allows the user to create a title
	*
	* @return true when a title has been set.
	*
	* Has side-effects, changes the state of the data.
	* Referentially transparent.
	*/
	private boolean createTitle() {
		m_title = JOptionPane.showInputDialog("Name for chart?");
		return true;
	}
	
	/**
	* Method that enables/disables buttons
	* on the user interface.
	*
	* @param Button we want to change
	* @param state you want to change the button to
	*
	* @return true if button is set to new state
	*
	* Has side-effects, changes the state of a button.
	* Referentially transparent.
	*/
	private boolean enabledButton(JButton button, boolean enabled) {
		button.setEnabled(enabled);
		return true;
	}
	
	/**
	* Method that allows the user to select a file
	* using the FileChooser. Process that file and 
	* save its results.
	*
	* @return true if file is chosen and processed.
	*
	* Has side-effects, changes the state of the data.
	* Not referentially transparent, returns false if 
	* no file is chosen.
	*/
	private boolean importFile() {
		
		FileChooser filechooser = new FileChooser();
		try {
			m_data = filechooser.SelectFile();
		} catch (Exception e) {
			
		}
		if (m_data == null) {
			return false;
		} else {
			enabledButton(m_import_button, false);
			enabledButton(m_query_button, true);
			enabledButton(m_table_view_button, false);
			enabledButton(m_pie_chart_button, false);
			enabledButton(m_bar_chart_button, false);
			enabledButton(m_change_colour_button, false);
			enabledButton(m_save_graph_button, false);
			return true;
		}
		
	}
	
	/**
	* Method that allows the user to csave the graph
	* that is on show.
	*
	* @return true when chart has been saved.
	*
	* No side-effects.
	* Referentially transparent.
	*/
	private boolean saveGraph() {
		
		GraphSaver saver = new GraphSaver();
		saver.SaveGraph(m_chart, m_title);
		
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, true);
		enabledButton(m_save_graph_button, true);
		return true;
	}
	
	/**
	* Method that allows the user to show a bar chart
	* the data that has previously been selected.
	*
	* @return true when bar chart has been shown.
	*
	* Has side-effects, changes the state of the data.
	* Referentially transparent.
	*/
	private boolean showBarChart() {
	
		createTitle();
		BarChart barchart = new BarChart(m_data, m_title);
		barchart.Show(REPRESENTATION_WIDTH, REPRESENTATION_HEIGHT);
		m_chart = barchart; 
		
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, true);
		enabledButton(m_save_graph_button, true);
		return true;
	}
	
	/**
	* Method that allows the user to show a pie chart
	* the data that has previously been selected.
	*
	* @return true when pie chart has been shown.
	*
	* Has side-effects, changes the state of the data.
	* Referentially transparent.
	*/
	private boolean showPieChart() {
	
		createTitle();
		PieChart piechart = new PieChart(m_data, m_title);
		piechart.Show(REPRESENTATION_WIDTH, REPRESENTATION_HEIGHT);
		m_chart = piechart;
		
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, true);
		enabledButton(m_save_graph_button, true);
		return true;
	}
	
	/**
	* Method that allows the user to select make a query
	* to shorted the data that the user would want to present.
	*
	* @return true when query has been set.
	*
	* Has side-effects, changes the state of the data.
	* Referentially transparent.
	*/
	private boolean showQuery() {
		Query query = new Query(m_data);
		
		m_data = query.SelectColumns();
		m_data = query.SelectRows();
		
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, true);
		enabledButton(m_pie_chart_button, true);
		enabledButton(m_bar_chart_button, true);
		enabledButton(m_change_colour_button, false);
		enabledButton(m_save_graph_button, false);
		return true;
	}
	
	/**
	* Method that allows the user to show a table view of
	* the data that they would have previously selected.
	*
	* @return true when the table has been shown.
	*
	* No side-effects.
	* Referentially transparent.
	*/
	private boolean showTableView() {
	
		TableView table = new TableView(m_data);
		table.Show(REPRESENTATION_WIDTH, REPRESENTATION_HEIGHT);
	
		enabledButton(m_import_button, false);
		enabledButton(m_query_button, false);
		enabledButton(m_table_view_button, false);
		enabledButton(m_pie_chart_button, false);
		enabledButton(m_bar_chart_button, false);
		enabledButton(m_change_colour_button, false);
		enabledButton(m_save_graph_button, false);
		return true;
	}
	
	private JButton m_import_button;	/* import button */
	private JButton m_query_button;	/* Query Button */
	private JButton m_table_view_button;	/* table view button */
	private JButton m_pie_chart_button; /* pie chart button */
	private JButton m_bar_chart_button; /* bar chart button */
	private JButton m_change_colour_button; /* change colour button */
	private JButton m_save_graph_button; /* save graph button */
	private ArrayList<DataTypeManager> m_data; /* Data being represented */
	private final int WIDTH = 335; /* width of frame */
	private final int HEIGHT = 675; /* height of frame */
	private final int REPRESENTATION_WIDTH = 500; /* width of charts */
	private final int REPRESENTATION_HEIGHT = 500; /* height of charts */
	private String m_title; /* title of chart */
	private Visualization m_chart; /* Outputted graph */
	
}