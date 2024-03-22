package ju.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 상범 빌딩
 *
 * 메모리: 20396KB
 * 시간: 240ms
 */
public class _6593 {
    static int l, r, c;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Queue<Position> q;
    static boolean[][][] visited;
    static int[][][] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0) System.exit(0);

            building = new int[l][r][c];
            visited = new boolean[l][r][c];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String lines = br.readLine();
                    for (int k = 0; k < c; k++) {
                        char line = lines.charAt(k);
                        building[i][j][k] = line;
                        if (line == 'S') {
                            q.offer(new Position(i, j, k, 0));
                            visited[i][j][k] = true;
                        }
                    }
                }
                br.readLine();
            }

            System.out.println(bfs());
        }
    }

    private static String bfs() {

        while (!q.isEmpty()) {
            Position poll = q.poll();
            int x = poll.x;
            int y = poll.y;
            int z = poll.z;
            int curTime = poll.time;

            if (building[x][y][z] == 'E') {
                return String.format("Escaped in %d minute(s).", curTime);
            }

            for (int i = 0; i < 6; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];
                int posZ = z + dz[i];

                if (posX < 0 || posX >= l || posY < 0 || posY >= r || posZ < 0 || posZ >= c || visited[posX][posY][posZ] || building[posX][posY][posZ] == '#') continue;

                visited[posX][posY][posZ] = true;
                q.offer(new Position(posX, posY, posZ, curTime + 1));
            }
        }

        return "Trapped!";
    }

    static class Position {
        int x;  //층
        int y;  //세로
        int z;  //가로
        int time;

        public Position(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
