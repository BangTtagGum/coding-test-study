package lee.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _17837 {

    static int dr[] = {0, 0, -1, 1};
    static int dc[] = {1, -1, 0, 0};
    static int n, k;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Map<Integer, Chip> chipHashMap = new HashMap();
        Deque<Chip>[][] chipLocationMap = new ArrayDeque[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chipLocationMap[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            Chip chip = new Chip(i, r, c, dir);
            chipHashMap.put(i, chip);
            chipLocationMap[r][c].push(chip);
        }

        // 게임 시작
        int turn = 1;
        while (turn <= 1000) {
            for (int i = 1; i <= k; i++) {
                Chip chip = chipHashMap.get(i);
                // 이동할 칩들 임시 저장 스택
                Deque<Chip> tmpStack = new ArrayDeque<>();

                // 현재 칩 위에 쌓인 칩들 임시 스택에 추가
                Deque<Chip> stack = chipLocationMap[chip.r][chip.c];
                while (stack.peek().num != i) {
                    Chip nextChip = stack.pop();
                    tmpStack.addLast(nextChip);
                }
                // i번째 칩도 기존 위치 스택에서 제거 후 임시 스택에 추가
                stack.pop();
                tmpStack.addLast(chip);

                int nr = chip.r + dr[chip.dir];
                int nc = chip.c + dc[chip.dir];
                // 이동 가능한 경우
                if (isValid(nr, nc)) {
                    // 이동할 위치의 스택
                    Deque<Chip> nextStack = chipLocationMap[nr][nc];

                    // 빨간색인 경우 뒤집어서 삽입
                    if (map[nr][nc] == 1) {
                        while (!tmpStack.isEmpty()) {
                            Chip tmpChip = tmpStack.pop();
                            tmpChip.r = nr;
                            tmpChip.c = nc;
                            nextStack.push(tmpChip);
                        }
                    } else {
                        while (!tmpStack.isEmpty()) {
                            Chip tmpChip = tmpStack.pollLast();
                            tmpChip.r = nr;
                            tmpChip.c = nc;
                            nextStack.push(tmpChip);
                        }
                    }

                    // 종료 조건
                    if (chipLocationMap[nr][nc].size() >= 4) {
                        System.out.println(turn);
                        return;
                    }
                } else { // 이동 불가능한 경우 반대로 한칸 이동 시도
                    chip.dir = chip.dir % 2 == 0 ? chip.dir + 1 : chip.dir - 1;
                    nr = chip.r + dr[chip.dir];
                    nc = chip.c + dc[chip.dir];
                    // 이동 가능한 경우
                    if (isValid(nr, nc)) {
                        // 이동할 위치의 스택
                        Deque<Chip> nextStack = chipLocationMap[nr][nc];

                        // 빨간색인 경우 뒤집어서 삽입
                        if (map[nr][nc] == 1) {
                            while (!tmpStack.isEmpty()) {
                                Chip tmpChip = tmpStack.pop();
                                tmpChip.r = nr;
                                tmpChip.c = nc;
                                nextStack.push(tmpChip);
                            }
                        } else {
                            while (!tmpStack.isEmpty()) {
                                Chip tmpChip = tmpStack.pollLast();
                                tmpChip.r = nr;
                                tmpChip.c = nc;
                                nextStack.push(tmpChip);
                            }
                        }

                        // 종료 조건
                        if (chipLocationMap[nr][nc].size() >= 4) {
                            System.out.println(turn);
                            return;
                        }
                    } else {
                        Deque<Chip> nextStack = chipLocationMap[chip.r][chip.c];
                        while (!tmpStack.isEmpty()) {
                            Chip tmpChip = tmpStack.pollLast();
                            nextStack.push(tmpChip);
                        }
                    }
                }


            }
            turn++;

        }
        System.out.println(-1);

    }

    static boolean isValid(int r, int c) {
        if (0 <= r && r < n && 0 <= c && c < n && map[r][c] != 2) {
            return true;
        }
        return false;
    }

    static class Chip {

        int num;
        int r, c, dir;
        int up = 0;

        public Chip(int num, int r, int c, int dir) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

    }

}


