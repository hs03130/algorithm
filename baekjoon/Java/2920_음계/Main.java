import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static String line;
	
	static final String ASCENDING = "1 2 3 4 5 6 7 8";
	static final String DESCENDING = "8 7 6 5 4 3 2 1";
	
	public static void main(String[] args) throws IOException {

		line = br.readLine();
		
		if (line.equals(ASCENDING)) {
			System.out.println("ascending");
		} else if (line.equals(DESCENDING)) {
			System.out.println("descending");
		} else {
			System.out.println("mixed");
		}

	}

}
