package lee.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.11.24 내리막 길
 * 첫번째 풀이 - 그냥 백트래킹 (시간초과)
 * 두번째 풀이 - 사방이 자신보다 높은 공간은 체크 후 제외하고 백트래킹 (시간초과)
 * 세번째 풀이 -
 * 방문한 위치 사방으로 도착 가능한지 아닌지 체크 후 반복 방문 방지 (시간초과)
 * 네번째 풀이 - DP
 */
public class _1520 {


    static int m;
    static int n;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }


        System.out.println(start());


    }

    public static int start() {
        return lookAround(0, 0);
    }


    // 주변 탐색 후 이동 여부 지시
    public static int lookAround(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }
        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 높이가 더 낮은 경우
            if (isValid(nx, ny)) {
                if (map[ny][nx] < map[y][x]) {
                    dp[y][x] += lookAround(nx,ny);
                }
            }
        }
        return dp[y][x];
    }

    // 유효 범위 이내인지 판단
    public static boolean isValid(int x, int y) {
        if (0 <= x && x < n && 0 <= y && y < m) {
            return true;
        }
        return false;
    }

    // 세번째 풀이
    /*
     static int m;
    static int n;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
//    static int[][] dp;
    static boolean[][][] inValid; //
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
//        dp = new int[m][n];
        inValid = new boolean[m][n][4];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        start();

        System.out.println(answer);


    }

    public static void start() {
        lookAround(0,0);
    }


    // 주변 탐색 후 이동 여부 지시
    public static boolean lookAround(int x, int y) {
        boolean valid = false; // 현재 위치에서 정답에 접근할 수 있는지 아닌지 체크
        for (int i = 0; i < 4; i++) {
            // 높이가 더 낮은 경우
            if (isValid(x, y, i)) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx == n - 1 && ny == m - 1) {
                    answer++;
                    valid = true;
                } else {
                    if (!lookAround(nx, ny)) {
                        inValid[y][x][i] = true;
                    } else { // 정답에 접근하지 못했을 경우 체크
                        valid = true;
                    }
                }
            } else { // 이동할 수 없는 방향 체크
                inValid[y][x][i] = true;
            }
        }
        return valid;
    }

    // 유효 범위 이내인지 판단
    public static boolean isValid(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (!inValid[y][x][dir] && 0 <= nx && nx < n && 0 <= ny && ny < m) {
            if (map[ny][nx] < map[y][x]) {
                return true;
            }
        }
        return false;
    }

    public static void printMap() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
     */
}
