package lee.week13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2023.12.15
 * 2667 단지번호붙이기
 */
public class _2667 {

    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int n;
    static int group;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        sc.nextLine();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '1') {
                    map[i][j] = 1;
                }
            }
        }

        ArrayList<Integer> groupCntList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    group++;
                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j));
                    int cnt = 0;
                    while (!q.isEmpty()) {
                        Point point = q.poll();
                        cnt++;
                        int r = point.r;
                        int c = point.c;
                        map[r][c] = 2;

                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (isValid(nr, nc)) {
                                q.add(new Point(nr, nc));
                                map[nr][nc] = 2;
                            }
                        }
                    }
                    groupCntList.add(cnt);
                }
            }
        }
        System.out.println(group);

        groupCntList.sort(Comparator.naturalOrder());
        for (Integer i : groupCntList) {
            System.out.println(i);
        }

    }

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r  ;
            this.c = c;
        }
    }

    private static boolean isValid(int nr, int nc) {
        if (0 <= nr && nr < n && 0 <= nc && nc < n && map[nr][nc] == 1){
            return true;
        }

        return false;
    }
}
