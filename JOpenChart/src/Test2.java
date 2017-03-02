import de.progra.charting.*;
import de.progra.charting.model.*;
import de.progra.charting.render.*;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.*;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import com.sun.jmx.snmp.Timestamp;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

  

public class Test2 extends ApplicationFrame{
	
	 public  Test2( String applicationTitle , String chartTitle, double[][] graph){
	      super(applicationTitle);        
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Monat",            
	         "Verbrauch",            
	         createDataset(graph),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel(barChart);        
	      chartPanel.setPreferredSize(new java.awt.Dimension(560,367));        
	      setContentPane(chartPanel); 
	   }
	   private CategoryDataset createDataset(double[][] graph){      
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	      
	      for (int i = 0; i < graph.length; i++){
				dataset.addValue(graph[i][1], "", ""+(graph[i][0]+1));
	      }                    

	      return dataset; 
	   }
	
	static Database db = new Database();

	public static PolynomialSplineFunction getVerbrauch() {
		LinearInterpolator interpolator = new LinearInterpolator();
		ArrayList<Usage> verbrauch = db.selectStromverbrauch();
		//ArrayList<Usage> verbrauch = db.selectWasserverbrauch();
		double[] xaxis = new double[verbrauch.size()];
		double[] yaxis = new double[verbrauch.size()];
		for (int i = 0; i < verbrauch.size(); i++){
			long x = verbrauch.get(i).getTimestamp();
			System.out.println("x"+i+": "+x+"\n");
			xaxis[i] = x;
			double y = verbrauch.get(i).getValue();
			System.out.println("y"+i+": "+y+"\n");
			yaxis[i] = y;
		}
		return interpolator.interpolate(xaxis, yaxis);
	}

	public static double[][] absoluterVerbrauch() throws ParseException {
		PolynomialSplineFunction verbrauch = getVerbrauch();
		double[][] data = new double[12][2];
		Calendar cal = Calendar.getInstance();
		Date datum;
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		for (int i = 0; i < 12; i++) {
			cal.set(Calendar.MONTH, i);
			int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String datestringfirst = (i + 1) + "-01-" + 2016;
			String datestringlast = (i + 1) + "-" + days + "-" + 2016;
			datum = (Date) formatter.parse(datestringfirst);
			double firstvalue = verbrauch.value(datum.getTime() / 1000L);
			datum = (Date) formatter.parse(datestringlast);
			double lastvalue = verbrauch.value(datum.getTime() / 1000L);
			data[i][0] = datum.getMonth();
			data[i][1] = lastvalue - firstvalue;
		}
		return data;
	}
	
	public static double[][] durchschnittlicherVerbrauch() throws ParseException {
		PolynomialSplineFunction verbrauch = getVerbrauch();
		double[][] data = new double[12][2];
		Calendar cal = Calendar.getInstance();
		Date datum = null;
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		double sum = 0;
		for (int i = 0; i < 12; i++) {
			sum=0;
			cal.set(Calendar.MONTH, i);
			int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (int j = 0; j < days-1; j++) {
				String datestringfirst = (i + 1) + "-" + (j+1) + "-" + 2016;
				String datestringsecond = (i + 1) + "-" + (j+2) + "-" + 2016;
				datum = (Date) formatter.parse(datestringfirst);
				double firstvalue = verbrauch.value(datum.getTime() / 1000L);
				datum = (Date) formatter.parse(datestringsecond);
				double secondvalue = verbrauch.value(datum.getTime() / 1000L);
				sum += secondvalue-firstvalue;
			}
			data[i][0] = datum.getMonth();
			data[i][1] = sum/days;
		}
		return data;
	}

	public static void main(String[] args){
		double[][] graph;
		try {
			graph = absoluterVerbrauch();
			Test2 chart = new Test2("", "", graph);
			chart.pack();        
			RefineryUtilities.centerFrameOnScreen(chart);        
			chart.setVisible(true); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}