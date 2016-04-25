package modo;

import java.util.StringTokenizer;

public class Tester {

	public static void main(String[] args) {
		int x = DataInput.getInt("Введіть кількість рішень: ");
		int c = DataInput.getInt("Введіть кількість наслідків: ");
		int o = DataInput.getInt("Введіть кількість параметрів: ");
		
		Z = new int[x][c][o];
		
		String str;
		StringTokenizer st;
		for(int i = 0; i < Z.length; ++i){
			str = DataInput.getString("x"+(i+1)+": ");
			st = new StringTokenizer(str);
			for(int om = 0; om < Z[0][0].length; ++om ){
				str = st.nextToken();
				int c0 = Integer.valueOf(str);
				Z[i][c0-1][om] = 1;
			}
		}
		
		createProjection(x, c, o);
		showProjection();
		
		createStretching();
		showStretching();
	}
	
	private static void createProjection(int x, int c, int o){
		Zproj = new int[x][c];
		for(int i = 0; i < x; ++i){
			for(int j = 0; j < c; ++j){
				for(int k = 0; k < o; ++k){
					if(Z[i][j][k]>0){
						Zproj[i][j] = 1;
					}
				}
			}
		}
	}
	
	private static void showProjection(){
		for(int i = Z.length-1; i >=0; --i){
			for(int j = 0; j < Z[i].length; ++j){
				System.out.print(Zproj[j][i]+" ");
			}
			System.out.println();
		}
	}
	
	private static void showStretching() {
		for(int i = 0; i<pathCount; ++i){
			System.out.println("omega"+i+":");
			for(int j = Zstretch.length-1; j >=0; --j){
				for(int k = 0; k < Zstretch[j].length; ++k){
					System.out.print(Zstretch[k][j][i]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private static void createStretching() {
		int count = 0;
		for(int i = 0; i<Zproj.length; ++i){
			for(int j = 0; j<Zproj[i].length; ++j){
				if(Zproj[i][j]>0){
					count++;
				}
			}
			pathCount *= count;
			count = 0;
		}
		System.out.println("Шляхів:"+pathCount);
		
		Zstretch = new int[Zproj.length][Zproj[0].length][pathCount];
		int[] numberOfPoints = new int[Zproj.length];
		
		for(int i=0; i<Zproj.length; ++i){
			for(int j=0; j< Zproj[i].length; ++j){
				numberOfPoints[i] += Zproj[i][j];
			}
		}
	
		for(int i=0; i<Zproj.length; ++i){
			int counter = 1;
			for(int j=0; j<Zproj[i].length; ++j){
				if(Zproj[i][j]==1){
					for(int k=(pathCount/numberOfPoints[i])*(counter-1); k<(pathCount/numberOfPoints[i])*counter; ++k){
						Zstretch[i][j][k] = Zproj[i][j];
					}
					counter++;
				}
			}
		}
	}
	
	private static int Z[][][], Zproj[][], Zstretch[][][], pathCount = 1;

}
