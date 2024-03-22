package lee.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {

    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class _2580 {

    public static int[][] board = new int[9][9];
    public static int[][] answer = new int[9][9];
    public static ArrayList<Point> array = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    array.add(new Point(i, j));
                }
            }
        }

        fill(0);

        print();
    }

    static void fill(int idx) {
        if (answer[0][0] != 0) {
            return;
        }
        if (idx == array.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    answer[i][j] = board[i][j];
                }
            }
            return;
        }
        Point pop = array.get(idx);
        for (int j = 1; j <= 9; j++) {
            if (validCheck(pop.r, pop.c, j)) {
                board[pop.r][pop.c] = j;
                fill(idx + 1);
                board[pop.r][pop.c] = 0;
            }
        }
    }

    private static boolean validCheck(int row, int col, int val) {

        // 가로줄 검증
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }


    static void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
