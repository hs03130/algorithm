import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static Vector<Person> persons = new Vector<>();

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());

		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			persons.add(new Person(Integer.parseInt(st.nextToken()), st.nextToken()));
		}

		persons.sort((p1, p2) -> p1.age - p2.age);

		for (Person p : persons) {
			System.out.println(p);
		}
	}

}

class Person {
	int age;
	String name;
	
	Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public String toString() {
		return age + " " + name;
	}
}