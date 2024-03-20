package lee.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2011_2 {

    /**
     * 25114 -> 2511 4 + 251 14
     * dp[i] = dp[i - 1] + dp[i - 2]
     *
     * i-2의 수가 26을 넘어가는 경우
     * 25127 -> 2512 7 + 251 27(X)
     * dp[i] = dp[i - 1]
     *
     * 0처리
     * 0앞에 1, 2가 오는 경우
     * 25110 -> 251 10
     * dp[i] = dp[i - 2]
     *
     * 다른수가 앞에오면 암호가 성립되지 않음
     * dp[i] = 0
     *
     * 0뒤에 숫자가 오는경우
     *
     * 25104 -> 2510 4 + 251 04(X)
     * dp[i] = dp[i - 1]
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int dp[] = new int[input.length()+1];
        if (input.charAt(0) != '0') {
            dp[0] = 1; // X
            dp[1] = 1; // 1
            for (int i = 2; i <= input.length(); i++) {
                if (input.charAt(i - 1) == '0') {
                    if (input.charAt(i - 2) == '1' || input.charAt(i - 2) == '2') {
                        dp[i] = dp[i - 2] % 1000000;
                    } else {
                        break;
                    }
                } else {
                    int tmpNum = Integer.parseInt(input.substring(i - 2, i));
                    if (10 <= tmpNum && tmpNum <= 26) {
                        dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
                    } else { // 0이 앞에 있는 경우
                        dp[i] = dp[i - 1] % 1000000;
                    }
                }
            }
        }
        System.out.println(dp[input.length()]);


    }

}
