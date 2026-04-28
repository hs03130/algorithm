import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] numCnt;
    static int num;

    public static void main(String[] args) throws Exception {
        num = Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine());

        numCnt = new int[10];
        while (num > 0) {
            numCnt[num % 10]++;
            num /= 10;
        }

        for (int idx = 0; idx < 10; idx++) {
            System.out.println(numCnt[idx]);
        }
    }
}