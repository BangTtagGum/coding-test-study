package lee.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.08.31
 * 9461 파도반 수열
 */
public class _9461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        Long dp[] = new Long[101];
        dp[1] = 1L;
        dp[2] = 1L;
        dp[3] = 1L;
        dp[4] = 2L;
        dp[5] = 2L;
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
