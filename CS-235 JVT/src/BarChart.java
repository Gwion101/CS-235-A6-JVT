/**
 * \file    -BarChart.java
 * \author  -J. Reynolds
 * \date    -28 Feb 2013
 * \see     -
 *
 *  \brief  -A class that allows the user to view
 *	their data in the form of a BarChart. They
 *  can modify the colours for each series.
 *
 */

import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import java.awt.Color;
import org.jfree.chart.plot.CategoryPlot;

public class BarChart extends Visualization {
	/**
	* Constructor that takes in DataTypeManager as an arraylist
	* and barTitle as a string for arguments. It loops through
	* the data and assigns cell values. It then sets the dataset 
	* for the m_barDataset from the CSV file.
	*/
    public BarChart(ArrayList<DataTypeManager> data, String barTitle) {		
		SetTitle(barTitle);
		SetDataset(data);
		SetColourSet(new ColourManager());		
		m_barDataset = new DefaultCategoryDataset();		
		int length = 0;
		for (int i = 0; i < GetDataset().size(); i++) {
			if (GetDataset().get(i).ReturnSize() > length) {
				length = GetDataset().get(i).ReturnSize();
			}
		}		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < GetDataset().size(); j++) {
				double cellValue;
				if (GetDataset().get(j).GetElement(i) == null) {
					cellValue = 0;	//Replaces empty cells with 0 values
				} else {				
					cellValue = Double.parseDouble(GetDataset().get(j).
						GetElement(i).toString());
				}
				String series = "series " + i;
				String cellHeader = GetDataset().get(j).GetHeader();
				m_barDataset.setValue(cellValue, series, cellHeader);				
			}
		}		
	}
	
	/**
	* Method that allows the user to change the colour of
	* each series of the Bar Chart.
	*
	* \return boolean True if the colour has been changed
	*
	* Has side-effects, changes the plot for the chart.
	* Referentially transparent.
	*/	
	public boolean ChangeColour(){		
		createChart();		
		ArrayList<Color> m_colourList = GetColourSet();		
		int colourCounter = 0;
		for (int i = 0; i < m_barDataset.getRowCount(); i++) {		
			m_plot.getRenderer().setSeriesPaint( i, 
				m_colourList.get(colourCounter));			
			colourCounter++;
			if (colourCounter == GetColourSet().size()) {
				colourCounter = 0; //Reset
			}			
		}		
		return true;
	}
	
	/**
	* Method that displays the Bar Chart on a JFrame 
	*
	* \param width of displayed chart
	* \param height of displayed chart
	*
	* \return boolean True if the BarChart has been added
	*
	* Has side-effects, changes the panel on the frame.
	* Referentially transparent.
	*/
	public boolean Show(int width, int height) {		
		createChart();		
		ChangeColour();		
		JFrame frame = new JFrame();
		m_chartPanel = new JPanel(new BorderLayout());		
		ChartPanel barchartpanel = new ChartPanel(m_chart);		
		m_chartPanel.add(barchartpanel);		
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.add(m_chartPanel);
		frame.pack();
 		frame.setVisible(true); 		
		return true;
	}
	
	/**
	* Method that updates the Bar Chart once changes have been
	* completed
	*
	* \return boolean True if the update what successful
	*
	* Has side-effects, changes the panel on the frame.
	* Referentially transparent.
	*/	
	public boolean Update() {		
		m_chartPanel.removeAll();
		createChart();
		ChangeColour();
		ChartPanel barchartpanel = new ChartPanel(m_chart);
		m_chartPanel.add(barchartpanel);
		m_chartPanel.validate();
	    m_chartPanel.repaint();		
		return true;
	}
	
	/**
	* Method that creates all the features for the Bar Chart.
	*
	* \return boolean True if the chart has been created
	*
	* Has side-effects, changes plot and chart.
	* Referentially transparent.
	*/
	private boolean createChart() {
		m_chart = ChartFactory.createBarChart(
			GetTitle(),
			" ",
			" ",
			m_barDataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false
		);
		
		m_plot = (CategoryPlot) m_chart.getPlot();
		SetChart(m_chart);
		return true;
	}
 	
	/** Variable declaring m_barDataset as Default */
	private DefaultCategoryDataset m_barDataset;
	/** variable storing current plot value */
	private CategoryPlot m_plot;
	/** variable storing current panel for the frame */
	private JPanel m_chartPanel;
	/** variable storing current chart */
	private JFreeChart m_chart;
}