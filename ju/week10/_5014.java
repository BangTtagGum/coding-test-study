package ju.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스타트링크
 *
 * 풀이
 * 1차: 그리디 나누기
 * 반례: 6 1 6 3 2
 * 층수를 올라가고 내려가고 다시 올라가는 경우도 따져야함
 *
 * 2차:
 */
public class _5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int result = 0;
        //목표 층수 계산
        int goal = Math.abs(s - g);
        if (s < g) {
            //가야하는 층수가 더 높은데 U버튼이 0
            if (u == 0) {
                System.out.println("use the stairs");
                System.exit(0);
            }
            result += goal / u;
            int remain = goal % u;
            while (remain % u != 0) {
                result++;
                remain += d;
                if (f <= remain) {
                    System.out.println("use the stairs");
                    System.exit(0);
                }
            }
            result += remain / u;
        } else if (s > g) {
            if (d == 0) {
                //가야하는 층수가 더 낮은데 D버튼이 0
                System.out.println("use the stairs");
                System.exit(0);
            }
            result += goal / d;
            int remain = goal % d;
            while (remain % d != 0) {
                result++;
                remain += u;
                if (f <= remain) {
                    System.out.println("use the stairs");
                    System.exit(0);
                }
            }
            result += remain / d;
        } else {
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(result);
    }
}
