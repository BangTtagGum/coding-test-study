package lee.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.10.17 14502 연구소
 */
public class _14502 {

    static int[][] map;
    static int n, m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean visited[][];
    static int maxVal = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pick(-1, 0);

        System.out.println(maxVal);

    }


    static void pick(int idx, int cnt) {
        if (cnt == 3) {
            int[][] copyMap = copyMap(map);
            init();
            checkVirus(copyMap);
            maxVal = Math.max(maxVal,countSafetyRoom(copyMap));
            return;
        }
        for (int i = idx + 1; i < n * m; i++) {
            if (map[i / m][i % m] == 0) {
                map[i/m][i%m] = 1;
                pick(i, cnt + 1);
                map[i / m][i % m] = 0;
            }
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private static void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }
    }

    static void checkVirus(int[][] copyMap) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 2) {
                    spreadVirus(j, i,copyMap);
                }
            }
        }
    }

    static void spreadVirus(int x, int y, int[][] copyMap) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[y][x] = true;
        while (!q.isEmpty()) {
            Point virus = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];
                if (isValid(nx, ny) && !visited[ny][nx] && copyMap[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    copyMap[ny][nx] = 2;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    public static int countSafetyRoom(int[][] copyMap) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    static public boolean isValid(int x, int y) {
        if (0 <= x && x < m && 0 <= y && y < n) {
            return true;
        }
        return false;
    }

    static class Point{

        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void displayMap(int[][] copyMap) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(copyMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
