package Stretching;

import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Chart3DPanel;
import com.orsoncharts.axis.NumberAxis3D;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import com.orsoncharts.graphics3d.swing.DisplayPanel3D;
import com.orsoncharts.plot.XYZPlot;

public class Plot {
	public Plot(ArrayList<ArrayList<Point>> paths, double[][] z, int choises, int consequences) {
		this.parameters = paths.size();
		Z = z;
		this.choises = choises;
		this.consequences = consequences;
		JFrame content = new JFrame("Розтягнення");
		content.setMinimumSize(new Dimension(800, 600));
		XYZSeriesCollection dataset = createDataset(paths);
		Chart3D chart = createChart(dataset);
		Chart3DPanel chartPanel = new Chart3DPanel(chart);
		content.add(new DisplayPanel3D(chartPanel));
		content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.pack();
		content.setVisible(true);
	}

	private Chart3D createChart(XYZSeriesCollection dataset) {
		Chart3D chart = Chart3DFactory.createScatterChart("", "", dataset, "X",
				"C", "Ω");

		final XYZPlot plot = (XYZPlot) chart.getPlot();

		final NumberAxis3D xAxis = (NumberAxis3D) plot.getXAxis();
		xAxis.setRange(0, choises+1);

		final NumberAxis3D yAxis = (NumberAxis3D) plot.getYAxis();
		yAxis.setRange(0, consequences+1);

		final NumberAxis3D zAxis = (NumberAxis3D) plot.getZAxis();
		zAxis.setRange(0, parameters+1);

		return chart;
	}

	private static XYZSeriesCollection createDataset(
			ArrayList<ArrayList<Point>> paths) {
		XYZSeriesCollection dataset = new XYZSeriesCollection();
		XYZSeries series[] = new XYZSeries[paths.size()];
		int p = 0;
		DecimalFormat df = new DecimalFormat("#.####");
		for (ArrayList<Point> path : paths) {
			series[p] = new XYZSeries("ω" + (p + 1));
			double om = 1;
			for (Point point : path){
				series[p].add(point.getX(), point.getY(), p + 1);
				om *= Z[(int)point.getX()-1][(int)point.getY()-1];
			}
			System.out.println(series[p].getKey()+" = "+df.format(om));
			++p;
		}

		for (int i = 0; i < series.length; ++i)
			dataset.add(series[i]);
		return dataset;
	}
	
	private int choises, consequences, parameters;
	private static double[][] Z;
}
