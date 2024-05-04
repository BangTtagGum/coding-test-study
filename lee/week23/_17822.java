package lee.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17822 {

    static int n, m, t;
    static double total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        Circle[] circles = new Circle[n];

        for (int i = 0; i < n; i++) {
            int[] arr = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            circles[i] = new Circle(i + 1, arr);
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // x의 배수만 회전
            for (int j = 0; j < n; j++) {
                if ((j + 1) % x == 0) {
                    circles[j].turn(d, k);
                }
            }

            // 인접한 수 중 동일한 수 체크
            boolean[][] check = new boolean[n][m];

            //디버깅 용 이차원배열
            int[][] debugArray = new int[n][m];
            for (int j = 0; j < n; j++) {
                debugArray[j] = circles[j].numberArray;
            }
            // 동일한 수 체크
            for (int j = 0; j < n; j++) {
                Circle currentCircle = circles[j];
                int[] array = currentCircle.numberArray;
                int currentAxis = currentCircle.axis;

                // 원 안에서 인접한 수
                for (int l = 0; l < m; l++) {
                    if (array[l] != 0 && array[l] == array[(l + 1) % m]) {
                        check[j][l] = true;
                        check[j][(l + 1) % m] = true;
                    }
                }

                // 작은 판과 체크
                int adjacent = j - 1;
                if (0 <= adjacent && adjacent < n) {
                    Circle innerCircle = circles[adjacent];
                    int[] innerArray = innerCircle.numberArray;
                    int innerAxis = innerCircle.axis;
                    for (int l = 0; l < m; l++) {
                        // 안쪽 원과 현재 원의 인접한 수가 같을 경우
                        if (array[(l + currentAxis) % m] != 0
                                && array[(l + currentAxis) % m] ==
                                innerArray[(l + innerAxis) % m]) {
                            check[j][(l + currentAxis) % m] = true;
                            check[adjacent][(l + innerAxis) % m] = true;
                        }
                    }
                }

                // 큰 판과 체크
                adjacent = j + 1;
                if (0 <= adjacent && adjacent < n) {
                    Circle outerCircle = circles[adjacent];
                    int[] outerArray = outerCircle.numberArray;
                    int outerAxis = outerCircle.axis;
                    for (int l = 0; l < m; l++) {
                        // 안쪽 원과 현재 원의 인접한 수가 같을 경우
                        if (array[(l + currentAxis) % m] != 0
                                && array[(l + currentAxis) % m] ==
                                outerArray[(l + outerAxis) % m]) {
                            check[j][(l + currentAxis) % m] = true;
                            check[adjacent][(l + outerAxis) % m] = true;
                        }
                    }
                }
            }

            //인접한 수 제거
            boolean flag = false;
            total = 0;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                Circle circle = circles[j];
                for (int l = 0; l < m; l++) {
                    if (check[j][l]) {
                        flag = true;
                        circle.numberArray[l] = 0;
                    }
                    if (circle.numberArray[l] != 0) {
                        cnt++;
                        total += circle.numberArray[l];
                    }
                }
            }

            // 인접한 수가 하나도 없을 경우 숫자 조정
            if (!flag) {
                double avg = total / cnt;
                total = 0;
                for (int j = 0; j < n; j++) {
                    Circle circle = circles[j];
                    for (int l = 0; l < m; l++) {
                        if (circle.numberArray[l] != 0) {
                            if (circle.numberArray[l] > avg) {
                                circle.numberArray[l]--;
                            } else if (circle.numberArray[l] < avg) {
                                circle.numberArray[l]++;
                            }
                            total += circle.numberArray[l];
                        }
                    }
                }
            }
        }
        System.out.println((int) total);
    }

    static class Circle {

        int r;
        int[] numberArray;
        int axis = 0; // 중심 축

        public Circle(int r, int[] arr) {
            this.r = r;
            this.numberArray = arr;
        }

        public void turn(int dir, int k) {
            // 시계 방향
            if (dir == 0) {
                axis = (axis + m - k) % m;
            } else { //반시계 방향
                axis = (axis + k) % m;
            }
        }

        public void updateArray(int[] arr) {
            numberArray = arr;
        }
    }
}


