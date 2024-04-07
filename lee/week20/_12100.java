package lee.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _12100 {

    static int[][] map;
    static int n;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cycle(0);
        System.out.println(maxValue);
    }

    public static void cycle(int cnt) {
        if (cnt == 5) {
            getMaxValue();
            return;
        }

        // 상 하 좌 우
        for (int i = 0; i < 4; i++) {
            int[][] copyMap = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    copyMap[j][k] = map[j][k];
                }
            }

            //실제 판 움직이기
            move(i);
            // 다음 케이스
            cycle(cnt + 1);

            // 판 되돌리기
            map = copyMap;
        }
    }

    public static void move(int dir) {
        switch (dir) {
            case 0:
                tiltUp();
                break;
            case 1:
                tiltLeft();
                break;
            case 2:
                tiltDown();
                break;
            case 3:
                tiltRight();
                break;
        }
    }

    private static void tiltLeft() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    q.add(map[i][j]);
                }
            }
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int num = q.poll();
                // 합칠 수 있는 경우
                if (!q.isEmpty() && num == q.peek()) {
                    q.poll();
                    map[i][j] = num * 2;
                    size--;
                } else {
                    map[i][j] = num;
                }
            }
            for (int j = size; j < n; j++) {
                map[i][j] = 0;
            }
        }
    }

    private static void tiltRight() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    q.add(map[i][j]);
                }
            }

            int size = q.size();
            for (int j = n - 1; j >= n - size; j--) {
                int num = q.poll();
                // 합칠 수 있는 경우
                if (!q.isEmpty() && num == q.peek()) {
                    q.poll();
                    map[i][j] = num * 2;
                    size--;
                } else {
                    map[i][j] = num;
                }
            }
            for (int j = n - 1 - size; j >= 0; j--) {
                map[i][j] = 0;
            }
        }
    }

    private static void tiltUp() {
        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (map[i][j] != 0) {
                    q.add(map[i][j]);
                }
            }
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int num = q.poll();
                // 합칠 수 있는 경우
                if (!q.isEmpty() && num == q.peek()) {
                    q.poll();
                    map[i][j] = num * 2;
                    size--;
                } else {
                    map[i][j] = num;
                }
            }
            for (int i = size; i < n; i++) {
                map[i][j] = 0;
            }
        }
    }

    private static void tiltDown() {
        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    q.add(map[i][j]);
                }
            }

            int size = q.size();
            for (int i = n - 1; i >= n - size; i--) {
                int num = q.poll();
                // 합칠 수 있는 경우
                if (!q.isEmpty() && num == q.peek()) {
                    q.poll();
                    map[i][j] = num * 2;
                    size--;
                } else {
                    map[i][j] = num;
                }
            }
            for (int i = n - 1 - size; i >= 0; i--) {
                map[i][j] = 0;
            }
        }
    }

    public static void getMaxValue() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maxValue < map[i][j]) {
                    maxValue = map[i][j];
                }
            }
        }
    }
}