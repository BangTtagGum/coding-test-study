package lee.week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20173 {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());


        // 0: 하나만 잘못 비교 (r1,r2 상관x)  -> 다음 d 계산에 영향을 미침
        // 1: 둘다 잘 비교 or 둘다 잘못 비교    -> 다음 d 계산에 영향을 미치지 않음
        int dp = 1;

        int sequence = -1;
        for (int i = 1; i < n; i++) {
            sequence = Integer.parseInt(st.nextToken());
            if (dp == 1) {
                if (sequence == 0) {
                    dp = 1;
                } else if (sequence == 1) {
                    dp = 0;
                } else {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (sequence == 0 || sequence == 2) {
                    dp = 0;
                } else if (sequence == 1) {
                    dp = 1;
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }

        sequence = Integer.parseInt(st.nextToken());
        if (dp == 1 && sequence == 0 || dp == 0 && sequence == 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
