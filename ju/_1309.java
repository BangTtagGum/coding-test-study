package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 동물원
 *
 * 2 * 3 의 우리일 경우
 * 0마리 배치 -> 1가지
 * 1마리 배치 -> 6가지
 * 2마리 배치 -> 3 * 2 + 2 = 8가지
 * 3마리 배치 -> 2가지
 * -> 17가지
 *
 * 2 * 4 의 우리일 경우
 * 0마리 배치 -> 1가지  ->  무조건 1가지
 * 1마리 배치 -> 8가지  ->  무조건 2*n가지
 * 2마리 배치 -> 5 * 2 + 8 = 18가지
 * 3마리 배치 -> (3 + 1 + 1) * 2 + 2 = 12가지
 * 4마리 배치 -> 1 + 1 = 2가지   -> 무조건 2가지
 * -> 41가지
 *
 *
 * 2 * 5 의 우리일 경우
 * 0마리 배치 -> 1가지
 * 1마리 배치 -> 10가지
 * 2마리 배치 -> (7 * 2) + 18 = 32가지
 * 3마리 배치 -> (5 + 3 + 3 + 1 + 1) * 2 + 12 = 38가지
 * 4마리 배치 -> (7 * 2) + 2 = 16가지
 * 5마리 배치 -> 2가지
 * -> 99가지
 *
 * dp[n][0] -> n번째 줄에 한마리도 안넣을 때 dp[n-1][0] + dp[n-1][1] + dp[n-1][2]  -> 이전 dp의 모든 경우의 수
 * dp[n][1] -> n번째 줄의 1번째칸에 넣는 경우: dp[n-1][0] + dp[n-1][2]
 * dp[n][2] -> n번째 줄의 2번째칸에 넣는 경우: dp[n-1][0] + dp[n-1][1]
 */
public class _1309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][3];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
            dp[i][1] = dp[i-1][0] + dp[i-1][2];
            dp[i][2] = dp[i-1][0] + dp[i-1][1];

            dp[i][0] = dp[i][0] % 9901;
            dp[i][1] = dp[i][1] % 9901;
            dp[i][2] = dp[i][2] % 9901;
        }

        int sum = dp[n][0] + dp[n][1] + dp[n][2];
        System.out.println(sum % 9901);
    }
}
