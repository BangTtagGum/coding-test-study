package lee.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.08.31
 * 2133 타일 채우기
 */
public class _2133 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        int dp[] = new int[31];
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        dp[3] = 0;
        dp[4] = 11;
        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i - 2] * 3; // 남은 가로 두칸을 채우는 방법은 3가지
            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2; // 남은 4칸 채우는 방법 2가지, 6칸도 2가지 8칸도 ...마지막 딱 i칸 맞춰 채우는 경우의 수 2가지
            }
        }
        System.out.println(dp[n]);
    }
}
