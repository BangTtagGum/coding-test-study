package lee.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.09.16 17090 미로 탈출하기
 */
public class _17090 {

    static int n;
    static int m;


    // 미로
    static char[][] map = new char[500][500];
    // 나갈 수 있는 칸 표시 0은 아직 모름 1은 탈출 가능 2 는 탈출불가능
    static int[][] canOut = new int[500][500];
    //현재 탐색하고 있는 칸 체크
    static boolean[][] nowVisiting = new boolean[500][500];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String val = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = val.charAt(j);
            }
        }

        // 하나씩 탐색하면서 탈출 가능한지 체크
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canOut[i][j] == 0) { // 아직 탈출 불가능 가능 판정이 안났으면 탐색 시작
                    run(i, j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canOut[i][j] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    /**
     *
     * @param r 행
     * @param c 열
     * @return 탈출 가능여부 리턴 탈출성공 = 1, 탈출 실패 = 2
     */
    public static int run(int r, int c) {
        // 탈출 성공 여부 체크
        if (isOut(r, c)) {
            return 1;
        }
        // 이미 탐색이 끝나서 탈출 가능, 불가능 판정 나오면 값 건내주기
        if (canOut[r][c] != 0) {
            return canOut[r][c];
        }
        // 처음으로 되돌아올 경우
        if (nowVisiting[r][c]) {
            return 2;
        }

        // 방문하고 있는 칸이다 체크
        nowVisiting[r][c] = true;
        switch (map[r][c]) {
            case 'U':
                canOut[r][c] = run(r - 1, c);
                nowVisiting[r][c] = false; // 나갈때 방문하고있지 않다고 체크
                return canOut[r][c];
            case 'R':
                canOut[r][c] = run(r, c + 1);
                nowVisiting[r][c] = false;
                return canOut[r][c];
            case 'D':
                canOut[r][c] = run(r + 1, c);
                nowVisiting[r][c] = false;
                return canOut[r][c];
            case 'L':
                canOut[r][c] = run(r, c - 1);
                nowVisiting[r][c] = false;
                return canOut[r][c];
            default:
                return 0;
        }
    }

    // 탈출여부 체크 함수
    public static boolean isOut(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return true;
        }
        return false;
    }
}

