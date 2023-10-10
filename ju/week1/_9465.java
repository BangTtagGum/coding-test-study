package ju.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3
 * j = 0  dp[0][2] (1,1) + (1,0)
 * j = 1  dp[1][2] (0,0) + (0,1)
 *
 * 4
 * j = 0  dp[0][3] (1,2) + (1,1)
 * j = 1  dp[1][3] (0,1) + (0,2)
 */
public class _9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            if (n == 1){
                System.out.println(compareToMax(sticker[0][0], sticker[1][0]));
                continue;
            }

            int[][] dp = new int[2][n];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            dp[1][1] = sticker[0][0] + sticker[1][1];
            dp[0][1] = sticker[1][0] + sticker[0][1];

            for (int j = 2; j < n; j++) {
                dp[0][j] = compareToMax(dp[1][j-2], dp[1][j-1]) + sticker[0][j];
                dp[1][j] = compareToMax(dp[0][j-2], dp[0][j-1])  + sticker[1][j];
            }

            System.out.println(compareToMax(dp[0][n-1], dp[1][n-1]));
        }
    }

    private static int compareToMax(int a, int b) {
        return a > b ? a : b;
    }
}
