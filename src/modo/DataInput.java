package modo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

	private static void writeText(String wr){
		if (wr == null)
			System.out.print("Введіть дані: ");
		else 
			System.out.print(wr);
	}
	
	public static Long getLong() throws IOException{
		String s = getString();
		Long value = Long.valueOf(s);
		return value;
	}
	
	public static char getChar(String wr) {
		writeText(wr);
		String s = "";
		try {
			s = getString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s.charAt(0);
	}
	
	public static Integer getInt(String wr){
		writeText(wr);
		String s="";
		try {
			s = getString();
			for(int i=0; i<s.length(); i++){
				if(!Character.isDigit(s.charAt(i))){
					System.out.println("Введіть ціле число");
					return getInt("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!s.equals("")){
			Integer value = Integer.valueOf(s);
			return value;
		}
		else{
			System.out.println("Введіть ціле число");
			return getInt("");
		}
	}
	
	public static Double getDouble(String wr){
		writeText(wr);
		String s = "";
		try {
			s = getString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!s.equals("")){
			Double value = Double.valueOf(s);
			return value;
		}
		else
			return getDouble("Введіть ціле число");
	}
	
	public static String getString(String wr){
		writeText(wr);
		String s = "";
		try {
			s = getString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = String.valueOf(s);
		return value;		
	}
	
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
}
