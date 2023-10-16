package lee.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2023.10.16 11559 Puyo Puyo
 */
public class _11559 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                char c = input.charAt(j);
                PuyoGame.map[i][j] = new Puyo(j, i, c);
            }
        }

        System.out.println(PuyoGame.run());


    }

    static class PuyoGame {

        static Puyo[][] map = new Puyo[12][6];
        static boolean visited[][] = new boolean[12][6];
        static int dx[] = {0, 1, 0, -1};
        static int dy[] = {-1, 0, 1, 0};

        static void falling() {
            for (int j = 0; j < 6; j++) {
                int bottom = 0;
                for (int i = 11; i >= 0; i--) {
                    if (bottom == 0 && map[i][j].value == '.') {
                        bottom = i;
                    }
                    if (bottom != 0 && map[i][j].value != '.') {

                        map[bottom][j].value = map[i][j].value;
                        map[i][j].value = '.';
                        bottom -= 1;
                    }
                }
            }
        }

        static int run() {
            int ans = 0;
            while (check()) {
                ans++;
            }
            return ans;
        }

        private static boolean check() {
            boolean flag = false;
            Queue<Puyo> q = new LinkedList<>();
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        q.add(map[i][j]);
                        int count = 1;
                        while (!q.isEmpty()) {
                            Puyo puyo = q.poll();
                            char target = puyo.value;
                            for (int k = 0; k < 4; k++) {
                                int nx = puyo.x + dx[k];
                                int ny = puyo.y + dy[k];
                                // 연결되어 있는 뿌요가 있다면
                                if (isValid(nx, ny) && !visited[ny][nx] && target != '.'
                                        && map[ny][nx].value == target) {
                                    visited[ny][nx] = true;
                                    q.add(map[ny][nx]);
                                    count++;
                                }
                            }
                        }
                        if (count >= 4) {
                            flag = true;
                            pop();
                        } else {
                            init();
                        }
                    }
                }
            }
            falling();
            return flag;
        }

        static boolean isValid(int x, int y) {
            if (0 <= x && x <= 5 && 0 <= y && y <= 11) {
                return true;
            }
            return false;
        }

        static void pop() {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j]) {
                        visited[i][j] = false;
                        map[i][j].value = '.';
                    }
                }
            }
        }

        static void init() {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    visited[i][j] = false;
                }
            }
        }

        static void display() {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(map[i][j].value);
                }
                System.out.println();
            }
        }
    }

    static class Puyo {

        int x, y;
        char value;

        public Puyo(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
