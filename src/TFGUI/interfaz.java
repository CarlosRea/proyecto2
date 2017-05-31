package TFGUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.IntervalCategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.Month;
import org.jfree.ui.TextAnchor;

import TFG.Cliente;
import TFG.Puertas;
import TFG.Vuelos;

public class interfaz extends JFrame{
	private static final long serialVersionUID = 1L;

	public interfaz(String applicationTitle, String chartTitle) {

		super(applicationTitle);

		// Creamos el conjunto de datos con las votaciones
		TaskSeriesCollection dataset = createDataset();

		JFreeChart chart = createChart(dataset, chartTitle);
		// Ponemos el gráfico en un panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// Dejamos el tamaño por defecto
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 400));
		// Lo añadimos a nuestra aplicación (PieChart)
		setContentPane(chartPanel);

	}

	/**
	 * Creates a sample dataset
	 */

	private TaskSeriesCollection createDataset() {
		//pruebas ejemplo 
		
		TaskSeriesCollection dataset = new TaskSeriesCollection();

		TaskSeries vuelos = new TaskSeries("Vuelos");
		List<Task> puertas = new ArrayList<Task>();
		for(int i=0;i<Cliente.puertas.size();i++){
			Task puerta= new Task(Cliente.puertas.get(i).getNumero(), 
					new GregorianCalendar(2009, Month.DECEMBER, 4, 17, 00).getTime(), 
					new GregorianCalendar(2009, Month.DECEMBER, 5, 01, 00).getTime());
				vuelos.add(puerta);
				puertas.add(puerta);
		}
		
		for(Task puerta:puertas){
			for(Vuelos vuelo:Cliente.vuelos){
				if (puerta.getDescription().equals(vuelo.getPuertas().getNumero())){
					puerta.addSubtask(new Task(vuelo.getNumero(),
							new GregorianCalendar(2009, Month.DECEMBER, vuelo.getFechaLlegada().getTime().getDay(), vuelo.getFechaLlegada().getTime().getHours(), vuelo.getFechaLlegada().getTime().getMinutes()).getTime(), 
							new GregorianCalendar(2009, Month.DECEMBER, vuelo.getFechaSalida().getTime().getDay(), vuelo.getFechaSalida().getTime().getHours(), vuelo.getFechaSalida().getTime().getMinutes()).getTime()));
				}
			}
			
		}

		dataset.add(vuelos);
		

		return dataset;

	}

	/**
	 * Creates a chart por andres2288
	 */

	private JFreeChart createChart(final TaskSeriesCollection dataset, String title) {

		JFrame frame = new JFrame("MeetNow!");	
		// title, domain axis, range axis, dataset, legend, tooltip, urls
		JFreeChart chart = ChartFactory.createGanttChart(title, "Puertas", "Tiempo", dataset, true, true, true);
		//
		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		 MyGanttRenderer renderer = new MyGanttRenderer(dataset);
		 plot.setRenderer(renderer);

		 renderer.setBaseItemLabelGenerator(new CategoryItemLabelGenerator() {

		      public String generateLabel(CategoryDataset dataSet, int series, int categories) {
		       /* your code to get the label */
		    	String aux = null;
		        List<Task> tareas=dataset.getSeries(series).getTasks();
		       /* List res = null;
		        for(Task tar:tareas){
		        	res=new ArrayList<String>();
		        	for(int i=0;i<tar.getSubtaskCount();i++){
		        			aux=tar.getSubtask(series).getDescription();
		        			res.add(aux);
		        	}
		        }
				return java.util.Arrays.toString(dataset.getSeries(series).getTasks().toArray());*/
		        
				return "";
		        
		      }

		      public String generateColumnLabel(CategoryDataset dataset, int categories) {
		          return dataset.getColumnKey(categories).toString();
		      }

		      public String generateRowLabel(CategoryDataset dataset, int series) {
		          return dataset.getRowKey(series).toString();
		      }
		 });

		 renderer.setBaseItemLabelsVisible(true);
		 renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_LEFT));
		renderer.setBaseItemLabelPaint(Color.BLACK);

		//

		ChartPanel chartPanel = new ChartPanel(chart);
		frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(50, 50, 800, 200);
		frame.setVisible(true);
		
		
		return chart;

	}

	public static void main(String[] args) {
		/*interfaz demo = new interfaz("Elecciones Generales 2011",
				"Escaños obtenidos por partido");
		demo.pack();
		demo.setVisible(true);*/
	}
}
