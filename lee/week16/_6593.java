package lee.week16;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2024.03.08
 * 6593 상범빌딩
 */

class _6593 {

    static int[] dr = {-1, 0, 1, 0, 0, 0}; // 북 서 남 동 위 아래
    static int[] dc = {0, -1, 0, 1, 0, 0};
    static int[] df = {0, 0, 0, 0, -1, 1};
    static int L, R, C;
    static char[][][] building;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (L != 0 || R != 0 || C != 0) {

            building = new char[R][C][L];

            SangBum sb = new SangBum();

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String lineInput = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[j][k][i] = lineInput.charAt(k);
                        if (building[j][k][i] == 'S') {
                            sb.row = j;
                            sb.column = k;
                            sb.floor = i;
                        }
                    }
                }
                br.readLine();
            }

            bfs(sb);

            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }

    }

    static void bfs(SangBum sb) {
        Queue<SangBum> q = new LinkedList<>();

        q.add(sb);
        while (!q.isEmpty()) {
            SangBum poll = q.poll();

            int r = poll.row;
            int c = poll.column;
            int f = poll.floor;

            for (int i = 0; i < 6; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nf = f + df[i];

                // validCheck
                if (!isValid(nr, nc, nf)) {
                    continue;
                }

                // endCheck
                if (building[nr][nc][nf] == 'E') {
                    System.out.println("Escaped in " + poll.time + " minute(s).");
                    return;
                }

                q.add(new SangBum(nr, nc, nf, poll.time + 1));
                building[nr][nc][nf] = '#';
            }
        }
        System.out.println("Trapped!");
    }

    static boolean isValid(int r, int c, int f) {
        if (0 <= r && r < R && 0 <= c && c < C && 0 <= f && f < L) {
            if (building[r][c][f] != '#') {
                return true;
            }
        }
        return false;
    }

    static class SangBum {

        int row;
        int column;
        int floor;

        int time = 1;

        public SangBum() {
        }

        public SangBum(int row, int column, int floor, int time) {
            this.row = row;
            this.column = column;
            this.floor = floor;
            this.time = time;
        }
    }

}

