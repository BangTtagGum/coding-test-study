package lee.week5;


import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.09.19
 * 1726 로봇
 */
public class _1726 {

    static int map[][];
    static int m,n;
    static int end_r;
    static int end_c;
    static int end_dir;
    static Robot startRobot;
    static int[] dx = {1,-1,0,0}; // 동서남북
    static int[] dy = {0,0,1,-1};
    static boolean visited[][][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m+1][n+1];
        visited = new boolean[m+1][n+1][5];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int start_r = Integer.parseInt(st.nextToken());
        int start_c = Integer.parseInt(st.nextToken());
        int start_dir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        end_r = Integer.parseInt(st.nextToken());
        end_c = Integer.parseInt(st.nextToken());
        end_dir = Integer.parseInt(st.nextToken());

        startRobot = new Robot(start_r, start_c, start_dir,0);

        bfs();
    }

    static void bfs() {
        Queue<Robot> q = new LinkedList<>();
        visited[startRobot.r][startRobot.c][startRobot.dir] = true;
        q.add(startRobot);
        while (!q.isEmpty()) {
            Robot r = q.poll();
            if (r.isFinish()) {
                System.out.println(r.cnt);
                return;
            }
            for (int i = 1; i <= 3; i++) { //지금 방향으로 1.2.3칸
                int nx = r.r + (dy[r.dir - 1] * i);
                int ny = r.c + (dx[r.dir - 1] * i);

                if (nx <= 0 || ny <= 0 || nx > m || ny > n) { // 범위를 나가면 안된다.
                    break;
                }
                if (map[nx][ny] == 0) { // nx, ny가 갈수있고, 방문하지 않았을 경우
                    if (!visited[nx][ny][r.dir]) {
                        visited[nx][ny][r.dir] = true;
                        q.add(new Robot(nx, ny, r.dir, r.cnt + 1));
                    }
                } else { // 벽이있으면 이후 2, 3칸도 가서는 안된다.
                    break;
                }
            }

            for (int i = 1; i <= 4; i++) { // 방향 돌리는 경우
                if (r.dir != i && !visited[r.r][r.c][i]) { // 현재 보고있는 방향이 아니고, 이미 해당위치에서 쳐다봤던 뱡항이 아닐경우
                    int turn = 1; // 돈 횟수
                    switch (r.dir) {
                        case 1:
                            if (i == 2) {
                                turn++;
                            }
                            break;
                        case 2:
                            if (i == 1) {
                                turn++;
                            }
                            break;
                        case 3:
                            if (i == 4) {
                                turn++;
                            }
                            break;
                        case 4:
                            if (i == 3) {
                                turn++;
                            }
                            break;
                    }

                    visited[r.r][r.c][i] = true;
                    q.add(new Robot(r.r, r.c, i, r.cnt + turn));
                }

            }


        }
    }



    static class Robot{
        int r;
        int c;
        int dir;
        int cnt;

        public Robot(int start_r, int start_c, int start_dir,int cnt) {
            this.r = start_r;
            this.c = start_c;
            this.dir = start_dir;
            this.cnt = cnt;
        }
        public void go(int k) {

        }
        public void turn(int dir) { //1은 왼쪽 2는 오른쪽

        }

        public boolean isFinish() {
            if (this.r == end_r && this.c == end_c && this.dir == end_dir) {
                return true;
            }
            return false;
        }
    }

}

