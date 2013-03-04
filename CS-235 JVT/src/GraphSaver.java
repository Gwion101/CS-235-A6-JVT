/**
 * \file    -PieChart.java
 * \author  -K. Zheng
 * \date    -22 Feb 2013
 * \see     -Program Development in Java, Babara Liskov, Page 65 4.2.4 Handling 
 *				Exceptions
 *
 *  \brief  -A class that saves the current 
 *	visualisation as a PNG
 *
 */
import org.jfree.chart.ChartUtilities;
import java.io.File;

public class GraphSaver {

	/**
	* Method that saves the current visualisation with a title
	* and as a PNG file.
	*
	* \param Visualization that is to be saved
	* \param title for the file
	*
	* \return boolean True if the visulisation has been successfully saved
	*
	* No side-effects.
	* Referentially transparent.
	*/
	public Boolean SaveGraph(Visualization chart, String title) {	
		boolean test = false;
		try {
			ChartUtilities.saveChartAsPNG(new File(title + ".PNG"), 
				chart.GetChart(), WIDTH, HEIGHT);
		} catch (Exception e) {
			if (test) {
				System.out.println("SaveGraph(): Line 35: Failure to save");
			}
			return false;
		}
		System.out.println("SaveGraph(): Line 39: Save successful");
		return true;
	}
	/** Variable declaring the width of the saved visualisation as 500 */
	final private int WIDTH = 500;
	/** Variable declaring the height of the saved visualisation as 350 */
	final private int HEIGHT = 350;	
}