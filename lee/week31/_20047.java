package lee.week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20047 {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[] s = new char[10002];
        char[] t = new char[10002];
        char[] s2 = new char[10002];

        String input = br.readLine();
        String target = br.readLine();

        for (int i = 0; i < n; i++) {
            s[i + 1] = input.charAt(i);
            t[i + 1] = target.charAt(i);
        }

        st = new StringTokenizer(br.readLine());

        int fir = Integer.parseInt(st.nextToken()) + 1;
        int sec = Integer.parseInt(st.nextToken()) + 1;

        int idx = 1;
        for (int i = 1; i <= n; i++) {
            if (i == fir || i == sec) {
                continue;
            }
            s2[idx++] = s[i];
        }

        /**
         *  0은 손가락 하나도 사용 x
         *  1은 왼쪽 손가락만 사용
         *  2는 왼쪽, 오른쪽 손가락 사용
         */
        boolean[][] dp = new boolean[n + 1][3];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && t[i] == s2[i];
            dp[i][1] = dp[i - 1][1] && t[i] == s2[i - 1] || dp[i - 1][0] && t[i] == s[fir];
            dp[i][2] = dp[i - 1][2] && t[i] == s2[i - 2] || dp[i - 1][1] && t[i] == s[sec];
        }
        System.out.println(dp[n][2] ? "YES" : "NO");

    }
}
