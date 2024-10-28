import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _20058 {

    static int n;
    static int q;
    static int len;
    static int[][] A;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        len = (int) Math.pow(2, n);
        A = new int[len][len];

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (q-- > 0) {

            // 얼음 줄일 좌표 저장 큐
            Queue<int[]> q = new LinkedList<>();

            int l = Integer.parseInt(st.nextToken());
            fireStorm(l);

            // 얼음 녹이기
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (A[i][j] == 0) {
                        continue;
                    }
                    int adjacentCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (isNearByIce(nr, nc)) {
                            adjacentCnt++;
                        }
                    }
                    if (adjacentCnt < 3) {
                        q.add(new int[]{i, j});
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                A[poll[0]][poll[1]]--;
            }
        }

        int totalIce = getTotalIce();
        int largestSize = getLargestSize();

        System.out.println(totalIce);
        System.out.println(largestSize);


    }

    private static int getLargestSize() {
        int maxSize = 0;
        boolean[][] isVisited = new boolean[len][len];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (A[i][j] != 0 && !isVisited[i][j]) {
                    int currentSize = 1;
                    q.add(new int[]{i, j});
                    isVisited[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] poll = q.poll();
                        int r = poll[0];
                        int c = poll[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (isNearByIce(nr, nc) && !isVisited[nr][nc]) {
                                q.add(new int[]{nr, nc});
                                isVisited[nr][nc] = true;
                                currentSize++;
                            }
                        }
                    }

                    maxSize = Math.max(maxSize, currentSize);
                }
            }
        }
        return maxSize;
    }

    private static int getTotalIce() {
        int total = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                total += A[i][j];
            }
        }
        return total;
    }

    private static boolean isNearByIce(int nr, int nc) {
        if (0 <= nr && nr < len && 0 <= nc && nc < len && A[nr][nc] != 0) {
            return true;
        }
        return false;
    }

    private static void fireStorm(int l) {
        int partLength = (int) Math.pow(2, l);
        for (int i = 0; i < len; i += partLength) {
            for (int j = 0; j < len; j += partLength) {
                turn(i, j, partLength);
            }
        }
    }

    // y = -x 축으로 뒤집고 이후 y축으로 뒤집어 시계방향으로 90도 돌리기
    private static void turn(int r, int c, int len) {
        // y = -x 축으로 뒤집기

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int tmp = A[i + r][j + c];
                A[i + r][j + c] = A[j + r][i + c];
                A[j + r][i + c] = tmp;
            }
        }

        // y 축으로 뒤집기
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int tmp = A[i + r][j + c];
                A[i + r][j + c] = A[i + r][len - 1 - j + c];
                A[i + r][len - 1 - j + c] = tmp;
            }
        }
    }

}