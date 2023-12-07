package ju.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 탈출
 *
 * 메모리: 14212KB
 * 시간: 124ms
 */
public class _3055 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] maps;
    static boolean[][] visited;
    static int r,c;
    static int result = Integer.MAX_VALUE;

    static Queue<int[]> rainQ = new LinkedList<>();
    static Queue<Beaver> beaverQ = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        maps = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String lines = br.readLine();
            for (int j = 0; j < c; j++) {
                char line = lines.charAt(j);
                if (line == 'S') {
                    beaverQ.offer(new Beaver(i, j, 0));
                    visited[i][j] = true;
                } else if (line == '*') {
                    rainQ.offer(new int[]{i, j});
                }
                maps[i][j] = line;
            }
        }

        bfs();
        System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
    }

    private static void bfs() {

        while (!beaverQ.isEmpty()) {
            //빗물 이동
            int rainSize = rainQ.size();
            for (int i = 0; i < rainSize; i++) {
                int[] poll = rainQ.poll();
                int x = poll[0];
                int y = poll[1];
                for (int j = 0; j < 4; j++) {
                    int posX = x + dx[j];
                    int posY = y + dy[j];

                    if (posX < 0 || posX >= r || posY < 0 || posY >= c || visited[posX][posY] || maps[posX][posY] != '.') continue;

                    maps[posX][posY] = '*';
                    rainQ.offer(new int[]{posX, posY});
                }
            }

            //고슴도치 이동
            int beaverSize = beaverQ.size();
            for (int i = 0; i < beaverSize; i++) {
                Beaver beaver = beaverQ.poll();
                int x = beaver.posX;
                int y = beaver.posY;
                int dist = beaver.dist;

                for (int j = 0; j < 4; j++) {
                    int posX = x + dx[j];
                    int posY = y + dy[j];

                    if (posX < 0 || posX >= r || posY < 0 || posY >= c || visited[posX][posY]) continue;

                    if (maps[posX][posY] == 'D') {
                        result = dist+1;
                        return;
                    } else if (maps[posX][posY] == '.'){
                        visited[posX][posY] = true;
                        beaverQ.offer(new Beaver(posX, posY, dist+1));
                    }
                }
            }
        }
    }

    static class Beaver {
        int posX;
        int posY;
        int dist;

        public Beaver(int posX, int posY, int dist) {
            this.posX = posX;
            this.posY = posY;
            this.dist = dist;
        }
    }
}
