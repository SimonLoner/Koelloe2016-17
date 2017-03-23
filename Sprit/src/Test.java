import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Test extends ApplicationFrame{
	
	static Datenbank db = new Datenbank();
	
	 public Test(String applicationTitle, String chartTitle, double[][] graph){
	      super(applicationTitle);        
	      JFreeChart barChart = ChartFactory.createLineChart(
	         chartTitle,           
	         "Tag",            
	         "Kosten",            
	         createDataset(graph),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	      
	      barChart.getCategoryPlot().getRangeAxis().setRange(1.14,1.27);
	         
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
	 
	 public static double[][] jahresdurchschnittProTag(){
		 ArrayList<SpritObjekt> jpt = db.getJahresdurschnitProTag(1, "2017-01-01", "2017-01-31") ;
		 double[][]daten = new double[jpt.size()][2];
		 for(int i = 0; i < daten.length; i++) {
			 daten[i][0] = jpt.get(i).getZeit().getTime();
			 daten[i][1] = jpt.get(i).getWert();
		}
		 for (double[] ds : daten) {
			System.out.println(ds[0]+" "+ds[1]+"");
		}
		 return daten;
	 }
	 
	 public static double[][] billigsteTanke(){
		 ArrayList<SpritObjekt> jpt = db.getBilligsteTanke("2017-01-01 12", "2017-01-2 12") ;
		 double[][]daten = new double[jpt.size()][2];
		 for(int i = 0; i < daten.length; i++) {
			 daten[i][0] = jpt.get(i).getZeit().getTime();
			 daten[i][1] = jpt.get(i).getWert();
		}
		 return daten;
	 }
	 
	 public static void main(String[] args){
		 
		 double[][] graph;
		 
			//graph = jahresdurchschnittProTag();
			graph = billigsteTanke();
			Test chart = new Test("", "", graph);
			chart.pack();        
			RefineryUtilities.centerFrameOnScreen(chart);        
			chart.setVisible(true);
	 }

}
