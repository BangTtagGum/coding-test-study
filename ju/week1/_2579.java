package ju.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stairCount = Integer.parseInt(br.readLine());
        int[] stair = new int[stairCount];

        for (int i = 0; i < stairCount; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        if (stairCount < 3) {
            int result = 0;
            for (int i: stair) {
                result += i;
            }
            System.out.println(result);
            return;
        }

        int[] dp = new int[stairCount];
        dp[0] = stair[0];
        dp[1] = stair[0] + stair[1];
        dp[2] = compareToMax(stair[0] + stair[2], stair[1] + stair[2]);

        for (int i = 3; i < stairCount; i++) {
            dp[i] = compareToMax(dp[i - 3] + stair[i-1] + stair[i], dp[i - 2] + stair[i]);
        }

        System.out.println(dp[stairCount-1]);
    }

    private static int compareToMax(int a, int b) {
        return a > b ? a : b;
    }
}
