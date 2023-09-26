package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 합분배
 *
 * 20 2
 * -> 21
 *
 * 0 ~ 20개 까지의 숫자 중 2개를 더해서 20이 나오는 경우
 * 0 20
 * 20 0
 * 1 19
 * 19 1
 * ...
 * 9 11
 * 11 9
 * 10 10 -> 21개
 *
 * 6 4
 * -> 84
 * dp
 * dp[0] = 0
 * dp[1] = 1
 * dp[2] = dp[1] + n = 7
 * dp[3] = 28
 * dp[4] = 84
 * 0 0 6
 * 0 1 5
 * 0 2 4
 * 0 3 3
 * 0 4 2
 * 0 5 1
 * 0 6 0 -> 합이 6인(n = 6)
 *
 * 1 0 5
 * 1 1 4
 * 1 2 3
 * 1 3 2
 * 1 4 1
 * 1 5 0 -> 합이 5인(n = 5)
 *
 * 2 0 4
 * 2 1 3
 * 2 2 2
 * 2 3 1
 * 2 4 0 -> 합이 4인(n = 4)
 *
 * 3 0 3
 * 3 1 2
 * 3 2 1
 * 3 3 0 -> 합이 3인(n = 3)
 *
 * 4 0 2
 * 4 1 1
 * 4 2 0 -> 합이 2인(n = 2)
 *
 * 5 0 1
 * 5 1 0 -> 합이 1인(n = 1)
 *
 * 6 0 0 -> 합이 0인(n = 0)
 */

/**
 * 결론
 * n = 6 이고 k = 4일때
 *
 * 합의 수는 n이 0,1,2,3,4,5,6일때의 dp[k-1]의 합
 * dp[6][4] = dp[0][3] + dp[1][3] + dp[2][3] + dp[3][3] + ... + dp[6][3]
 *
 * k = 1이면 무조건 1
 * n = 0이면 무조건 1
 *
 * dp[0][2] = 1
 * dp[1][2] = 2
 * dp[2][2] = 3
 * dp[3][2] = 4
 *
 *
 * dp[0][3] = 1
 * dp[1][3] = dp[0][2] + dp[1][2] = 3
 * dp[2][3] = dp[0][2] + dp[1][2] + dp[2][2] = 6
 * dp[3][3] = dp[0][2] + dp[1][2] + dp[2][2] + dp[3][2] = 10
 * dp[4][3] = dp[0][2] + dp[1][2] + dp[2][2] + dp[3][2] + dp[4][2] = 15
 * dp[5][3] = dp[0][2] + dp[1][2] + dp[2][2] + dp[3][2] + dp[4][2] + dp[5][2] = 21
 * dp[6][3] = dp[0][2] + dp[1][2] + dp[2][2] + dp[3][2] + dp[4][2] + dp[5][2] + dp[6][2] = 28
 *
 * dp[6][4] = 1 + 3 + 6 + 10 + 15 + 21 + 28 = 84
 *
 */
public class _2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[201][201];
        //dp[a][2], dp[a][1] = a+1로 초기화
        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
            dp[i][2] = i+1;
        }
        //dp[0][a] = 1, dp[1][a] = a로 초기화
        for (int i = 0; i <= k; i++) {
            dp[0][i] = 1;
            dp[1][i] = i;
        }

        //합이 n인 정수 k개의 조합 = dp[i][k]
        //ex) dp[6][4] = dp[0][3] + dp[1][3] + ... + dp[6][3]
        for (int i = 2; i <= n; i++) {
            for (int j = 3; j <= k; j++) {
                int sum = 0;
                for (int l = 0; l <= i; l++) {
                    sum += dp[l][j-1];
                    sum = sum % 1000000000;
                }
                dp[i][j] = sum;
            }
        }

        System.out.println(dp[n][k]);
    }
}
