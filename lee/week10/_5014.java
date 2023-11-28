package lee.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.11.26
 * 스타트링크
 */
public class _5014 {

    static int f;
    static int s;
    static int g;
    static int ud[] = new int[2];

    static boolean visited[];
    static int count;
    static int floorNow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        ud[0] = Integer.parseInt(st.nextToken());
        ud[1] = -1 * Integer.parseInt(st.nextToken());

        visited = new boolean[f + 1];
        visited[s] = true;

        if (s == g) {
            System.out.println(0);
            return;
        }

        bfs();


    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        floorNow = s;
        while (!q.isEmpty()) {
            count++;
            int len = q.size();
            for (int i = 0; i < len; i++) {
                floorNow = q.poll();
                for (int j = 0; j < 2; j++) {
                    int move = ud[j];
                    if (isValid(floorNow + move)) {
                        if (floorNow + move == g) {
                            System.out.println(count);
                            return ;
                        }
                        q.add(floorNow + move);
                        visited[floorNow + move] = true;
                    }
                }
            }
        }
        System.out.println("use the stairs");
    }

    static boolean isValid(int floor) {
        if (1 <= floor && floor <= f && !visited[floor]) {
            return true;
        }
        return false;
    }
}
