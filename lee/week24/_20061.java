package lee.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20061 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        MonominoDomino domino = new MonominoDomino();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            domino.addBlock(t, r, c);
        }
        System.out.println(domino.score);
        System.out.println(domino.getBlockCnt());


    }

    static class MonominoDomino {

        boolean[][] green = new boolean[6][4];
        boolean[][] blue = new boolean[4][6];
        int score;

        public void addBlock(int t, int r, int c) {
            if (t == 1) {
                //파랑
                int nc = 0;
                while (nc + 1 < 6 && !blue[r][nc + 1]) {
                    nc++;
                }

                blue[r][nc] = true;

                //점수 체크
                boolean getScore = true;
                for (int i = 0; i < 4; i++) {
                    if (!blue[i][nc]) {
                        getScore = false;
                        break;
                    }
                }
                if (getScore) {
                    arrangeColumns(nc);
                    score++;
                }

                //연한 부분 체크
                if (nc <= 1) {
                    arrangeColumns(5);
                }

                //초록
                int nr = 0;
                while (nr + 1 < 6 && !green[nr + 1][c]) {
                    nr++;
                }

                green[nr][c] = true;

                //점수 체크
                getScore = true;
                for (int i = 0; i < 4; i++) {
                    if (!green[nr][i]) {
                        getScore = false;
                        break;
                    }
                }
                if (getScore) {
                    arrangeRows(nr);
                    score++;
                }

                //연한 부분 체크
                if (nr <= 1) {
                    arrangeRows(5);
                }
            } else if (t == 2) {
                //파랑
                int nc = 0;
                while (nc + 2 < 6 && !blue[r][nc + 2]) {
                    nc++;
                }

                blue[r][nc] = true;
                blue[r][nc + 1] = true;

                //점수 체크
                boolean getScore;
                for (int i = 1; i >= 0; i--) {
                    getScore = true;
                    for (int j = 0; j < 4; j++) {
                        if (!blue[j][nc + i]) {
                            getScore = false;
                            break;
                        }
                    }
                    if (getScore) {
                        arrangeColumns(nc + i);
                        nc++;
                        score++;
                    }
                }

                //연한 부분 체크
                while (nc <= 1) {
                    arrangeColumns(5);
                    nc++;
                }

                //초록
                int nr = 0;
                while (nr + 1 < 6 && !green[nr + 1][c] && !green[nr + 1][c + 1]) {
                    nr++;
                }

                green[nr][c] = true;
                green[nr][c + 1] = true;

                //점수 체크
                getScore = true;
                for (int i = 0; i < 4; i++) {
                    if (!green[nr][i]) {
                        getScore = false;
                        break;
                    }
                }
                if (getScore) {
                    arrangeRows(nr);
                    score++;
                }

                //연한 부분 체크
                if (nr <= 1) {
                    arrangeRows(5);
                }

            } else {
                //파랑
                int nc = 0;
                while (nc + 1 < 6 && !blue[r][nc + 1] && !blue[r + 1][nc + 1]) {
                    nc++;
                }

                blue[r][nc] = true;
                blue[r + 1][nc] = true;

                //점수 체크
                boolean getScore = true;
                for (int i = 0; i < 4; i++) {
                    if (!blue[i][nc]) {
                        getScore = false;
                        break;
                    }
                }
                if (getScore) {
                    arrangeColumns(nc);
                    score++;
                }

                //연한 부분 체크
                if (nc <= 1) {
                    arrangeColumns(5);
                }

                //초록
                int nr = 0;
                while (nr + 2 < 6 && !green[nr + 2][c]) {
                    nr++;
                }

                green[nr][c] = true;
                green[nr + 1][c] = true;

                //점수 체크
                for (int i = 1; i >= 0; i--) {
                    getScore = true;
                    for (int j = 0; j < 4; j++) {
                        if (!green[nr + i][j]) {
                            getScore = false;
                            break;
                        }
                    }
                    if (getScore) {
                        arrangeRows(nr + i);
                        nr++;
                        score++;
                    }
                }

                //연한 부분 체크
                while (nr <= 1) {
                    arrangeRows(5);
                    nr++;
                }
            }
        }

        private void arrangeRows(int r) {
            for (int i = r; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    green[i][j] = green[i - 1][j];
                }
            }
            for (int i = 0; i < 4; i++) {
                green[0][i] = false;
            }
        }

        private void arrangeColumns(int c) {
            for (int i = 0; i < 4; i++) {
                for (int j = c; j > 0; j--) {
                    blue[i][j] = blue[i][j - 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                blue[i][0] = false;
            }
        }

        public int getBlockCnt() {
            int blueBlockCnt = 0;
            int greenBlockCnt = 0;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    if (blue[i][j]) {
                        blueBlockCnt++;
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (green[i][j]) {
                        greenBlockCnt++;
                    }
                }
            }

            return blueBlockCnt + greenBlockCnt;
        }
    }
}


