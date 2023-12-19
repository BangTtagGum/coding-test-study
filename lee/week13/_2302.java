package lee.week13;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _2302 {

    public static int[] dp;
    public static int answer = 1;
    public static int n;
    public static int[] vip;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int m = sc.nextInt();

        vip = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            int num = sc.nextInt();
            vip[i] = num;
        }

        dp = new int[n+2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        for (int i = 1; i <= m; i++) {
            answer *= dp[vip[i] - vip[i - 1] - 1];
        }
        answer *= dp[n - vip[m]];

        System.out.println(answer);
    }
}
