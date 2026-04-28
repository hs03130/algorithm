/*
 * 안내멘트는 L초 재생 + 5초 대기을 반복한다.
 * 고객은 반복적으로  D초마다 한 번씩 1초간 상담원 연결을 요청할 수 있다.
 * 5초 대기하는 동안 연결을 요청하면 연결된다.
 * 안내멘트가 N번 재생되면 반드시 다음 요청시 바로 연결됨
 * 
 * 연결될 수 있는 가장 빠른 시간
 * 
 * 1. 입력 : 반드시 연결되는 횟수 | 안내멘트 길이 | 요청을 반복할 시간
 * 2. 안내멘트가 재생되는 동안 요청 반복 (무시)
 * 3. 안내멘트가 끝나고 5초 안 범위에 요청을 할 수 있는지 확인
 * 4. 불가능하면 5초후부터 다시 안내멘트 시작
 * 5. cnt반복될 동안 요청을 끝내지 못햇으면 다음 요청할 수 있는 주기에 연결 성공
 *  
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int cnt; // 안내멘트 반복 횟수
	static int wait; // 안내멘트 주기
	static int send; // 요청 보내는 주기
	static int ans; // 연결 성공 시간

	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		cnt = Integer.parseInt(st.nextToken());
		wait = Integer.parseInt(st.nextToken());
		send = Integer.parseInt(st.nextToken());
	}

	static void solve() {
		ans = 0;

		int endTime = 0; // 안내 멘트가 종료 되는 시간
		int sendTime = send; // 요청을 보내는 시간
		for (int idx = 1; idx <= cnt; idx++) {
			endTime += wait; // 안내 멘트 재생
			while (sendTime < endTime) {
				sendTime += send;
			}
			// 안내멘트 종료 후 5초 안에 연결을 요청함
			if (sendTime >= endTime && sendTime < endTime + 5) {
				// 연결 성공
				ans = sendTime;
				return;
			}
			endTime += 5; // 5초 대기후 다시 안내 멘트 시작
		}
		// 요청 실패 -> N번 이후 무조건 연결 가능
		while (sendTime < (wait + 5) * (cnt - 1) + wait) {
			sendTime += send;
		}
		ans = sendTime;
	}

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(ans);
	}

}