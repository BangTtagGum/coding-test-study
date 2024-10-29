package lee.week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _21608 {


    static StringTokenizer st;
    static int n;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] sits = new int[n][n];
        ArrayList<Integer>[] favorites = new ArrayList[n * n + 1];
        for (int i = 0; i <= n * n; i++) {
            favorites[i] = new ArrayList<>();
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> {
            // 좋아하는 학생이 동일한 경우
            if (arr1[0] == arr2[0]) {
                // 비어있는 자리가 동일한 경우
                if (arr1[1] == arr2[1]) {
                    // 행의 번호가 동일한 경우
                    if (arr1[2] == arr2[2]) {
                        return arr1[3] - arr2[3];
                    }
                    return arr1[2] - arr2[2];
                }
                return arr2[1] - arr1[1];
            }
            return arr2[0] - arr1[0];
        });

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                favorites[studentNum].add(Integer.parseInt(st.nextToken()));
            }

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (sits[r][c] != 0) {
                        continue;
                    }
                    int favoriteCount = 0;
                    int emptyCount = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (isValid(nr, nc)) {
                            if (sits[nr][nc] == 0) {
                                emptyCount++;
                            }
                            if (favorites[studentNum].contains(sits[nr][nc])) {
                                favoriteCount++;
                            }
                        }
                    }
                    pq.add(new int[]{favoriteCount, emptyCount, r, c});
                }
            }
            int[] poll = pq.poll();
            sits[poll[2]][poll[3]] = studentNum;
            pq.clear();
        }

        // 호감도 체크
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int studentNum = sits[i][j];
                int favoriteCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (isValid(nr, nc)) {
                        if (favorites[studentNum].contains(sits[nr][nc])) {
                            favoriteCount++;
                        }
                    }
                }
                total += favoriteCount == 0 ? 0 : (int) Math.pow(10, favoriteCount - 1);
            }
        }
        System.out.println(total);
    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }
}