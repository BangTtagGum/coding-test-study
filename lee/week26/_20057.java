package lee.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20057 {

    static int n;
    static int totalSand;
    static int[][] sand;

    static StringTokenizer st;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    static int[][] sandr = {
            {-2, -1, -1, -1, 0, 1, 1, 1, 2}, // 왼
            {0, 1, 0, -1, 2, 1, 0, -1, 0},   // 아래
            {-2, -1, -1, -1, 0, 1, 1, 1, 2}, // 오
            {0, -1, 0, 1, -2, -1, 0, 1, 0}  // 위
    };

    static int[][] sandc = {
            {0, -1, 0, 1, -2, -1, 0, 1, 0}, // 왼
            {-2, -1, -1, -1, 0, 1, 1, 1, 2}, // 아래
            {0, 1, 0, -1, 2, 1, 0, -1, 0},   // 오
            {-2, -1, -1, -1, 0, 1, 1, 1, 2}  // 위
    };
    static int[] sandPercentage = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        sand = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
                totalSand += sand[i][j];
            }
        }

        tornado(n);

        System.out.println(getLostSand());


    }

    static void tornado(int n) {
        int r = n / 2;
        int c = n / 2;
        int dir = 0; // 왼쪽을 보고 시작
        int distance = 1; // 한칸 부터 시작
        int moveCnt = 0; // 현재 거리로 움직인 횟수

        while (moveCnt < 3) {
            for (int i = 0; i < distance; i++) {
                r += dr[dir];
                c += dc[dir];
                spreadSand(r, c, dir);
            }

            dir = (dir + 1) % 4;
            moveCnt++;

            if (moveCnt == 2) {
                distance++;
                if (distance == n) {
                    distance--;
                    continue;
                }
                moveCnt = 0;

            }
        }


    }

    static void spreadSand(int r, int c, int dir) {
        int flyingSandTotal = 0;
        for (int i = 0; i < 9; i++) {
            int nr = r + sandr[dir][i];
            int nc = c + sandc[dir][i];
            int flyingSand = (sandPercentage[i] * sand[r][c]) / 100;
            flyingSandTotal += flyingSand;
            if (isValid(nr, nc)) {
                sand[nr][nc] += flyingSand;
            }
        }
        // 나머지 모래 알파 구간으로 보내기
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        if (isValid(nr, nc)) {
            sand[nr][nc] += sand[r][c] - flyingSandTotal;
        }
        sand[r][c] = 0;
    }

    static int getLostSand() {
        int currentSand = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                currentSand += sand[i][j];
            }
        }
        return totalSand - currentSand;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }


}