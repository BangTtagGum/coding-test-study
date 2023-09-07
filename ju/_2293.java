package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전 1
 *
 * dp[1] = 1
 * dp[2] = 2
 * dp[3] = 2
 * dp[4] = 3
 * dp[5] = 4
 * dp[6] = 5
 * dp[7] = 6
 * dp[8] = 7
 * dp[9] = 8
 * dp[10] = 10
 *
 * dp[i] = j -> i는 합계 j는 경우의 수
 *
 * 이전 동전의 i원에 대한 경우의 수를 구하고 다음 동전의 i-coin의 값을 더하면 답이 나옴???
 * dp[i](현재코인) = dp[i](이전코인) + dp[i-coin]
 */
public class _2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] dp = new int[t+1];
        dp[0] = 1;
        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());

            for (int j = 1; j <= t; j++) {
                if (j - coin[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coin[i]];
                }
            }
        }

        System.out.println(dp[t]);
    }
}
