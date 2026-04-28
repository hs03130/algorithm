import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int hour;
    static int minute;
    static int totalMinute;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        hour = Integer.parseInt(st.nextToken());
        minute = Integer.parseInt(st.nextToken());

        // 기상 시간이 0:44분 이전이면 24시로 바꿔준다.
        if (hour == 0 && minute < 45) {
            hour = 24;
        }

        totalMinute = hour * 60 + minute - 45;
        System.out.println(totalMinute/60 + " " + totalMinute%60);
    }
}