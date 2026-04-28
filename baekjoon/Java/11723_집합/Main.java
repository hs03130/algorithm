import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static HashSet<Integer> hs;
	static int cmdCnt;
	static String cmd;
	static int number;
	
	public static void main(String[] args) throws Exception {
		hs = new HashSet<>();
		sb = new StringBuilder();

		cmdCnt = Integer.parseInt(br.readLine()); 
		
		for(int cnt=0; cnt<cmdCnt; cnt++) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			if (cmd.equals("add")) {
				number = Integer.parseInt(st.nextToken());
				hs.add(number);
			} else if (cmd.equals("remove")) {
				number = Integer.parseInt(st.nextToken());
				hs.remove(number);
			} else if (cmd.equals("check")) {
				number = Integer.parseInt(st.nextToken());
				if(hs.contains(number)) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
			} else if (cmd.equals("toggle")) {
				number = Integer.parseInt(st.nextToken());
				if (!hs.remove(number)) {
					hs.add(number);
				}
			} else if (cmd.equals("all")) {
				hs = new HashSet<>();
				for(int idx=1; idx<=20; idx++) {
					hs.add(idx);
				}
			} else if (cmd.equals("empty")) {
				hs = new HashSet<>();
			}
		}
		System.out.println(sb.toString());
	}
}