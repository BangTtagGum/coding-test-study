package ju.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전2
 *
 * case1: 동전이 1,5,12 일때 15의 최솟값
 * 15가 되기위한 최소동전의 수 는 3개(5,5,5)
 *
 * dp[1] dp[2] dp[3] dp[4] dp[5] dp[6] dp[7] dp[8] dp[9] dp[10] dp[11] dp[12] dp[13] dp[14] dp[15]
 *   1    2      3      4   1       2   3      4    5       2      3      1     2      3      3     -> 동전이 1,5,12
 *   1    2      3      4   1       2   3      4    5       2      3      4     5      6      3     -> 동전이 1,5
 *   1    2      3      4   5       6   7      8    9       10     11     12    13     14     15    -> 동전이 1
 *
 *
 *  case2: 동전이 2,3,5 일때 10의 최솟값
 * dp[1] dp[2] dp[3] dp[4] dp[5] dp[6] dp[7] dp[8] dp[9] dp[10]
 *   -1   1      1      2   1       2   2      2    3       2       -> 동전이 2,3,5
 *   -1   1      1      2   2       2   3      3    3       4       -> 동전이 2,3
 *   -1   1     -1      2   -1      3   -1     4    -1      5       -> 동전이 2
 *
 *  case3: 동전이 1, 5 일때 14의 최솟값
 * dp[1] dp[2] dp[3] dp[4] dp[5] dp[6] dp[7] dp[8] dp[9] dp[10] dp[11] dp[12] dp[13] dp[14]
 *   1    2      3      4   1       2   3      4    5       2      3      4     5      6    -> 동전이 1,5
 *   1    2      3      4   5       6   7      8    9       10     11     12    13     14   -> 동전이 1
 *
 *
 *   공통점은 다음 코인이 추가 되기전까진 dp[i]의 값이 같음 -> coin[i]값부터 값을 갱신하면댐
 *   dp[5] = dp[0] + 1 = 1
 *   dp[10] = dp[5] + 1 = 2
 *   dp[12] = dp[0] + 1 = 1
 *   dp[15] = dp[10] + 1 = 3
 *   규칙 : dp[j-coin[i]] + 1 인데 dp[15]의 경우 이전 dp[15]의 최솟값보다 낮음 -> 이전 최솟값과 비교해서 더 작은 값
 *   문제: dp[i]가 0인 경우(합계를 구할 수 없는 경우) 에는 무조건 1로 덮어씌어짐 -> dp초기화를 최댓값으로 시작 후 작은 값 덮기
 *
 */
public class _2294 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        int[] dp = new int[k+1];

        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());

            for (int j = coins[i]; j <= k; j++) {
                int temp = dp[j - coins[i]] + 1;
                dp[j] = dp[j] > temp ? temp : dp[j];
            }
        }

        System.out.println(dp[k] == Integer.MAX_VALUE - 1 ? -1 : dp[k]);
    }
}
