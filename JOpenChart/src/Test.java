//import de.progra.charting.*;
//import de.progra.charting.model.*;
//import de.progra.charting.render.*;
//import java.awt.*;
//import java.io.*;
//import java.util.Date;
//
//import com.sun.jmx.snmp.Timestamp;
//
//public class Test{
//	static Database db = new Database();
//	static Double[] d;
//
//	public static void makeChart() {
//		
//	int[][] model = {{0, 1000, 200000}}; 	// Create data array 
//	double[] columns = {0.0, 1.0, 2000.0};  // Create x-axis values
//	String[] rows = {"DataSet 1"};          // Create data set title
//	String title = "Test";     		        // Create diagram title
//	int width = 640;                        // Image size
//	int height = 480;
//	// Create data model
//	DefaultChartDataModel data = new DefaultChartDataModel( /*db.selectStromverbrauch()*/model, columns, rows);
//	// Create chart with default coordinate system
//	DefaultChart c = new DefaultChart(data, title, DefaultChart.LINEAR_X_LINEAR_Y);
//	// Add a line chart renderer
//	c.addChartRenderer(new LineChartRenderer(c.getCoordSystem(), data), 1);
//	// Set the chart size
//	c.setBounds(new Rectangle(0, 0, width, height));
//	// Export the chart as a PNG image
//	try {
//		ChartEncoder.createEncodedImage(new FileOutputStream(System.getProperty("user.home")+"/OneDrive/HTL/INFI-DBP Köllö/JOpenChart/chart.png"), c, "png");
//		} catch(Exception e) {
//			e.printStackTrace();
//			}
//	}
//	public static Date[] getWasserDatum(){
//		Timestamp ts;
//		Date date;
//		double[][] wasserverbrauch = db.selectWasserverbrauch();
//		Date[] WasserDatum = new Date[47];
//		for (int i = 0; i < wasserverbrauch.length; i++) {
//			
//			ts = new Timestamp((long) wasserverbrauch[i][0]);
//			date = new Date(ts.getDateTime());
//			WasserDatum[i] = date;
//		}
//		return WasserDatum;
//	}
//	public static double[] getWasserValue(){
//		double[][] wasserverbrauch = db.selectWasserverbrauch();
//		double[] wasserValue = new double[47];
//		for (int i = 0; i < wasserverbrauch.length; i++) {
//			wasserValue[i] = wasserverbrauch[i][1];
//		}
//		return wasserValue;
//	}
//	public static double[] getStromTimestamp(){
//		double[][] stromverbrauch = db.selectStromverbrauch();
//		double[] stromTimestamp = new double[60];
//		for (int i = 0; i < stromverbrauch.length; i++) {
//			stromTimestamp[i] = stromverbrauch[i][0];
//		}
//		return stromTimestamp;
//	}
//	public static Date[] getStromDatum(){
//		Timestamp ts;
//		Date date;
//		double[][] stromverbrauch = db.selectStromverbrauch();
//		Date[] stromDatum = new Date[60];
//		for (int i = 0; i < stromverbrauch.length; i++) {
//			
//			ts = new Timestamp((long) stromverbrauch[i][0]);
//			date = new Date(ts.getDateTime());
//			stromDatum[i] = date;
//		}
//		return stromDatum;
//	}
//	public static double[] getStromValue(){
//		double[][] stromverbrauch = db.selectStromverbrauch();
//		double[] stromValue = new double[60];
//		for (int i = 0; i < stromverbrauch.length; i++) {
//			stromValue[i] = stromverbrauch[i][1];
//		}
//		return stromValue;
//	}
//	
//	public static double[] dateToDouble(Date[] date){
//		double[] doublearray = null;
//		for (int i = 0; i < date.length; i++) {
//			doublearray[i] = new Double(date[i].toString());
//		}
//		return doublearray;
//	}
//	public static void main(String[] args){
//
//		
//		makeChart();
//	}
//}