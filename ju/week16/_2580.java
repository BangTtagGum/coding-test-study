package ju.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스도쿠
 *
 * 메모리: 25752KB
 * 시간: 464ms
 */
public class _2580 {
    private static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }

    private static void sudoku(int row, int col) {
        //오른쪽으로 한칸씩 체크 후 다음 줄
        if (col == 9) {
            sudoku(row + 1, 0);
            return;
        }

        //전체 다 체크 완료
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        if (board[row][col] == 0) {
            //가로, 세로, 3x3 체크
            for (int i = 1; i <= 9; i++) {
                if (check(row, col, i)){
                    board[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            board[row][col] = 0;
            return;
        }

        sudoku(row, col + 1);
    }

    private static boolean check(int row, int col, int number) {

        //가로 체크
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        //세로 체크
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        //3x3 체크
        int width = (row / 3) * 3;
        int height = (col / 3) * 3;

        for (int i = width; i < width + 3; i++) {
            for (int j = height; j < height + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }
}
