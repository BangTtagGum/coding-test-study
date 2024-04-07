package ju.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2048(Easy)
 *
 * 방향에 따른 모든 경우의 수를 다 찾아야함
 * 메모리: 20800KB
 * 시간: 184ms
 */
public class _12100 {
    static int n;
    static int[][] board;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(0);
        System.out.println(answer);
    }

    private static void game(int cnt) {
        if (cnt == 5) {
            //최대값 비교해야함
            findMax();
            return;
        }

        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = board[i].clone();
        }
        for (int i = 0; i < 4; i++) {
            //이동
            move(i);
            game(cnt + 1);
            //board초기화
            for (int j = 0; j < n; j++) {
                board[j] = copy[j].clone();
            }
        }
    }

    private static void findMax() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, board[i][j]);
            }
        }
    }

    private static void move(int dir) {
        switch (dir) {
            //위
            case 0:
                for (int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (board[j][i] != 0) {
                            //같은 블럭일시
                            if (block == board[j][i]) {
                                board[index - 1][i] = block * 2;
                                block = 0;
                                board[j][i] = 0;
                            }
                            //다른 블럭일시
                            else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            //오른쪽
            case 1:
                for (int i = 0; i < n; i++) {
                    int block = 0;
                    int index = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (board[i][j] != 0) {
                            if (block == board[i][j]) {
                                board[i][index + 1] = block * 2;
                                block = 0;
                                board[i][j] = 0;
                            }
                            else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            //아래
            case 2:
                for (int i = 0; i < n; i++) {
                    int index = n - 1;
                    int block = 0;
                    for (int j = n - 1; j >= 0; j--) {
                        if (board[j][i] != 0) {
                            if (block == board[j][i]) {
                                board[index + 1][i] = block * 2;
                                block = 0;
                                board[j][i] = 0;
                            }
                            else {
                                block = board[j][i];
                                board[j][i] = 0;
                                board[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            //왼쪽
            case 3:
                for (int i = 0; i < n; i++) {
                    int index = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (board[i][j] != 0) {
                            if (block == board[i][j]) {
                                board[i][index - 1] = block * 2;
                                block = 0;
                                board[i][j] = 0;
                            }
                            else {
                                block = board[i][j];
                                board[i][j] = 0;
                                board[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
        }
    }
}
