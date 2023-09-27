package lee.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.09.27
 * 14890 경사로
 */

public class _14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int map[][] = new int[n][n];
        boolean isBridgeEmpty[][] = new boolean[n][n]; // 다리를 놓을 수 있는지

        // 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 다리 허용 판단 배열 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isBridgeEmpty[i][j] = true;
            }
        }


        int ans = 0;


        /**
         * 행 탐색
         * i 는 탐색중인 행
         */
        for (int i = 0; i < n; i++) {
            int floor = 0;
            boolean flag = true; // 길 건너기 성공 여부
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    floor = map[i][j]; // 현재 층 저장
                }
                if (floor - map[i][j] == 1) { // 층이 더 낮은 경우
                    if (j + l - 1 >= n) { // 경사가 밖으로 나가는 경우
                        flag = false;
                        break;
                    } else {
                        for (int k = 0; k < l; k++) {
                            if (map[i][j] != map[i][j + k]) { //경사를 놓으려는데 층이 다르거나 이미 다리가 놓인 경우
                                flag = false;
                                break;
                            }
                            isBridgeEmpty[i][j + k] = false;
                        }
                        if(!flag){
                            break;
                        }
                        floor = map[i][j]; // 한층 내려옴
                    }
                } else if (map[i][j]- floor == 1) { // 층이 높은 경우
                    if (j - l < 0) { // 다리가 맵 밖으로 나감
                        flag = false;
                        break;
                    } else {
                        for (int k = 1; k <=l; k++) {
                            if (map[i][j - 1] != map[i][j - k] || !isBridgeEmpty[i][j
                                    - k]) { // 경사 놓을 길 높이가 다르거나 이미 다리가 놓인경우
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                        floor = map[i][j];
                    }
                } else if (floor == map[i][j]) {
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) { // 무사히 길을 건넘
                ans++;
            }
            for (int j = 0; j < n; j++) { // 놓았던 다리 치우기
                isBridgeEmpty[i][j] = true;
            }
        }

        /**
         * 열 탐색
         * j 는 탐색중인 열
         */
        for (int j = 0; j < n; j++) {
            int floor = 0;
            boolean flag = true; // 길 건너기 성공 여부
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    floor = map[i][j]; // 현재 층 저장
                }
                if (floor - map[i][j] == 1) { // 층이 더 낮은 경우
                    if (i + l - 1 >= n) { // 경사가 밖으로 나가는 경우
                        flag = false;
                        break;
                    } else {
                        for (int k = 0; k < l; k++) {
                            if (map[i][j] != map[i+k][j]) { //경사를 놓으려는데 층이 다르거나 이미 다리가 놓인 경우
                                flag = false;
                                break;
                            }
                            isBridgeEmpty[i+k][j] = false;
                        }
                        if(!flag){
                            break;
                        }
                        floor = map[i][j]; // 한층 내려옴
                    }
                } else if (map[i][j]- floor == 1) { // 층이 높은 경우
                    if (i - l < 0) { // 다리가 맵 밖으로 나감
                        flag = false;
                        break;
                    } else {
                        for (int k = 1; k <=l; k++) {
                            if (map[i-1][j] != map[i -k][j] || !isBridgeEmpty[i-k][j]) { // 경사 놓을 길 높이가 다르거나 이미 다리가 놓인경우
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                        floor = map[i][j];
                    }
                } else if (floor == map[i][j]) {
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) { // 무사히 길을 건넘
                ans++;
            }
            for (int i = 0; i < n; i++) { // 놓았던 다리 치우기
                isBridgeEmpty[i][j] = true;
            }
        }
        System.out.println(ans);
    }
}
