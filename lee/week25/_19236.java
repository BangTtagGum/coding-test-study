package lee.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _19236 {

    static int maxScore = 0;

    public static void main(String[] args) throws IOException {

        Fishbowl fishbowl = new Fishbowl();
        fishbowl.setup();

        fishbowl.cycle();

        System.out.println(maxScore);
    }

    static class Fishbowl {

        private int[][] water = new int[4][4];
        private Map<Integer, ArrayList<Integer>> fishDetails = new HashMap<>();
        private Map<Integer, Boolean> deadFishes = new HashMap<>();
        private Shark shark;


        public Fishbowl() {
        }

        int dr[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
        int dc[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};

        void setup() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    int dir = Integer.parseInt(st.nextToken());

                    water[i][j] = num;
                    ArrayList<Integer> fishDetail = new ArrayList<>(Arrays.asList(dir, i, j));

                    fishDetails.put(num, fishDetail);
                }
            }

            // 상어 출현
            int fishNum = water[0][0];
            shark = new Shark();
            // 맨 처음 물고기 먹기
            shark.eat(fishNum);
            water[0][0] = -1;
            br.close();
        }

        void cycle() {
            fishMove();
            sharkMove();
        }


        private void fishMove() {
            for (int i = 1; i <= 16; i++) {

                // 먹히지 않은 물고기만 이동
                if (!deadFishes.getOrDefault(i, false)) {
                    ArrayList<Integer> fishDetail = fishDetails.get(i);
                    int dir = fishDetail.get(0);
                    int r = fishDetail.get(1);
                    int c = fishDetail.get(2);

                    for (int j = 0; j <= 7; j++) {
                        int ndir = (dir + j) <= 8 ? dir + j : dir + j - 8;

                        int nr = r + dr[ndir];
                        int nc = c + dc[ndir];
                        // 갈 수 있으면 이동
                        if (isValid(i, nr, nc)) {
                            swap(r, c, nr, nc, ndir);
                            break;
                        }
                    }

                }
            }
        }

        private void swap(int r, int c, int nr, int nc, int ndir) {

            int a = water[r][c];
            int b = water[nr][nc];

            ArrayList<Integer> fishADetail = fishDetails.get(a);

            fishADetail.set(0, ndir);
            fishADetail.set(1, nr);
            fishADetail.set(2, nc);

            // 바꾸려는 곳에 물고기가 존재할 경우
            if (b != 0) {
                ArrayList<Integer> fishBDetail = fishDetails.get(b);

                fishBDetail.set(1, r);
                fishBDetail.set(2, c);
            }
            water[r][c] = b;
            water[nr][nc] = a;

        }

        private boolean isValid(int fishNum, int r, int c) {
            if (0 <= r && r < 4 && 0 <= c && c < 4) {
                if (fishNum == -1) {
                    if (water[r][c] != 0) {
                        return true;
                    }
                } else if (water[r][c] != -1) {
                    return true;
                }
            }
            return false;
        }


        private void sharkMove() {
            boolean flag = true;

            int dir = shark.dir;
            int r = shark.r;
            int c = shark.c;
            for (int i = 1; i <= 3; i++) {
                int nr = r + dr[dir] * i;
                int nc = c + dc[dir] * i;
                if (isValid(-1, nr, nc)) {
                    flag = true;
                    int[][] copyWater = copyWater();
                    Map<Integer, ArrayList<Integer>> copyFishDetails = copyFishDetails();
                    HashMap<Integer, Boolean> copyDeadFishes = new HashMap<>(deadFishes);
                    Shark copyShark = new Shark(shark);

                    shark.r = nr;
                    shark.c = nc;
                    shark.eat(water[nr][nc]);

                    water[r][c] = 0;
                    water[nr][nc] = -1;

                    cycle();
                    // 시행 후 원복
                    this.water = copyWater;
                    this.fishDetails = copyFishDetails;
                    this.deadFishes = copyDeadFishes;
                    this.shark = copyShark;
                }
            }
            // 세번의 이동 모두 불가능 할 경우 상어의 점수 저장
            if (flag) {
                maxScore = Math.max(maxScore, shark.score);
            }

        }

        private Map<Integer, ArrayList<Integer>> copyFishDetails() {
            Map<Integer, ArrayList<Integer>> copyFishDetails = new HashMap<>();
            for (int i = 1; i <= 16; i++) {
                ArrayList<Integer> details = fishDetails.get(i);
                copyFishDetails.put(i, new ArrayList<>(details));
            }
            return copyFishDetails;
        }

        private int[][] copyWater() {
            int[][] copyWater = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyWater[i][j] = water[i][j];
                }
            }
            return copyWater;
        }


        private class Shark {

            int score = 0;
            int dir = 0;
            int r = 0, c = 0;

            public Shark(Shark shark) {
                this.score = shark.score;
                this.dir = shark.dir;
                this.r = shark.r;
                this.c = shark.c;
            }

            public Shark() {

            }

            void eat(int fishNum) {
                ArrayList<Integer> fishDetail = fishDetails.get(fishNum);
                score += fishNum;
                dir = fishDetail.get(0);

                deadFishes.put(fishNum, true);
            }
        }
    }
}
