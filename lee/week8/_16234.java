package lee.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.11.06 인구 이동
 */
public class _16234 {

    static Country[][] map;
    static boolean[][] isVisited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static int l;
    static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new Country[n][n];
        isVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                Country con = new Country(j, i, Integer.parseInt(st.nextToken()));
                map[i][j] = con;
            }
        }

        int day = 0;
        while (true) {

            if (!oneDayCycle()) {
                break;
            }
            day++;
        }

        System.out.println(day);
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isVisited[i][j] = false;
            }
        }
    }

    static boolean oneDayCycle() {
        boolean move = false;
        init();
        //하루 사이클
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    Queue<Country> q = new LinkedList<>();
                    Queue<Country> spreadQueue = new LinkedList<>();
                    q.add(map[i][j]);
                    spreadQueue.add(map[i][j]);

                    int populationSum = 0;
                    while (!q.isEmpty()) {
                        Country con = q.poll();
                        int x = con.x;
                        int y = con.y;
                        int population = con.population;
                        populationSum += population;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (isValid(nx, ny) && !isVisited[ny][nx]) {
                                int diff = Math.abs(population - map[ny][nx].population);
                                if (l <= diff && diff <= r) {
                                    move = true;
                                    isVisited[ny][nx] = true;
                                    q.add(map[ny][nx]);
                                    spreadQueue.add(map[ny][nx]);
                                }
                            }
                        }
                    }

                    //인구수 분배
                    int teamSize = spreadQueue.size();
                    while (!spreadQueue.isEmpty()) {
                        Country con = spreadQueue.poll();
                        int x = con.x;
                        int y = con.y;
                        map[y][x].population = populationSum / teamSize;
                    }
                }
            }
        }
        return move;
    }

    static boolean isValid(int x, int y) {
        if (0 <= x && x < n && 0 <= y && y < n) {
            return true;
        }
        return false;
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j].population);
            }
            System.out.println();
        }
    }

    static class Country {

        int x;
        int y;
        int population;

        public Country(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }
    }


}
