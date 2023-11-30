package ju.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 스타트링크
 *
 * 풀이
 * 1차: 그리디 나누기
 * 반례: 6 1 6 3 2
 * 층수를 올라가고 내려가고 다시 올라가는 경우도 따져야함
 *
 * 2차: bfs를 활용한 완전탐색
 * 해당 층에 가장 빨리 도달하도록 탐색
 *
 * 메모리: 58600KB
 * 시간: 284ms
 */
public class _5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        if (u > 1000000) {
            System.out.println("use the stairs");
            System.exit(0);
        }

        int max = f + 1;
        int[] floor = new int[max];
        boolean[] visited = new boolean[max];

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        while (!q.isEmpty()) {

            if (visited[g]) break;

            int x = q.poll();
            int upStair = x + u;
            int downStair = x - d;

            if (upStair < max && !visited[upStair]) {
                floor[upStair] = floor[x] + 1;
                visited[upStair] = true;
                q.offer(upStair);
            }

            if (downStair > 0 && !visited[downStair]) {
                floor[downStair] = floor[x] + 1;
                visited[downStair] = true;
                q.offer(downStair);
            }
        }

        System.out.println(visited[g] ? floor[g] : "use the stairs");
    }
}
