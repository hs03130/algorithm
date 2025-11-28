import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static int totalPasswordCnt;
	static int findCnt;
	static HashMap<String, String> map;
	
	public static void main(String[] args) throws Exception {
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		totalPasswordCnt = Integer.parseInt(st.nextToken());
		findCnt = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		for(int idx=0; idx<totalPasswordCnt; idx++) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken());
		}

		for(int idx=0; idx<findCnt; idx++) {
			sb.append(map.get(br.readLine()) + "\n");
		}
		
		System.out.println(sb.toString());
	}

}