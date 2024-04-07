package lee.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _15685 {

    static int x, y, d, g;
    static PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> {
        if (a.get(0) == b.get(0)) {
            return a.get(1) - b.get(1);
        }
        return a.get(0) - b.get(0);
    });


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            dragon(x, y, d, g);
        }

        int size = pq.size();
        Map<String, Boolean> map = new HashMap<>();
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> point = pq.poll();

            if (!map.getOrDefault(point.get(0) + " " + point.get(1), false)) {
                map.put(point.get(0) + " " + point.get(1), true);

                q.add(point);
            }
        }
        int checkX[] = {0, 1, 0, 1};
        int checkY[] = {0, 0, 1, 1};
        int answer = 0;
        size = q.size();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> point = q.poll();
            for (int j = 1; j < 4; j++) {
                int x = point.get(0) + checkX[j];
                int y = point.get(1) + checkY[j];
                if (!map.getOrDefault(x + " " + y, false)) {
                    break;
                }
                if (j == 3) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void dragon(int x, int y, int d, int g) {
        Deque<Integer> dq1 = new ArrayDeque<>();
        Deque<Integer> dq2 = new ArrayDeque<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        int curX = x;
        int curY = y;
        boolean toggle = true; // 번갈아가며 두개의 데크 사용

        pq.add(new ArrayList<>(Arrays.asList(curX, curY)));

        curX += dx[d];
        curY += dy[d];

        pq.add(new ArrayList<>(Arrays.asList(curX, curY)));

        dq1.addLast(d);

        while (g > 0) {
            g--;
            if (toggle) {
                toggle = false;
                int size = dq1.size();
                for (int i = 0; i < size; i++) {
                    int dir = dq1.pollLast();
                    int nextDir = (dir + 1) % 4;

                    // 거쳐간 좌표들 삽입
                    curX += dx[nextDir];
                    curY += dy[nextDir];
                    pq.add(new ArrayList<>(Arrays.asList(curX, curY)));

                    // 다음 드래곤 커브 생성
                    dq2.addFirst(dir);
                    dq2.addLast(nextDir);
                }
            } else {
                toggle = true;
                int size = dq2.size();
                for (int i = 0; i < size; i++) {
                    int dir = dq2.pollLast();
                    int nextDir = (dir + 1) % 4;

                    // 거쳐간 좌표들 삽입
                    curX += dx[nextDir];
                    curY += dy[nextDir];
                    pq.add(new ArrayList<>(Arrays.asList(curX, curY)));

                    // 다음 드래곤 커브 생성
                    dq1.addFirst(dir);
                    dq1.addLast(nextDir);
                }
            }
        }

    }

}