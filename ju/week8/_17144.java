package ju.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미세먼지 안녕!
 *
 * 메모리: 34284KB
 * 시간: 376ms
 *
 * 단순구현문제로 노가다가 많이 필요함
 */
public class _17144 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] maps;
    static int[][] spreadMap;
    static int r, c;
    static int airR, airC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        maps = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());

                if (maps[i][j] == -1) {
                    airR = i;
                    airC = j;
                }
            }
        }


        for (int i = 0; i < t; i++) {
            spreadMap = new int[r][c];
            //미세먼지 확산
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (maps[j][k] > 0) {
                        spread(j, k);
                    }
                }
            }

            //확산된 미세먼지 값 합치기
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (maps[j][k] != -1) {
                        maps[j][k] += spreadMap[j][k];
                    }
                }
            }

            //공기청정기 순환
            airClean();
        }

        //결과
        int result = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i][j] != -1) {
                    result += maps[i][j];
                }
            }
        }

        System.out.println(result);
    }

    private static void airClean() {
        //공기청정기 위치부터 반시계로 돌려야함, 상단 공기청정기 위치 (airR-1, airC)
        //오른쪽 -> 왼쪽
        for (int i = airC-1; i > 0; i--) {
            maps[airR-1][i] = maps[airR-1][i-1];
        }
        //왼쪽아래 -> 왼쪽위
        for (int i = airR-1; i > 0; i--) {
            maps[i][0] = maps[i-1][0];
        }
        //상단왼쪽 -> 상단오른쪽
        for (int i = 0; i < c-1; i++) {
            maps[0][i] = maps[0][i+1];
        }
        //오른쪽위 -> 오른쪽아래
        for (int i = 0; i < airR - 1; i++) {
            maps[i][c-1] = maps[i+1][c-1];
        }
        //오른쪽 -> 왼쪽
        for (int i = c-1; i > airC; i--) {
            maps[airR-1][i] = maps[airR-1][i-1];
        }
        maps[airR-1][airC+1] = 0;
        maps[airR-1][airC] = -1;

        //공기청정기 위치부터 반시계로 돌려야함, 하단 공기청정기 위치 (airR, airC)
        //오른쪽 -> 왼쪽
        for (int i = airC-1; i > 0; i--) {
            maps[airR][i] = maps[airR][i-1];
        }
        //왼쪽위 -> 왼쪽아래
        for (int i = airR; i < r-1; i++) {
            maps[i][0] = maps[i+1][0];
        }
        //하단왼쪽 -> 하단오른쪽
        for (int i = 0; i < c-1; i++) {
            maps[r-1][i] = maps[r-1][i+1];
        }
        //오른쪽아래 -> 오른쪽위
        for (int i = r-1; i > airR; i--) {
            maps[i][c-1] = maps[i-1][c-1];
        }
        //오른쪽 -> 왼쪽
        for (int i = c-1; i > airC; i--) {
            maps[airR][i] = maps[airR][i-1];
        }
        maps[airR][airC+1] = 0;
        maps[airR][airC] = -1;
    }

    private static void spread(int j, int k) {

        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int x = j + dx[i];
            int y = k + dy[i];

            if (x < 0 || x >= r || y < 0 || y >= c || maps[x][y] == -1) continue;

            spreadMap[x][y] += maps[j][k]/5;
            cnt++;
        }

        maps[j][k] -= maps[j][k]/5 * cnt;
    }
}
