package lee.week11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.11.30
 * 탈출
 */
public class _3055 {

    public static char[][] map;
    public static int r;
    public static int c;
    public static boolean[][] visited;
    public static int dx[] = {1,0,-1,0};
    public static int dy[] = {0,1,0,-1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];

        Queue<Dochi> q = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (s.charAt(j) == 'S') {
                    q.add(new Dochi(j, i));
                    visited[i][j] = true;
                }

            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            time++;

            //물이 먼저 퍼짐
            floods();


            int len = q.size();
            for (int i = 0; i < len; i++) {
                //한칸씩 나아가는 도치의 모험
                Dochi dochi = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = dochi.x + dx[j];
                    int ny = dochi.y + dy[j];
                    //무사히 비버네 집 도착
                    if (isValid(nx, ny)) {
                        if (map[ny][nx] == 'D') {
                            System.out.println(time);
                            return;
                        }
                        if (!visited[ny][nx] && map[ny][nx] == '.') {
                            q.add(new Dochi(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }

        System.out.println("KAKTUS");
        br.close();
    }

    // 물 퍼짐
    public static void floods() {
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '*') {
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (isValid(nx, ny)) {
                            // 물이 퍼질 수 있는 공간일 경우
                            if (map[ny][nx] == '.') {
                                q.add(new Point(nx, ny));
                            }
                        }
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            Point point = q.poll();
            map[point.y][point.x] = '*';
        }
    }

    public static boolean isValid(int x, int y) {
        if (0 <= x && x < c && 0 <= y && y < r) {
            return true;
        }
        return false;
    }

    static class Dochi {
        int x;
        int y;

        public Dochi(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
