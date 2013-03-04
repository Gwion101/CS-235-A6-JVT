/**
 * \file    -PieChart.java
 * \author  -D. Morgan
 * \date    -28 Feb 2013
 * \see     -
 *
 *  \brief  -A class that allows the user to view
 *	their data in the form of a PieChart. They
 *  can modify the colours for each segement.
 *
 */
 
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class PieChart extends Visualization{
	/**
	* Constructor that takes in DataTypeManager as an arraylist
	* and chartTitle as a string for arguments. It loops through
	* the data and assigns cell values. It then sets the dataset 
	* for the m_pieDataset from the CSV file.
	*
	* \param ArrayList of DataTypeManagers, the initial data
	* \param The title of the chart
	*
	*/
  public PieChart(ArrayList<DataTypeManager> data, String chartTitle) {        
		SetDataset(data);
		SetTitle(chartTitle);
		SetColourSet(new ColourManager());		
		m_pieDataset = new DefaultPieDataset(); 		
		int i = 0;
		for (int j = 0; j < GetDataset().size(); j++) {
			double cellValue = Double.parseDouble(GetDataset().get(j).
				GetElement(i).toString());
			m_pieDataset.setValue(GetDataset().get(j).GetHeader(), cellValue);
		}
    }
	
	/**
	* Method that allows the user to change the colour of
	* each segement of the Pie Chart.
	*
	* \return boolean True if the colour has been changed
	*
	* Has side-effects as it changes the global state
	* of the program's varibale m_plot.
	* Referentially transparent.
	*/
	public boolean ChangeColour(){		
		createChart();		
		ArrayList<Color> m_colourList = GetColourSet();
		int colourCounter = 0;
		for (int i = 0; i < m_pieDataset.getItemCount(); i++) {			
			m_plot.setSectionPaint(i, m_colourList.get(colourCounter));
			colourCounter++;
			if (colourCounter == GetColourSet().size()) {
				colourCounter = 0; //Reset
			}			
		}		
		return true;
	}
	
	/**
	* Method that displays the Pie Chart on a JFrame 
	*
	* \param width of the displayed chart
	* \param height of the displayed chart
	*
	* \return boolean True if the PieChart has been added
	*
	* Has side-effects as it changes panels.
	* Referentially transparent.
	*/
	public boolean Show(int width, int height) {		
		createChart();		
		ChangeColour();		
		JFrame frame = new JFrame();
		m_chartPanel = new JPanel(new BorderLayout());		
		ChartPanel piechartpanel = new ChartPanel(m_chart);		
		m_chartPanel.add(piechartpanel);				
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.add(m_chartPanel);
		frame.pack();
 		frame.setVisible(true);		
		return true;
	}
	
	/**
	* Method that updates the Pie Chart once changes have been
	* completed
	*
	* \return boolean True if the update what successful
	*
	* Has side-effects as it changes the panel
	* Referentially transparent
	*/
	public boolean Update(){		
		m_chartPanel.removeAll();
		createChart();
		ChangeColour();
		ChartPanel piechartpanel = new ChartPanel(m_chart);
		m_chartPanel.add(piechartpanel);
		m_chartPanel.validate();
		m_chartPanel.repaint();		
		return true;
	}
	
	/**
	* Method that creates all the features for the Pie Chart and then
	* stores the structure in "m_plot".
	*
	* \return boolean True if the chart has been created
	*
	* Has side-effects as it changes the panel.
	* Referentially transparent.
	*/
	private boolean createChart() {
		m_chart = ChartFactory.createPieChart3D(
			GetTitle(), 
			m_pieDataset, 
			true, 
			true,
			false
		);		
		m_plot = (PiePlot3D) m_chart.getPlot();	
		SetChart(m_chart);
		return true;
	}
	
	/** variable declaring m_pieDataset as Default */
	private DefaultPieDataset m_pieDataset;
	/** variable storing current plot value */
    private PiePlot3D m_plot;
	/** variable storing current state of the chart */
	private JPanel m_chartPanel;
	/** variable storing the chart */
	private JFreeChart m_chart;
} 