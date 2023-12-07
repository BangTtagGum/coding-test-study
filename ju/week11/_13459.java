package ju.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구슬 탈출
 *
 * 빨,파 구슬의 위치를 q에 같이 넣으면서 비교해야함
 *
 * 반례
 8 8
 ########
 #BR.#.O#
 ###.#..#
 #...#..#
 #.###..#
 #..#..##
 ##...#.#
 ########

 * 10번째 시도시 체크로직이 잘못됨.
 *
 * 메모리: 16412KB
 * 시간: 160ms
 */
public class _13459 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] maps;
    static Queue<Marble> redMarble = new LinkedList<>();
    static Queue<Marble> blueMarble = new LinkedList<>();
    static boolean[][][][] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maps = new char[n][m];
        visited = new boolean[n][m][n][m];
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        for (int i = 0; i < n; i++) {
            String lines = br.readLine();
            for (int j = 0; j < m; j++) {
                maps[i][j] = lines.charAt(j);
                if (maps[i][j] == 'R') {
                    redMarble.offer(new Marble(i, j, 0));
                    rx = i;
                    ry = j;
                } else if (maps[i][j] == 'B') {
                    blueMarble.offer(new Marble(i, j, 0));
                    bx = i;
                    by = j;
                }

            }
        }

        visited[rx][ry][bx][by] = true;
        bfs();
        System.out.println(0);
    }

    private static void bfs() {
        while (!redMarble.isEmpty()) {
            Marble redPoll = redMarble.poll();
            int redX = redPoll.x;
            int redY = redPoll.y;
            int dist = redPoll.dist;
            if (dist == 10) break;

            Marble bluePoll = blueMarble.poll();
            int blueX = bluePoll.x;
            int blueY = bluePoll.y;

            for (int i = 0; i < 4; i++) {
                //빨간구슬이 먼저 움직이는지 확인
                boolean redFirst = false;
                if (i == 0) {if (redY > blueY) redFirst = true;}
                else if (i == 1) {if (redX > blueX) redFirst = true;}
                else if (i == 2) {if (redY < blueY) redFirst = true;}
                else if (i == 3) {if (redX < blueX) redFirst = true;}

                //빨간구슬 끝까지 이동
                int redPosX = redX;
                int redPosY = redY;
                while (true) {
                    redPosX += dx[i];
                    redPosY += dy[i];

                    if (maps[redPosX][redPosY] == '#') {
                        redPosX -= dx[i];
                        redPosY -= dy[i];
                        break;
                    }

                    if (maps[redPosX][redPosY] == 'O') break;
                }

                //파란구슬 끝까지 이동
                int bluePosX = blueX;
                int bluePosY = blueY;
                while (true) {
                    bluePosX += dx[i];
                    bluePosY += dy[i];

                    if (maps[bluePosX][bluePosY] == '#') {
                        bluePosX -= dx[i];
                        bluePosY -= dy[i];
                        break;
                    }

                    if (maps[bluePosX][bluePosY] == 'O') break;
                }

                if (maps[bluePosX][bluePosY] == 'O') continue;
                if (maps[redPosX][redPosY] == 'O') {
                    System.out.println(1);
                    System.exit(0);
                }

                //구슬이 같은 위치에 있음
                if (redPosX == bluePosX && redPosY == bluePosY) {
                    if (redFirst) {
                        bluePosX -= dx[i];
                        bluePosY -= dy[i];
                    } else {
                        redPosX -= dx[i];
                        redPosY -= dy[i];
                    }
                }

                if (visited[redPosX][redPosY][bluePosX][bluePosY]) continue;

                visited[redPosX][redPosY][bluePosX][bluePosY] = true;
                redMarble.offer(new Marble(redPosX, redPosY, dist+1));
                blueMarble.offer(new Marble(bluePosX, bluePosY, 0));
            }
        }
    }

    static class Marble {
        int x;
        int y;
        int dist;

        public Marble(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
