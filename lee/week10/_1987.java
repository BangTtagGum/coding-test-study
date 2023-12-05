package lee.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.11.26
 * 알파벳
 */
public class _1987 {

    static int r;
    static int c;
    static char[][] map;
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static boolean[] isUsed = new boolean[26];
    static int count;
    static int maxCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        char alpha = map[0][0];
        isUsed[alpha - 65] = true;
        count = 1;

        dfs(0, 0);

        System.out.println(maxCount);


    }

    public static void dfs(int r, int c) {

        for (int i = 0; i < 4; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];
            if (isValid(nc, nr)) {
                char alpha = map[nr][nc];
                if (!isUsed[alpha - 65]) {
                    isUsed[alpha - 65] = true;
                    count++;
                    if (count > maxCount) {
                        maxCount = count;
                    }
                    dfs(nr,nc);
                    count--;
                    isUsed[alpha - 65] = false;
                }
            }
        }
    }

    public static boolean isValid(int x, int y) {
        if (0 <= x && x < c && 0 <= y && y < r) {
            return true;
        }
        return false;
    }
}
