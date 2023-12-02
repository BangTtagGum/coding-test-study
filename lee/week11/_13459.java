package lee.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.12.02
 * 구슬 탈출
 */
public class _13459 {

    static char[] command = {'U', 'R', 'D', 'L'};
    static int n;
    static int m;
    static char[][] boardMap;
    static boolean[][][][] redBlueVisited = new boolean[10][10][10][10]; // 거쳤던 위치 저장 608ms -> 128ms 로 시간 단축

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boardMap = new char[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                boardMap[i][j] = input.charAt(j);
            }
        }
        Board board = new Board(n, m, boardMap);

        Queue<Board> q = new LinkedList<>();
        q.add(board);
        int cnt = 1;
        while (!q.isEmpty()) {
            if (cnt > 10) {
                System.out.println(0);
                return;
            }
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Board poll = q.poll();
                for (int j = 0; j < 4; j++) {
                    if (poll.preCommand != command[j]) {
                        Board newBoard = new Board(poll);
                        if (newBoard.move(command[j])) {
                            if (!newBoard.blueGoal && newBoard.redGoal) {
                                System.out.println(1);
                                return;
                            }
                        } else {
                            int[] points = newBoard.redBluePoints();
                            if (!redBlueVisited[points[0]][points[1]][points[2]][points[3]]) {
                                q.add(newBoard);
                                redBlueVisited[points[0]][points[1]][points[2]][points[3]] = true;
                            }
                        }
                    }
                }
            }
            cnt++;
        }
        System.out.println(0);




    }

    public static void print(Board board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board.map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }



    static class Board {

        private int r;
        private int c;
        private char[][] map;
        private boolean redGoal;
        private boolean blueGoal;

        public char preCommand = 'X';

        public Board(int r, int c, char[][] map) {
            this.r = r;
            this.c = c;
            this.map = copyMap(map);
        }

        public Board(Board board) {
            this.r = board.r;
            this.c = board.c;
            this.map = copyMap(board.map);
            this.preCommand = board.preCommand;
        }

        public boolean move(char command) {
            switch (command) {
                case 'U':
                    return up();
                case 'R':
                    return right();
                case 'D':
                    return down();
                case 'L':
                    return left();
            }
            return false;
        }

        private boolean up() {
            boolean flag = false;
            for (int i = 1; i < r-1; i++) {
                for (int j = 1; j < c-1; j++) {
                    if (map[i][j] == 'R' || map[i][j] == 'B') {
                        int n = 1;
                        while (true) {
                            if (map[i - n][j] == '#' || map[i -n][j] == 'R' || map[i -n][j] == 'B') {
                                char tmp = map[i][j];
                                map[i][j] = '.';
                                map[i - n + 1][j] = tmp;
                                break;
                            }
                            if (map[i - n][j] == 'O') {
                                if(map[i][j] == 'R') {
                                    this.redGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                } else if (map[i][j] == 'B') {
                                    this.blueGoal = true;
                                    map[i][j] = '.';
                                    flag =  true;
                                }
                                break;
                            }
                            n++;
                        }
                    }
                }
            }
            this.preCommand = 'U';
            return flag;
        }

        private boolean right() {
            boolean flag = false;
            for (int i = 1; i < r-1; i++) {
                for (int j = c-2; j >= 1; j--) {
                    if (map[i][j] == 'R' || map[i][j] == 'B') {
                        int n = 1;
                        while (true) {
                            if (map[i][j+n] == '#' || map[i][j+n] == 'R' || map[i][j+n] == 'B') {
                                char tmp = map[i][j];
                                map[i][j] = '.';
                                map[i][j+n-1] = tmp;
                                break;
                            }
                            if (map[i][j+n] == 'O') {
                                if (map[i][j] == 'R') {
                                    this.redGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                } else if (map[i][j] == 'B') {
                                    this.blueGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                }
                                break;
                            }
                            n++;
                        }
                    }
                }
            }
            this.preCommand = 'R';
            return flag;

        }

        private boolean down() {
            boolean flag = false;
            for (int i = r-2; i >= 1; i--) {
                for (int j = 1; j < c-1; j++) {
                    if (map[i][j] == 'R' || map[i][j] == 'B') {
                        int n = 1;
                        while (true) {
                            if (map[i + n][j] == '#' || map[i +n][j] == 'R' || map[i +n][j] == 'B') {
                                char tmp = map[i][j];
                                map[i][j] = '.';
                                map[i + n - 1][j] = tmp;
                                break;
                            }
                            if (map[i + n][j] == 'O') {
                                if (map[i][j] == 'R') {
                                    this.redGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                } else if (map[i][j] == 'B') {
                                    this.blueGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                }
                                break;
                            }
                            n++;
                        }
                    }
                }
            }
            this.preCommand = 'D';
            return flag;


        }

        private boolean left() {
            boolean flag = false;
            for (int i = 1; i < r-1; i++) {
                for (int j = 1; j < c-1; j++) {
                    if (map[i][j] == 'R' || map[i][j] == 'B') {
                        int n = 1;
                        while (true) {
                            if (map[i][j-n] == '#' || map[i][j-n] == 'R' || map[i][j-n] == 'B') {
                                char tmp = map[i][j];
                                map[i][j] = '.';
                                map[i][j-n+1] = tmp;
                                break;
                            }
                            if (map[i][j-n] == 'O') {
                                if (map[i][j] == 'R') {
                                    this.redGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                } else if (map[i][j] == 'B') {
                                    this.blueGoal = true;
                                    map[i][j] = '.';
                                    flag = true;
                                }
                                break;
                            }
                            n++;
                        }
                    }
                }
            }
            this.preCommand = 'L';
            return flag;
        }

        private char[][] copyMap(char[][] map) {
            char[][] clone = new char[n][m];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    clone[i][j] = map[i][j];
                }
            }
            return clone;
        }
        public int[] redBluePoints() {
            int[] redBlueArray = new int[4];
            for (int i = 1; i < r-1; i++) {
                for (int j = 1; j < c-1; j++) {
                    if (map[i][j] == 'R') {
                        redBlueArray[0] = i;
                        redBlueArray[1] = j;
                    }else if (map[i][j] == 'B') {
                        redBlueArray[2] = i;
                        redBlueArray[3] = j;
                    }
                }
            }
            return redBlueArray;
        }
    }
}

