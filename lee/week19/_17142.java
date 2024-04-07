package lee.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class _17142 {

    static int n;
    static int m;
    static int emptySpace;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static ArrayList<Point> virusArray = new ArrayList<>();
    static ArrayList<Point> activeVirusArray = new ArrayList<>();
    static Deque<Lab> q = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // -1 빈공간
        // -2 벽
        // -3 바이러스
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpace++;
                }
                else if (map[i][j] == 2) {
                    virusArray.add(new Point(i, j));
                }
            }
        }
        if (emptySpace == 0) {
            System.out.println(0);
            return;
        }

        virusSelect(0, 0);

        int time = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            time++;
            for (int i = 0; i < len; i++) {
                Lab lab = q.pollFirst();
                // 확산이 이루어진 경우
                if (lab.spread()) {
                    q.add(lab);
                    continue;
                }

                // 빈 공간이 없다면 시간 출력 후 종료
                if (lab.emptySpace == 0) {
                    System.out.println(time);
                    return;
                }
            }

        }
        System.out.println(-1);
    }

    static public void virusSelect(int cnt, int k) {
        if (cnt == m) {
            q.add(new Lab(activeVirusArray, emptySpace));
            return;
        }
        for (int i = k; i < virusArray.size(); i++) {
            Point point = virusArray.get(i);

            activeVirusArray.add(point);
            virusSelect(cnt + 1, i + 1);
            activeVirusArray.remove(cnt);
        }
    }


    static class Lab {

        boolean[][] valid = new boolean[n][n];
        ArrayList<Point> activeVirusArray;
        int emptySpace;


        public Lab(ArrayList<Point> virusArray,int emptySpace) {
            activeVirusArray = new ArrayList<>(virusArray);
            for (Point point : virusArray) {
                valid[point.r][point.c] = true;
            }
            this.emptySpace = emptySpace;
        }

        public boolean spread() {
            ArrayList<Point> temp = new ArrayList<>();
            boolean flag = false; // 더 이상 확산 되는지 판단
            int len = activeVirusArray.size();
            for (int i = 0; i < len; i++) {
                Point point = activeVirusArray.get(i);
                for (int k = 0; k < 4; k++) {
                    int nr = point.r + dr[k];
                    int nc = point.c + dc[k];
                    if (isValid(nr, nc)) {
                        if (!valid[nr][nc] && map[nr][nc] != 1) {
                            if (map[nr][nc] == 0) {
                                emptySpace--;
                            }
                            if (emptySpace == 0) {
                                return false;
                            }
                            valid[nr][nc] = true;
                            temp.add(new Point(nr,nc));
                            flag = true;
                        }
                    }
                }
            }
            activeVirusArray = new ArrayList<>(temp);

            // 확산이 이루어 졌다면 큐에 담기
            return flag;
        }

        private boolean isValid(int r, int c) {
            if (0 <= r && r < n && 0 <= c && c < n) {
                return true;
            }
            return false;
        }

    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


}