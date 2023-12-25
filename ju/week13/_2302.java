package ju.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 극장 좌석
 *
 * 입력: 9 2 4 7
 *
 * 1 2 3 4 5 6 7 8 9
 *   3   *  2  *  2
 * -> 12개 = dp[3]) * dp[2] * dp[2]
 *
 * dp[0] = 1
 * dp[1] = 1
 * dp[2] = 2
 * dp[3] = 3
 * dp[4] = 5
 * dp[5] = 8
 * dp[6] = 13
 * dp[7] = 21
 * dp[8] = 34
 * dp[9] = 55
 *
 * 메모리: 14176KB
 * 시간: 120ms
 */
public class _2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }

        int result = 1;
        int seq = 0;
        for (int i = 0; i < m; i++) {
            int temp = Integer.parseInt(br.readLine());
            result *= dp[temp - seq - 1];
            seq = temp;
        }

        result *= dp[n - seq];

        System.out.println(result);
    }
}
