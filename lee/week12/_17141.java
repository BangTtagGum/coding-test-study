package lee.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.12.11
 * 연구소 2
 */

public class _17141 {

    static int[][] map;
    static int[][] virusPoint = new int[10][2];
    static int virusPointCnt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int n;
    static int m;
    static int minCnt = 99999;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        virusPointCnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusPoint[virusPointCnt][0] = i;
                    virusPoint[virusPointCnt][1] = j;
                    virusPointCnt++;
                }
                map[i][j] -= 3; // -3 은 빈 공간 -2는 벽 -1은 바이러스 놓을 수 있는 위치
            }
        }

        dfs(0);

        if (minCnt == 99999) {
            System.out.println(-1);
        } else {
            System.out.println(minCnt);
        }


    }

    public static void dfs(int virusCnt) {
        if (virusCnt == m) {
            // 바이러스 위치 선택이 완료되면 BFS로 최단기간 탐색
            Lab lab = new Lab(map);
            int cnt = virusSpreadCount(lab);
            if (cnt != -1) {
                minCnt = Math.min(minCnt, cnt);
            }
            return ;
        }
        for (int i = virusCnt; i < virusPointCnt; i++) {
            if (map[virusPoint[i][0]][virusPoint[i][1]] == -1) {
                map[virusPoint[i][0]][virusPoint[i][1]] = 0;
                dfs(virusCnt + 1);
                map[virusPoint[i][0]][virusPoint[i][1]] = -1;
            }
        }
    }

    // 바이러스가 배치가 완료 된 후 해당 상태에서 바이러스가 퍼지는 최단 시간 구하는 메소드
    public static int virusSpreadCount(Lab lab) {
        int time = 0;
        while (true) {
            boolean spread = false;
            int len = lab.map.length;
            time++;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (lab.map[i][j] == time - 1) {
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (lab.isValid(nr, nc)) {
                                spread = true;
                                lab.map[nr][nc] = time;
                            }
                        }
                    }
                }
            }
            if (!spread) {
                break;
            }
        }
        // 바이러스가 다 퍼지지 못했다면
        if (!lab.virusCompletelySpread()) {
            return -1;
        }
        return time - 1;
    }

    static class Lab{
        int[][] map;
        int time;

        public Lab(int[][] map) {
            this.map = copyMap(map);
            this.time = 0;
        }

        private int[][] copyMap(int[][] map) {
            int[][] ints = new int[map.length][map.length];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    ints[i][j] = map[i][j];
                }
            }
            return ints;
        }

        boolean isValid(int r,int c) {
            int len = this.map.length;
            if (0 <= r && r < len && 0 <= c && c < len) {
                if(map[r][c] == -1 || map[r][c] == -3)
                    return true;
            }
            return false;
        }

        boolean virusCompletelySpread() {
            int len = this.map.length;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (this.map[i][j] == -3 || this.map[i][j] == -1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


}



