package ju.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사다리 조작
 *
 * 메모리: 18628kb
 * 시간: 1016ms
 */
public class _15684 {
    private static int n;
    private static int h;
    private static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   //세로선 개수
        int m = Integer.parseInt(st.nextToken());   //놓여져있는 가로선 개수
        h = Integer.parseInt(st.nextToken());   //가로선 개수

        ladder = new int[h+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 1;       //사다리가 우측방향
            ladder[a][b+1] = -1;    //사다리가 좌측방향
        }

        //사다리는 최대 3개까지
        for (int i = 0; i < 4; i++) {
            dfs(0, i);
        }

        System.out.println(-1);
    }

    private static void dfs(int setLadderCnt, int ladderCnt) {

        if (setLadderCnt == ladderCnt) {
            //사다리 게임 시작
            if (play()) {
                System.out.println(ladderCnt);
                System.exit(0);
            }
            return;
        }

        //사다리를 놓는 모든 경우의 수 체크
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (ladder[i][j] == 0 && ladder[i][j+1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j+1] = -1;
                    dfs(setLadderCnt+1, ladderCnt);
                    ladder[i][j] = 0;
                    ladder[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean play() {
        for (int i = 1; i <= n; i++) {
            int hPos = 1;
            int nPos = i;
            while (true) {

                //마지막에 도달했을때
                if (hPos == h+1) {
                    if (nPos != i) {
                        return false;
                    }
                    break;
                }

                if (ladder[hPos][nPos] == 1) {
                    nPos++;
                } else if (ladder[hPos][nPos] == -1) {
                    nPos--;
                }
                hPos++;
            }
        }

        return true;
    }
}
