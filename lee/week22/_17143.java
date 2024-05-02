package lee.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17143 {

    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, 1, -1};
    static int maxR, maxC, m;

    static Map<String, Shark> sharkMap = new HashMap<>();
    static Map<String, Boolean> sharkExist = new HashMap<>();
    static Queue<Shark> sharkList = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        maxR = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sharkR = Integer.parseInt(st.nextToken()) - 1;
            int sharkC = Integer.parseInt(st.nextToken()) - 1;
            int sharkSpeed = Integer.parseInt(st.nextToken());
            int sharkDir = Integer.parseInt(st.nextToken()) - 1;
            int sharkSize = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(sharkR, sharkC, sharkSpeed, sharkDir, sharkSize);
            String sharkRC = sharkR + " " + sharkC;
            sharkMap.put(sharkRC, shark);
            sharkExist.put(sharkRC, true);
            sharkList.add(shark);
        }

        FishKing fishKing = new FishKing();
        while (fishKing.c < maxC) {
            // 낚시왕 이동
            fishKing.move();
            // 낚시왕 낚시
            fishKing.fishing();
            // 상어 이동
            int size = sharkList.size();
            for (int i = 0; i < size; i++) {
                Shark shark = sharkList.poll();
                String sharkRC = shark.r + " " + shark.c;
                Boolean exist = sharkExist.getOrDefault(sharkRC, false);
                if (exist) {
                    shark.move();
                    sharkExist.put(sharkRC, false);
                    sharkMap.put(sharkRC, null);
                    sharkList.add(shark);
                }
            }

            // 상어 중복 제거
            size = sharkList.size();
            for (int i = 0; i < size; i++) {
                Shark shark = sharkList.poll();
                String sharkRC = shark.r + " " + shark.c;
                Boolean exist = sharkExist.getOrDefault(sharkRC, false);
                if (exist) {
                    Shark existingShark = sharkMap.get(sharkRC);
                    // 지금 상어가 기존 상어보다 크기가 크면 잡아먹기
                    if (existingShark.size < shark.size) {
                        sharkMap.put(sharkRC, shark);
                        sharkList.remove(existingShark);
                        sharkList.add(shark);
                    }
                } else {
                    sharkExist.put(sharkRC, true);
                    sharkMap.put(sharkRC, shark);
                    sharkList.add(shark);
                }
            }
        }

        System.out.println(fishKing.score);
    }

    static class Shark {

        int r, c;
        int speed;
        int dir;
        int size;

        public Shark(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public void move() {
            int nr = r + dr[dir] * speed;
            int nc = c + dc[dir] * speed;

            // 격자 밖으로 이동할 경우
            if (!isValid(nr, nc)) {
                nr = nr < 0 ? nr * -1 : nr;
                nc = nc < 0 ? nc * -1 : nc;
                if (nr >= maxR) {
                    dir = ((nr / (maxR - 1)) + 1) % 2;
                    if (dir == 0) {
                        r = maxR - 1 - (nr % (maxR - 1));
                    } else {
                        r = nr % (maxR - 1);
                    }
                    c = nc;
                    return;
                } else if (nc >= maxC) {
                    dir = (nc / (maxC - 1)) % 2 + 2;
                    if (dir == 3) {
                        c = maxC - 1 - (nc % (maxC - 1));
                    } else {
                        c = nc % (maxC - 1);
                    }
                    r = nr;
                    return;
                }
                if (dir % 2 == 0) {
                    dir += 1;
                } else {
                    dir -= 1;
                }
            }
            r = nr;
            c = nc;
        }

        private boolean isValid(int r, int c) {
            if (0 <= r && r < maxR && 0 <= c && c < maxC) {
                return true;
            }
            return false;
        }
    }

    static class FishKing {

        int c = -1;
        int score = 0;

        public void fishing() {
            for (int i = 0; i < maxR; i++) {
                String sharkRC = i + " " + c;
                Boolean exist = sharkExist.getOrDefault(sharkRC, false);
                if (!exist) {
                    continue;
                }
                Shark shark = sharkMap.get(sharkRC);
                score += shark.size;

                sharkExist.put(sharkRC, false);
                sharkMap.put(sharkRC, null);

                return;
            }
        }

        public void move() {
            c += 1;
        }
    }
}