package ju.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 인구 이동
 *
 * 메모리: 298688KB
 * 시간: 608ms
 */
public class _16234 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, l, r;
    static int[][] nation;
    static boolean[][] visited;
    static boolean resultBool = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        nation = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                nation[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (resultBool) {
            resultBool = false;
            visited = new boolean[n][n];    //중복 체크 방지
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    if (openAndDist(i, j)) resultBool = true;
                }
            }
            if (resultBool) result++;
        }
        System.out.println(result);
    }

    private static boolean openAndDist(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        int sum = nation[x][y];
        List<int[]> openNation = new ArrayList<>();
        openNation.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int posX = poll[0];
            int posY = poll[1];
            for (int i = 0; i < 4; i++) {
                int naX = posX + dx[i];
                int naY = posY + dy[i];

                if (naX < 0 || naX >= n || naY < 0 || naY >= n || visited[naX][naY]) continue;

                int diff = Math.abs(nation[posX][posY] - nation[naX][naY]);

                //국경선 열기
                if (diff >= l && diff <= r) {
                    q.offer(new int[]{naX, naY});
                    openNation.add(new int[]{naX, naY});
                    sum += nation[naX][naY];
                    visited[naX][naY] = true;
                }
            }
        }

        //인구 분배
        if (openNation.size() > 1) {
            for (int[] pos : openNation) {
                nation[pos[0]][pos[1]] = sum/openNation.size();
            }
            return true;
        }else {
            return false;
        }
    }
}
