package ju.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 연구소
 *
 * 벽 3개를 세우는 모든 경우의 수를 계산 후 완전탐색해서 안전영역 결과값 추출
 *
 * 메모리: 230868KB
 * 시간: 596ms
 */
public class _14502 {

    static int[][] maps;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;
    static int safeCnt = 0;
    static List<int[]> virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maps = new int[n][m];
        visited = new boolean[n][m];

        virus = new ArrayList<>();
        List<int[]> safeArea = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 2){
                    virus.add(new int[]{i,j});
                }else if (maps[i][j] == 0) {
                    safeArea.add(new int[]{i,j});
                }
            }
        }

        //벽을 3개 세워두고 dfs돌리기
        for (int i = 0; i < safeArea.size()-2; i++) {
            for (int j = i+1; j < safeArea.size()-1; j++) {
                for (int k = i+2; k < safeArea.size(); k++) {
                    visited = new boolean[n][m];
                    safeCnt = safeArea.size() - 3;
                    maps[safeArea.get(i)[0]][safeArea.get(i)[1]] = 1;
                    maps[safeArea.get(j)[0]][safeArea.get(j)[1]] = 1;
                    maps[safeArea.get(k)[0]][safeArea.get(k)[1]] = 1;
                    bfs();
                    maps[safeArea.get(i)[0]][safeArea.get(i)[1]] = 0;
                    maps[safeArea.get(j)[0]][safeArea.get(j)[1]] = 0;
                    maps[safeArea.get(k)[0]][safeArea.get(k)[1]] = 0;

                    if (safeCnt > result) result = safeCnt;
                }
            }
        }

        System.out.println(result);
    }

    private static void bfs() {

        Queue<int[]> q = new LinkedList<>();
        virus.stream().forEach(x -> q.offer(new int[]{x[0], x[1]}));

        while (!q.isEmpty()) {
            int[] popQ = q.poll();
            int x = popQ[1];
            int y = popQ[0];

            if (safeCnt <= result) break;

            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                if (posY < 0 || posY >= n || posX < 0 || posX >= m || maps[posY][posX] != 0 || visited[posY][posX]) continue;

                q.offer(new int[]{posY, posX});
                visited[posY][posX] = true;
                safeCnt--;
            }
        }
    }
}
