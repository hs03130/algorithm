import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;

	static Person persons[];

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		
		persons = new Person[N];
		for(int idx=0; idx<N; idx++) {
			st = new StringTokenizer(br.readLine());
			persons[idx] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int idx=0; idx<N; idx++) {
			int num = 1;
			for(Person person : persons) {
				if (persons[idx].height < person.height && persons[idx].weight < person.weight) {
					num++;
				}
			}
			sb.append(num + " ");
		}
		
		System.out.println(sb);

	}

}

class Person {
	int height;
	int weight;
	
	public Person(int x, int y) {
		this.height = x;
		this.weight = y;
	}
	
}
