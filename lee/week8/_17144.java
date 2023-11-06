package lee.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2023.11.05
 * 미세먼지 안녕!
 */
public class _17144 {

    static int[][] map;
    static int cleanX;
    static int cleanY;
    static int r;
    static int c;
    static int t;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r =Integer.parseInt(st.nextToken());
        c =Integer.parseInt(st.nextToken());
        t =Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    cleanX = j;
                    cleanY = i;
                }
                map[i][j] = num;
            }
        }

        for (int i = 0; i < t; i++) {
            spread();
            wind();
        }

        System.out.println(sumDust());





    }


    static void spread() {
        int[][] copyMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    int val = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (isValid(nx, ny) && map[ny][nx] != -1) {
                            copyMap[ny][nx] += map[i][j] / 5;
                            val += map[i][j] / 5;
                        }
                    }
                    copyMap[i][j] -= val;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += copyMap[i][j];
            }
        }
    }

    static void wind() {

        int[][] dir = {{0, 1, 2, 3}, {0, 3, 2, 1}};


        //n = 0 아래 순환, n = 1 위 순환
        for (int n = 0; n <= 1; n++) {
            int x = cleanX;
            int y = cleanY - n; //아래 위 구분

            int save = 0;
            int before = 0;
            for (int i : dir[n]) {
                x += dx[i];
                y += dy[i];
                while (isValid(x, y)) {
                    if (x == cleanX && y == cleanY - n) {
                        break;
                    }
                    save = map[y][x];
                    map[y][x] = before;
                    before = save;
                    x += dx[i];
                    y += dy[i];
                }
                x -= dx[i];
                y -= dy[i];
            }
        }

    }

    static boolean isValid(int x, int y) {
        if (0 <= x && x < c && 0 <= y && y < r) {
            return true;
        }
        return false;
    }

    static int sumDust() {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }
    static void printMap() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

}
