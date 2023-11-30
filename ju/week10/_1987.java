package ju.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 알파벳
 *
 * 알파벳 26개를 visited로 선언해서 방문하면 return
 *
 * 메모리: 15084KB
 * 시간: 1028ms
 */
public class _1987 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int r, c;
    static boolean[] visited = new boolean[26];
    static int[][] boards;
    static int result = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        boards = new int[r][c];
        for (int i = 0; i < r; i++) {
            String board = br.readLine();
            for (int j = 0; j < c; j++) {
                boards[i][j] = board.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);

        System.out.println(result);
    }

    private static void dfs(int x, int y, int count) {

        if (visited[boards[x][y]]) {
            result = Math.max(result, count);
        } else {
            visited[boards[x][y]] = true;
            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                if (posX < 0 || posX >= r || posY < 0 || posY >= c) continue;

                dfs(posX, posY, count + 1);
            }
            visited[boards[x][y]] = false;
        }
    }
}
