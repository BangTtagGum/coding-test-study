package lee.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 2023.09.19
 * 1309 동물원
 */
public class _1309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 0은 비었은 경우 1은 왼쪽 2는 오른쪽에 사자가 있는 경우의 수
        int[][] dp = new int [100001][3];

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        for (int i = 2; i < 100001; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }
        System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);
    }
}
