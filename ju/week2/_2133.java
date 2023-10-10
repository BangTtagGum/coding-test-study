package ju.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 타일 채우기
 *
 * n=1 : x
 * n=2 : 3
 * n=3 : x
 * n=4 : 11
 * ...
 * n=6 : 41
 * n이 홀수면 무조건 X임
 *
 * f(4) = 정상식(f(2) * (f)2) + 예외케이스(2)
 * f(6) = 정상식(f(4) * (f)2) + 예외케이스(2) + 오른쪽에 대한 예외케이스(f(2) * 2)
 * f(8) = 정상식(f(6) * (f)2) + 예외케이스(2) + f(6)의 오른쪽에 대한 예외케이스(f(4) * 2) + f(4)의 오른쪽에 대한 예외케이스(f(2) * 2)
 *
 * f(n) = f(n-2) * 3 + 2 + (f(n-4)*2)...
 */
public class _2133 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        if (n % 2 == 1) {
            System.out.println(0);
        } else {
            dp[2] = 3;
            for (int i = 4; i <= n; i+=2) {
                dp[i] = dp[i-2] * dp[2] + 2;
                for (int j = i-4; j > 0; j-=2) {
                    dp[i] += dp[j] * 2;
                }
            }

            System.out.println(dp[n]);
        }
    }
}
