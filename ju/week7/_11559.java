package ju.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Puyo Puyo
 *
 * 가로: 6
 * 세로: 12
 *
 * 연쇄작용은 한번에 이뤄져야 하므로 전체 탐색 후 .변환  + .체크 후 위에 있는 puyo들을 아래로 내려야함
 *
 * 메모리 : 14176KB
 * 시간 : 124ms
 */
public class _11559 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] maps;
    static boolean[][] visited;
    static List<int[]> list;
    static int h = 12;
    static int w = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new char[h][w];
        for (int i = 0; i < h; i++) {
            String lines = br.readLine();
            for (int j = 0; j < w; j++) {
                maps[i][j] = lines.charAt(j);
            }
        }

        int cnt = 0;
        while (true) {
            visited = new boolean[h][w];
            list = new ArrayList<>();
            boolean flag = false;
            //전체 탐색
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (maps[i][j] != '.' && !visited[i][j]){
                        list.add(new int[]{i , j});
                        bfs(i, j);

                        //4개 이상 연쇄작용 시 A로 변환
                        if (list.size() >= 4) {
                            flag = true;
                            for (int k = 0; k < list.size(); k++) {
                                maps[list.get(k)[0]][list.get(k)[1]] = '.';
                            }
                        }
                        list.clear();
                    }
                }
            }

            //아래로 전체 내리기
            if (flag) {
                cnt++;
                fall();
            } else {
                //연쇄없으면 return
                System.out.println(cnt);
                return;
            }
        }
    }

    private static void fall() {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            for (int j = h-1; j >= 0; j--) {
                if (maps[j][i] != '.') {
                    //puyo들만 q에 넣기
                    q.offer(maps[j][i]);
                }
            }

            int length = q.size();

            //puyo들은 바닥
            for (int j = 0; j < length; j++) {
                maps[h-1-j][i] = q.poll();
            }

            //나머지는 .으로 채우기
            for (int j = length; j < h; j++) {
                maps[h-1-j][i] = '.';
            }
        }
    }

    private static void bfs(int posH, int posW) {
        Queue<Puyo> q = new LinkedList<>();
        q.offer(new Puyo(posW, posH, maps[posH][posW]));
        visited[posH][posW] = true;

        while (!q.isEmpty()) {
            Puyo puyo = q.poll();
            int x = puyo.x;
            int y = puyo.y;

            for (int i = 0; i < 4; i++) {
                int posX = x + dx[i];
                int posY = y + dy[i];

                //범위, 방문 체크
                if (posX < 0 || posX >= w || posY < 0 || posY >= h || visited[posY][posX]) continue;
                //색상 체크
                if (puyo.color != maps[posY][posX]) continue;

                list.add(new int[]{posY, posX});
                visited[posY][posX] = true;
                q.offer(new Puyo(posX, posY, maps[posY][posX]));
            }
        }
    }

    static class Puyo {
        int x;
        int y;
        char color;

        public Puyo(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
