package ju.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇 청소기
 *
 * 미해결
 */
public class _14503 {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int n, m;
    private static int[][] maps;
    private static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        RobotPos robotPos = new RobotPos();
        robotPos.setPosX(Integer.parseInt(st.nextToken()));
        robotPos.setPosY(Integer.parseInt(st.nextToken()));
        robotPos.setDir(Integer.parseInt(st.nextToken()));

        maps = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(robotPos);

        System.out.println(result);
    }

    private static void dfs(RobotPos robotPos) {
        int posX = robotPos.getPosX();
        int posY = robotPos.getPosY();
        int dir = robotPos.getDir();

        maps[posX][posY] = 2;
        for (int i = 0; i < 4; i++) {
            //dir 을 현재 방향부터 반시계로 돌려줘야함
            int nowDir = (dir + 1 + i) % 4;
            int x = posX + dx[nowDir];
            int y = posY + dy[nowDir];

            if (x >= 0 && x < n && y >= 0 && y < m && maps[x][y] == 0) {
                result++;
                dfs(new RobotPos(x, y, nowDir));
                return;
            }
        }

        //후진 체크
        int checkDir = (dir + 2) % 4;
        int checkX = posX + dx[checkDir];
        int checkY = posY + dy[checkDir];
        if (checkX >= 0 && checkX < n && checkY >= 0 && checkY < m && maps[checkX][checkY] != 1) {
            dfs(new RobotPos(checkX, checkY, dir));
        }
    }

    static class RobotPos {
        int posX;
        int posY;
        int dir;

        public RobotPos() {
        }

        public RobotPos(int posX, int posY, int dir) {
            this.posX = posX;
            this.posY = posY;
            this.dir = dir;
        }

        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        public int getDir() {
            return dir;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }
    }
}
