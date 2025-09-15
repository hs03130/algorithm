import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static Coordinate c[];

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		
		c = new Coordinate[N];
		for(int idx=0; idx<N; idx++) {
			st = new StringTokenizer(br.readLine());
			c[idx] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(c);
		
		for (Coordinate coor: c) {
			System.out.println(coor.x + " " + coor.y);
		}
	}

}

class Coordinate implements Comparable<Coordinate> {
	int x;
	int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Coordinate o) {
		if (this.y == o.y) {
			return this.x - o.x;
		}
		return this.y - o.y;
	}
	
}
