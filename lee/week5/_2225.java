package lee.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.09.27
 * 14890 합분해
 */
public class _2225 {

    static int n,k;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int dp[][] = new int[n+1][k+1]; // n을 , k 개로

        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
            dp[i][2] = i+1;
        }
        for (int i = 0; i <= k; i++) {
            dp[0][i] = 1;
            dp[1][i] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 3; j <= k; j++) {
                for (int l = 0; l <= n; l++) {
                    dp[i][j] += dp[l][j-1];
                }
            }
        }
    }
}

