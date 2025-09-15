import java.util.Scanner;

public class Main {

	static int cnt; // 재귀 횟수

	// 공통 질문 : "재귀함수가 뭔가요?" ... 라고 탑변하였지.
	// 재귀 답변 : "잘 들어보게. ...
	// 기저 답변 : "재귀함수는 자기 자신을 호출하는 함수라네"
	// 깊이 0부터 cnt-1까지는 재귀 답변 출력, 마지막 cnt는 기저 답변 출력 후 종료
	static void func(int depth, String underScore) {
		System.out.println(underScore + "\"재귀함수가 뭔가요?\"");
		
		// 기저 조건 : cnt번째에는 기저 답변 출력되고 종료
		if (depth == cnt) {
			System.out.println(underScore + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(underScore + "라고 답변하였지.");
			return;
		} 
		
		// 재귀로 반복 될 부분
		System.out.println(underScore + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(underScore + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(underScore + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

		// 재귀 진행
		// 깊이가 증가할때마다 "____" 길이 증가
		func(depth + 1, underScore + "____");
		
		System.out.println(underScore + "라고 답변하였지.");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cnt = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		func(0, "");
	}

}