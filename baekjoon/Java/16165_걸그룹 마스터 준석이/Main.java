import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int teamCnt, questionCnt;
	static HashMap<String, String> mapMember;
	static HashMap<String, List<String>> mapTeam;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		teamCnt = Integer.parseInt(st.nextToken());
		questionCnt = Integer.parseInt(st.nextToken());

		mapMember = new HashMap<>();
		mapTeam = new HashMap<>();
		for (int idx = 0; idx < teamCnt; idx++) {
			String team = br.readLine();
			int memberCnt = Integer.parseInt(br.readLine());
			ArrayList<String> members = new ArrayList<>();
			while (memberCnt-- > 0) {
				String member = br.readLine();
				members.add(member);
				mapMember.put(member, team);
			}
			Collections.sort(members);
			mapTeam.put(team, members);
		}

		for (int idx = 0; idx < questionCnt; idx++) {
			String name = br.readLine();
			int cmd = Integer.parseInt(br.readLine());
			if (cmd == 0) {
				for (String member : mapTeam.get(name)) {
					sb.append(member + '\n');
				}
			} else {
				sb.append(mapMember.get(name) + '\n');
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
