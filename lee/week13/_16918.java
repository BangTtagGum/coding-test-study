package lee.week13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2023.12.20 16918 봄버맨
 */
public class _16918 {

    static int r;
    static int c;
    static char[][] map;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        int t = sc.nextInt();

        map = new char[r][c];
        String empty = sc.nextLine();

        for (int i = 0; i < r; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        Queue<ArrayList<Point>> totalBombs = new LinkedList<>();

        // 3초후에 폭탄이 터져야 하기에 2초의 경우 빈 폭탄 배열을 추가
        totalBombs.add(new ArrayList<>()); // 2초

        int currentTime = 2;
        while (currentTime <= t) {
            ArrayList<Point> bombs = new ArrayList<>();
            // 이번 초에 터져야 할 폭탄 폭파
            ArrayList<Point> poll = totalBombs.poll();
            for (Point point : poll) {
                explosion(point);
            }

            if (currentTime % 2 == 0) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (map[i][j] == 'O') {
                            bombs.add(new Point(i, j));
                        } else {
                            map[i][j] = 'O';
                        }
                    }
                }
                // 이번 초에 심은 폭탄 리스트 큐에 추가
                totalBombs.add(bombs);
                totalBombs.add(new ArrayList<>());
            }

            currentTime++;
        }

        printMap();
        sc.close();
    }

    private static void explosion(Point point) {
        int[] dr = {0, 1, 0, -1, 0};
        int[] dc = {1, 0, -1, 0, 0};

        for (int i = 0; i < 5; i++) {
            int nr = point.r + dr[i];
            int nc = point.c + dc[i];
            if (isValid(nr, nc)) {
                map[nr][nc] = '.';
            }
        }
    }

    public static boolean isValid(int i, int j) {

        if (0 <= i && i < r && 0 <= j && j < c) {
            return true;
        }
        return false;
    }

    public static void printMap() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }


    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
