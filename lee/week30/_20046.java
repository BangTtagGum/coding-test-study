package lee.week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _20046 {

    static StringTokenizer st;

    static int n, m;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        if (map[0][0] != -1) {
            visited[0][0] = true;
            pq.add(new int[]{0, 0, map[0][0]});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int cost = cur[2];

            if(r == n-1 && c == m-1) {
                System.out.println(cost);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isValid(nr, nc, map, visited)) {
                    visited[nr][nc] = true;
                    pq.add(new int[]{nr, nc, cost + map[nr][nc]});
                }
            }
        }

        System.out.println(-1);

    }

    public static boolean isValid(int r, int c, int[][] map, boolean[][] visited) {
        return 0 <= r && r < n && 0 <= c && c < m && !visited[r][c] && map[r][c] != -1;
    }
}
