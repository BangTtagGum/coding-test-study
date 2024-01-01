package ju.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로 탈출
 *
 * 메모리: 106608KB
 * 시간: 652ms
 *
 * 지팡이를 사용한 경우와 사용하지않은 경우를 3차원배열을 활용해서 방문체크해야함
 */
public class _14923 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] maps;
    static int n, m, ex, ey;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken());
        int hy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        maps = new int[n+1][m+1];
        visited = new boolean[2][n+1][m+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        q.offer(new int[]{hx, hy, 0, 0});
        visited[0][hx][hy] = true;
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int cane = poll[2];
            int dist = poll[3];

            if (x == ex && y == ey) return dist;

            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];
                int caneCheck = cane;

                if (posX <= 0 || posX > n || posY <= 0 || posY > m || visited[cane][posX][posY]) continue;

                //지팡이 사용한 경우 벽을 못 뚫음
                if (maps[posX][posY] == 1) {
                    if (caneCheck == 1) continue;

                    else caneCheck = 1;
                }

                visited[caneCheck][posX][posY] = true;
                q.offer(new int[]{posX, posY, caneCheck, dist+1});
            }
        }

        return -1;
    }
}
