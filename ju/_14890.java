package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 경사로
 *
 * GG 못풀겠음
 */
public class _14890 {
    static int n;
    static int cnt = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로 경사로 체크
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            for (int j = 1; j < n; j++) {
                int temp = map[i][0] - map[i][j];
                //높이가 2이상 차이나면
                if (Math.abs(temp) > 1) break;
                //높이가 같다면 계속
                if (temp == 0) continue;

                //아래에서 위로 이동
                if (temp == -1){

                }
                //위에서 아래로 이동
                else {

                }
            }
        }

        System.out.println(cnt);
    }
}
