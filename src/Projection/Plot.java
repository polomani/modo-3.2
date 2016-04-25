package Projection;

import java.awt.Color;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class Plot extends ApplicationFrame {
	public Plot(String title, ArrayList<Point> points, double[][] probabilities, int choises, int consiquences) {
		super(title);
		this.points = points;
		this.probabilities = probabilities;
		this.choises = choises;
		this.consiquences = consiquences;
		
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	private XYDataset createDataset() {
		LabeledXYDataset dataset = new LabeledXYDataset();
		DecimalFormat df = new DecimalFormat("#.##");
		for(Point p:this.points)
			dataset.add(p.getX(), p.getY(), df.format(probabilities[(int) p.getX()-1][(int) p.getY()-1]));
		
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createXYLineChart(
				" ", // chart title
				"X", // x axis label
				"C", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, 
				false, // include legend
				true, // tooltips
				false // urls
				);
		
		final XYPlot plot = chart.getXYPlot();
		
		//налаштовую зовнішній вигляд
		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setBaseItemLabelGenerator(new LabelGenerator());
        renderer.setBaseItemLabelPaint(Color.black);
        renderer.setBaseItemLabelFont(
        renderer.getBaseItemLabelFont().deriveFont(14f));
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		plot.setRenderer(renderer);
		
		//налаштовую вісь абсцис
		final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		domainAxis.setRange(0, choises+1);
		
		//налаштовую вісь ординат
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setRange(0, consiquences+1);
		rangeAxis.setLabelAngle(Math.PI/2);
		
		return chart;

	}
	
	private ArrayList<Point> points;
	private double[][] probabilities;
	private int choises, consiquences;
	
	private static class LabeledXYDataset extends AbstractXYDataset {

        private static final int N = 26;
        private List<Number> x = new ArrayList<Number>(N);
        private List<Number> y = new ArrayList<Number>(N);
        private List<String> label = new ArrayList<String>(N);

        public void add(double x, double y, String label){
            this.x.add(x);
            this.y.add(y);
            this.label.add(label);
        }

        public String getLabel(int series, int item) {
            return label.get(item);
        }

        @Override
        public int getSeriesCount() {
            return 1;
        }

        @SuppressWarnings("rawtypes")
		@Override
        public Comparable getSeriesKey(int series) {
            return "Unit";
        }

        @Override
        public int getItemCount(int series) {
            return label.size();
        }

        @Override
        public Number getX(int series, int item) {
            return x.get(item);
        }

        @Override
        public Number getY(int series, int item) {
            return y.get(item);
        }
    }

    private static class LabelGenerator implements XYItemLabelGenerator {

        @Override
        public String generateLabel(XYDataset dataset, int series, int item) {
            LabeledXYDataset labelSource = (LabeledXYDataset) dataset;
            return labelSource.getLabel(series, item);
        }

    }
}




