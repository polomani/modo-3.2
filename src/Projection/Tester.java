package Projection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jfree.ui.RefineryUtilities;

public class Tester {

	public static void main(String[] args) {
		init();
		createProjection();
		showProjection();
	}
	
	private static void init() {
		choises = DataInput.getInt("Введіть кількість рішень: ");
		consiquences = DataInput.getInt("Введіть кількість наслідків: ");
		parameters = DataInput.getInt("Введіть кількість параметрів: ");
		
		omegas = new double[parameters];
		for(int i=0; i<parameters; ++i)
			omegas[i] = DataInput.getDouble("Введіть ω"+(i+1)+": ");
		
		Z = new double[choises][consiquences][parameters];
		
		String str;
		StringTokenizer st;
		for(int i = 0; i < Z.length; ++i){
			str = DataInput.getString("x"+(i+1)+": ");
			st = new StringTokenizer(str);
			if(st.countTokens()<parameters){
				System.out.println("Введіть через пробіл номери наслідків для кожного параметру.");
				--i;
				continue;
			}
			for(int om = 0; om < Z[0][0].length; ++om ){
				str = st.nextToken();
				int c = Integer.valueOf(str);
				Z[i][c-1][om] = omegas[om];
			}
		}
	}
	
	private static void createProjection(){
		Zproj = new double[choises][consiquences];
		for(int i = 0; i < choises; ++i){
			for(int j = 0; j < consiquences; ++j){
				for(int k = 0; k < parameters; ++k){
					if(Z[i][j][k] > 0){
						Zproj[i][j] += Z[i][j][k];
					}
				}
			}
		}
	}
	
	private static void showProjection() {
		final Plot plot = new Plot("Проекція ", getPoints(), Zproj, choises, consiquences);
        plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
        plot.setAlwaysOnTop(true);
	}

	private static ArrayList<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < choises; ++i)
			for(int j = 0; j < consiquences; ++j)
				if(Zproj[i][j] > 0)
					points.add(new Point(i+1, j+1));
		return points;
	}
	
	private static int choises, consiquences, parameters;
	private static double Z[][][], Zproj[][], omegas[];

}
