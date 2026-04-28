import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int num;

    public static void main(String[] args) throws Exception {
        num = Integer.parseInt(br.readLine());

        for (int row = 0; row < num; row++) {
            for (int col = 0; col < num - row - 1; col++) {
                System.out.print(" ");
            }
            for (int col = num - row - 1; col < num; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}