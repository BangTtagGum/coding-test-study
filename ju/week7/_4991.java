package ju.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 로봇청소기
 *
 * 청소기가 완전탐색해서 더러운 곳을 발견했을 때마다 전체 해당 위치에서 다시 완전탐색해서 전체 값 계산
 *
 * 반례
 * 가까운 먼지부터 탐색할경우 먼곳까지 돌아가는 문제
 *
 * 5 5
 * ....*
 * .*.*.
 * ..o..
 * ..*..
 * .....
 *
 * 답: 8
 * 결과: 10
 *
 * ??????????
 */
public class _4991 {

    static char[][] maps;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int h;
    static int w;
    static Queue<RobotPos> bfsQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0) break;

            int dirtyPlace = 0;
            maps = new char[h][w];
            bfsQ = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                String lines = br.readLine();
                for (int j = 0; j < w; j++) {
                    maps[i][j] = lines.charAt(j);
                    if (maps[i][j] == 'o') {
                        bfsQ.offer(new RobotPos(i, j, 0));
                    } else if (maps[i][j] == '*') {
                        dirtyPlace++;
                    }
                }
            }

            int result = 0;
            while (!bfsQ.isEmpty()) {
                visited = new boolean[h][w];

                if (dirtyPlace == 0) break;

                int bfsResult = bfs(bfsQ.poll());
                if (bfsResult == -1) {
                    break;
                }

                result = bfsResult;
                dirtyPlace--;
            }


            System.out.println(dirtyPlace == 0 ? result : -1);
        }
    }

    private static int bfs(RobotPos robotPos) {
        Queue<RobotPos> q = new LinkedList<>();
        q.offer(new RobotPos(robotPos.x, robotPos.y, robotPos.dist));
        visited[robotPos.y][robotPos.x] = true;

        while (!q.isEmpty()) {
            RobotPos pollRobotPos = q.poll();
            int x = pollRobotPos.x;
            int y = pollRobotPos.y;

            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                if (posX < 0 || posX >= w || posY < 0 || posY >= h || visited[posY][posX] || maps[posY][posX] == 'x') continue;

                if (maps[posY][posX] == '*') {
                    maps[posY][posX] = '.';
                    bfsQ.offer(new RobotPos(posX, posY, pollRobotPos.dist+1));
                    return pollRobotPos.dist+1;
                }

                visited[posY][posX] = true;
                q.offer(new RobotPos(posX, posY, pollRobotPos.dist+1));
            }
        }

        return -1;
    }

    static class RobotPos{
        int x;
        int y;
        int dist;

        public RobotPos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
