package ju.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 봄버맨
 *
 * 메모리: 109120KB
 * 시간: 364ms
 */
public class _16918 {
    static int r, c;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<int[]> q = new LinkedList<>();
    static char[][] maps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        maps = new char[r][c];

        for (int i = 0; i < r; i++) {
            String lines = br.readLine();
            for (int j = 0; j < c; j++) {
                maps[i][j] = lines.charAt(j);
            }
        }

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 1) {
                for (int j = 0; j < r; j++) {
                    for (int k = 0; k < c; k++) {
                        if (maps[j][k] == 'O') {
                            q.offer(new int[]{j, k});
                        }
                    }
                }
                for (char[] map : maps) {
                    Arrays.fill(map, 'O');
                }
                bfs();
            }
        }

        if (n % 2 == 0) {
            for (char[] map : maps) {
                Arrays.fill(map, 'O');
            }
        }

        print(maps);
    }

    private static void bfs() {

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            maps[x][y] = '.';

            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                if (posX < 0 || posX >= r || posY < 0 || posY >= c || maps[posX][posY] == '.') continue;

                maps[posX][posY] = '.';
            }
        }
    }

    private static void print(char[][] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(result[i][j]);
            }
            System.out.println(sb);
            sb.setLength(0);
        }
    }
}
