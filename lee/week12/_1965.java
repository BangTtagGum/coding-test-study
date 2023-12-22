package lee.week12;


import java.util.Scanner;

/**
 * 2023.12.12
 * 상자넣기
 */
public class _1965 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int dp[][] = new int[t][2];
        int max = 1;

        for (int i = 0; i < t; i++) {
            dp[i][0] = sc.nextInt();
            dp[i][1] = 1;
        }

        for (int i = 0; i < t; i++) {
            int box = 0;
            for (int j = i; j >= 0; j--) {
                if (dp[j][0] < dp[i][0]) {
                    box = Math.max(box, dp[j][1]);


                }
            }
            dp[i][1] += box;
            max = Math.max(dp[i][1], max);
        }

        System.out.println(max);

        sc.close();
    }
}
