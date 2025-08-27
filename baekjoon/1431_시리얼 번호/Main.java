import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int cnt;
	static ArrayList<String> serialNums;

	public static void main(String[] args) throws IOException {

		cnt = Integer.parseInt(br.readLine());
		serialNums = new ArrayList<>();
		for (int idx = 0; idx < cnt; idx++) {
			serialNums.add(br.readLine());
		}

		serialNums.sort((s1, s2) -> {
			// 1. 길이가 짧은 것
			if (s1.length() != s2.length()) {
				return s1.length() - s2.length();
			}
			// 2. 숫자 합이 작은 것
			int sum1 = 0, sum2 = 0;
			for (int idx = 0; idx < s1.length(); idx++) {
				char c = s1.charAt(idx);
				if (c >= '0' && c <= '9') {
					sum1 += c - '0';
				}
			}
			for (int idx = 0; idx < s2.length(); idx++) {
				char c = s2.charAt(idx);
				if (c >= '0' && c <= '9') {
					sum2 += c - '0';
				}
			}
			if (sum1 != sum2) {
				return sum1 - sum2;
			}

			// 3. 사전 순
			return s1.compareTo(s2);
		});

		for (String serialNum : serialNums) {
			sb.append(serialNum + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}