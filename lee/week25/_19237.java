package lee.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _19237 {

    static int n, m, k;
    static PriorityQueue<AdultShark>[][] map;
    static HashMap<Integer, AdultShark> sharkDetails = new HashMap<>();
    static int[][][] directionPriority;
    static int[][][] sharkSmell;
    static int sharkCnt = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new PriorityQueue[n][n];
        // 상어 총 수
        sharkCnt = m;

        // 상어 담기위한 우선순위 큐 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new PriorityQueue<>((s1, s2) -> {
                    return s2.num - s1.num;
                });
            }
        }

        // 상어 냄새 정보 초기화
        sharkSmell = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                sharkSmell[i][j][0] = -1;
            }
        }

        // 상어 정보 초기 설정
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int sharkNum = Integer.parseInt(st.nextToken()) - 1;
                if (sharkNum != -1) {
                    AdultShark adultShark = new AdultShark(sharkNum, i, j);
                    adultShark.leavingSmell(k);
                    sharkDetails.put(sharkNum, adultShark);
                    map[i][j].add(adultShark);
                }
            }
        }

        // 상어 방향 설정
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            AdultShark adultShark = sharkDetails.get(i);
            adultShark.dir = Integer.parseInt(st.nextToken()) - 1;
        }

        // 상어 방향 우선순위 설정
        directionPriority = new int[m][4][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    directionPriority[i][j][l] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        // 상어 이동
        int time = 0;
        while (time < 1000) {
            time++;
            // 상어 전체 이동
            for (int i = 0; i < m; i++) {
                AdultShark shark = sharkDetails.getOrDefault(i, null);
                if (shark == null) {
                    continue;
                }

                // 상어 이동
                shark.move();
            }

            // 상어들 이동이 끝나면 겹친 상어 제거 후 냄새 남기기
            for (int i = 0; i < m; i++) {
                AdultShark shark = sharkDetails.getOrDefault(i, null);
                if (shark == null) {
                    continue;
                }
                // 가장 작은 번호의 상어만 남기고 삭제
                while (map[shark.r][shark.c].size() > 1) {
                    AdultShark deletedShark = map[shark.r][shark.c].poll();
                    sharkDetails.remove(deletedShark.num);
                    sharkCnt--;
                }
                // 살아남은 상어는 냄새 남기기
                AdultShark remainShark = map[shark.r][shark.c].peek();
                remainShark.leavingSmell(k + 1);
            }

            // 상어가 한마리 남으면 종료
            if (sharkCnt == 1) {
                System.out.println(time);
                return;
            }

            // 기존 냄새들 유지시간 1초 줄이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (sharkSmell[i][j][1] >= 1) {
                        sharkSmell[i][j][1]--;
                        if (sharkSmell[i][j][1] == 0) {
                            sharkSmell[i][j][0] = -1;
                        }
                    }
                }
            }
        }

        // 1000초가 넘은 경우
        System.out.println(-1);
    }

    static boolean isValid(int r, int c) {
        if (0 <= r && r < n && 0 <= c && c < n) {
            return true;
        }
        return false;
    }

    static class AdultShark {

        int num;
        int r, c;

        int dir;

        public AdultShark(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        public void move() {
            // 방향에 따른 우선순위 4개
            for (int i = 0; i < 4; i++) {
                int ndir = directionPriority[num][dir][i];
                int nr = r + dr[ndir];
                int nc = c + dc[ndir];

                // 갈 수 있는지
                if (!isValid(nr, nc)) {
                    continue;
                }

                // 가려는 위치에 냄새가 있다면
                if (sharkSmell[nr][nc][1] != 0) {
                    continue;
                }

                // 기존 위치에서 pop
                map[r][c].poll();

                dir = ndir;
                r = nr;
                c = nc;

                // 새로운 위치에 추가
                map[r][c].add(this);

                return;
            }

            // 사방이 냄새일 경우
            for (int i = 0; i < 4; i++) {
                int ndir = directionPriority[num][dir][i];
                int nr = r + dr[ndir];
                int nc = c + dc[ndir];

                // 갈 수 있는지
                if (!isValid(nr, nc)) {
                    continue;
                }
                // 자신의 냄새인지
                if (sharkSmell[nr][nc][0] != num) {
                    continue;
                }
                // 기존 위치에서 pop
                map[r][c].poll();

                dir = ndir;
                r = nr;
                c = nc;

                // 새로운 위치에 추가
                map[r][c].add(this);

                return;
            }
        }

        public void leavingSmell(int k) {
            sharkSmell[r][c][0] = num;
            sharkSmell[r][c][1] = k;
        }

    }

}


