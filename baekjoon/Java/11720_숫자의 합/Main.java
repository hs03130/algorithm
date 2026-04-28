import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int numCnt;
    static String num;
    static int ans;

    public static void main(String[] args) throws Exception {
        // 입력 받을 숫자 개수
        numCnt = Integer.parseInt(br.readLine());

        // 최대 100자리의 숫자
        num = br.readLine();

        ans = 0;
        // num에서 한 자리씩 떼어서 더하기
        for (int idx=0; idx<numCnt; idx++) {
            ans += num.charAt(idx) - '0';
        }

        System.out.println(ans);
    }
}