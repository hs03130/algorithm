import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb;
	static long start;
	static long end;

	public static void main(String[] args) {
		start = sc.nextLong();
		end = sc.nextLong();

		if (start == end) {
			System.out.println(0);
			return;
		}
		
		if (start > end) {
			long tmp = start;
			start = end;
			end = tmp;
		}

		sb = new StringBuilder();
		for (long num = start + 1; num < end; num++) {
			sb.append(num + " ");
		}
		System.out.println(end - start - 1);
		System.out.println(sb.toString());
	}

}