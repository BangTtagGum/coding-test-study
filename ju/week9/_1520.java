package ju.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 내리막 길
 *
 * 메모리: 34460KB
 * 시간: 396ms
 *
 * 일반적인 bfs, dfs로 하면 시간초과, (a,b) -> (m,n) 까지 도달하는 경우의 수를 dp 좌표에 백트래킹으로 저장하면 dp[0][0] -> dp[m][n]의 경우의 수가 나옴
 */
public class _1520 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] maps;
    static int m, n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        maps = new int[m][n];
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    private static int dfs(int x, int y) {

        //목표 도착
        if (x == m-1 && y == n-1) {
            return 1;
        }

        //첫 방문이 아닌경우
        if (dp[x][y] != -1) {
            return dp[x][y];
        } else {
            //방문 처리
            dp[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                if (posX < 0 || posX >= m || posY < 0 || posY >= n) continue;

                if (maps[posX][posY] < maps[x][y]) dp[x][y] += dfs(posX, posY);
            }
        }

        return dp[x][y];
    }
}
