package Stretching;

import java.awt.Point;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tester {

	public static void main(String[] args) {
		choises = DataInput.getInt("Введіть кількість рішень: ");
		consequences = DataInput.getInt("Введіть кількість наслідків: ");
		Z = new double[choises][consequences];
		for(int i = 0; i<choises; ++i){
			for(int j = 0; j<consequences; ++j){
				Z[i][j] = DataInput.getDouble("Введіть ймовірність (x"+(i+1)+", c"+(j+1)+"): ");
			}
		}
		
		findPaths("",0);
		System.out.println("Шляхів: "+paths.size());
		new Plot(getPaths(), Z, choises, consequences);

	}
	
	private static void findPaths(String prev, int x){
		if(x<choises){
			for(int j=0; j<consequences; ++j)
				if(Z[x][j]>0)
					findPaths(prev+" "+j, x+1);
		}else{
			paths.add(prev);
		}
				
	}
	
	private static ArrayList<ArrayList<Point>> getPaths(){
		ArrayList<ArrayList<Point>> pathPoints = new ArrayList<ArrayList<Point>>();
		for(String s:paths){
			StringTokenizer st = new StringTokenizer(s);
			ArrayList<Point> points = new ArrayList<Point>();
			int x=0;
			while(st.hasMoreTokens())
				points.add(new Point((x++)+1, Integer.valueOf(st.nextToken())+1));
			pathPoints.add(points);
		}
		return pathPoints;
	}
	
	private static ArrayList<String> paths = new ArrayList<String>();
	private static int choises, consequences;
	private static double Z[][];
}
