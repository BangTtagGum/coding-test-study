package lee.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.09.19 15684 사다리 조작
 */
public class _15684 {

    static int r;
    static int c;
    static int min = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());   //세로선
        int m = Integer.parseInt(st.nextToken());   //가로선의 개수
        r = Integer.parseInt(st.nextToken());   //가로선을 놓을 수 있는 위치의 수

        boolean[][] sadari = new boolean[r+1][c+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sadari[r][c] = true;
        }

        putSadari(0,0,sadari,0);

        if (min == 4) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    static void putSadari(int row,int col,boolean[][] sadari,int num) {
        if (num >= min) {
            return;
        }
        if (sadariValidCheck(sadari)) {
            min = num;
            return;
        }
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j < c ; j++) {
                if (i > row || (i == row && j > col)) {
                    if (!sadari[i][j] && !sadari[i][j - 1] && !sadari[i][j + 1]) {
                        sadari[i][j] = true;
                        putSadari(i, j, sadari, num + 1);
                        sadari[i][j] = false;
                    }
                }
            }
        }
    }

    static boolean sadariValidCheck(boolean[][] sadari) {
        for (int i = 1; i <= c; i++) {
            int k = i;
            for (int j = 1; j <= r; j++) {
                if (sadari[j][k]) {
                    k++;
                } else if (sadari[j][k - 1]) {
                    k--;
                }
            }
            if (k != i) {
                return false;
            }
        }
        return true;
    }
}
