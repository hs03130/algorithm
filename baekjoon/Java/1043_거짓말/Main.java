import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int personCnt, partyCnt, truthKnowerCnt;
	static ArrayList<Integer> truthKnowers;
	static ArrayList<Integer>[] personToParties, partyToPerson;
	static boolean[] visitedParty, visitedPerson;
	static ArrayDeque<Integer> Q;
	static int ans;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		personCnt = Integer.parseInt(st.nextToken());
		partyCnt = Integer.parseInt(st.nextToken());
		ans = partyCnt;

		// 진실을 아는 사람 정보
		st = new StringTokenizer(br.readLine());
		truthKnowerCnt = Integer.parseInt(st.nextToken());
		truthKnowers = new ArrayList<>();
		for (int idx = 0; idx < truthKnowerCnt; idx++) {
			truthKnowers.add(Integer.parseInt(st.nextToken()) - 1);
		}

		// 파티 참석 정보
		personToParties = new ArrayList[personCnt];
		partyToPerson = new ArrayList[partyCnt];
		for (int partyIdx = 0; partyIdx < partyCnt; partyIdx++) {
			partyToPerson[partyIdx] = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			int attendeeCnt = Integer.parseInt(st.nextToken());
			for (int attendeeIdx = 0; attendeeIdx < attendeeCnt; attendeeIdx++) {
				int attendee = Integer.parseInt(st.nextToken()) - 1;
				if (personToParties[attendee] == null) {
					personToParties[attendee] = new ArrayList<>();
				}
				personToParties[attendee].add(partyIdx);
				partyToPerson[partyIdx].add(attendee);
			}
		}

		visitedParty = new boolean[partyCnt];
		visitedPerson = new boolean[personCnt];
		Q = new ArrayDeque<>();
		// 진실을 아는 사람과, 진실을 말한 파티에 참석한 사람들이 있는 파티에서는 거짓말을 할 수 없다
		for (int truthKnower : truthKnowers) {
			if (visitedPerson[truthKnower]) continue;

			visitedPerson[truthKnower] = true;
			Q.offer(truthKnower);

			while (!Q.isEmpty()) {
				int cur = Q.poll();
				if (personToParties[cur] == null) continue;
				// 진실을 아는 사람이 참석한 파티
				for (int party : personToParties[cur]) {
					// 아직 확인하지 않은 파티
					if (!visitedParty[party]) {
						// 같은 파티에 참석한 사람들을 Q에 넣는다
						for (int attendee : partyToPerson[party]) {
							if (!visitedPerson[attendee]) {
								visitedPerson[attendee] = true;
								Q.offer(attendee);
							}
						}
						visitedParty[party] = true;
						ans--; // 거짓말을 할 수 없다
					}
				}
			}
		}

		bw.write(Integer.toString(ans));
		bw.flush();
		bw.close();
	}

}
