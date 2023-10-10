package ju.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사다리 조작
 *
 * GG 못풀겠음
 */
public class _15684_not {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   //세로선 개수
        int m = Integer.parseInt(st.nextToken());   //놓여져있는 가로선 개수
        int h = Integer.parseInt(st.nextToken());   //가로선 개수

        int[][] ladder = new int[n][h];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 1;
            ladder[a][b+1] = 2;
        }
    }
}
