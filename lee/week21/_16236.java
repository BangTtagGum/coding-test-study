package lee.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16236 {

    static int map[][];
    static BabyShark shark;
    static Map<Integer, Integer> totaㅌㅐ태크lFishes = new HashMap<>();
    static PriorityQueue<ArrayList<Integer>> pq;
    static int n;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 물고기 거리순 정렬
        pq = new PriorityQueue<>((a, b) -> {
            // 거리가 같을 경우
            if (a.get(0) - b.get(0) == 0) {
                // r이 같을 경우 가장 왼쪽부터
                if (a.get(1) == b.get(1)) {
                    return a.get(2) - b.get(2);
                }
                return a.get(1) - b.get(1);
            }
            return a.get(0) - b.get(0);
        });

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new BabyShark(i, j);
                } else if (map[i][j] != 0) {
                    totalFishes.put(i * n + j, map[i][j]);
                }
            }
        }
        // 최단거리 구하고 PQ 삽입
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    int distance = getClosestDistance(i, j);
                    if (distance > 0) {
                        pq.add(new ArrayList<>(Arrays.asList(distance, i, j)));
                    }
                }
            }
        }

        while (pq.size() > 0) {
            ArrayList<Integer> fish = pq.poll();
            shark.eat(fish);

            if (shark.exp == shark.size) {
                shark.sizeUp();
            }
            // 거리 다시 계산
            pq.clear();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) {
                        int fishSize = totalFishes.getOrDefault(i * n + j, -1);
                        if (fishSize != -1 && fishSize < shark.size) {
                            int distance = getClosestDistance(i, j);
                            if (distance > 0) {
                                pq.add(new ArrayList<>(Arrays.asList(distance, i, j)));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(time);
    }

    private static int getClosestDistance(int targetRow, int targetCol) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};
        boolean[][] isVisit = new boolean[n][n];
        Queue<ArrayList<Integer>> q = new LinkedList<>();

        int r = shark.r;
        int c = shark.c;
        int size = shark.size;
        q.add(new ArrayList<>(Arrays.asList(r, c)));
        int distance = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                ArrayList<Integer> fish = q.poll();
                if (fish.get(0) == targetRow && fish.get(1) == targetCol) {
                    return distance;
                }
                for (int j = 0; j < 4; j++) {
                    int nr = fish.get(0) + dr[j];
                    int nc = fish.get(1) + dc[j];
                    if (isValid(nr, nc) && map[nr][nc] <= size && !isVisit[nr][nc]) {
                        isVisit[nr][nc] = true;
                        q.add(new ArrayList<>(Arrays.asList(nr, nc)));
                    }
                }
            }
            distance++;
        }
        // 갈 수 없는 경우
        return -1;
    }

    private static boolean isValid(int r, int c) {
        if (0 <= r && r < n && 0 <= c && c < n) {
            return true;
        }
        return false;
    }

    static class BabyShark {

        int size = 2;
        int exp = 0;
        int r;
        int c;

        public BabyShark(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public BabyShark() {
        }

        public void eat(ArrayList<Integer> fish) {
            time += fish.get(0);
            exp++;
            this.r = fish.get(1);
            this.c = fish.get(2);
            map[r][c] = 0;
        }

        public void sizeUp() {
            this.size++;
            this.exp = 0;
        }
    }
}