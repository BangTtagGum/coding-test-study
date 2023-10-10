package ju.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숨바꼭질
 *
 * 5 17
 * 5  10  9  18  17  -> 4번만에 도달 가능 -> bfs dfs로 모든 경우의 수를 탐색해야함
 */
public class _1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n, k);
    }

    private static void bfs(int n, int k) {
        //위치와 시간
        int[] graph = new int[100001];
        graph[n] = 0;
        //방문여부
        boolean[] visited = new boolean[100001];
        visited[n] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        int[] dx = {1, -1, 2};
        while (!q.isEmpty()) {
            int nowPos = q.poll();

            //dx의 경로만큼 반복
            for (int i = 0; i < 3; i++) {
                int x;
                if (i==2) x = nowPos * dx[i];   //x*2의 경우
                else x = nowPos + dx[i];        //x+1, x-1의 경우

                //이미 방문했을 경우엔 또 방문할 필요가 없음
                if(x >= 0 && x < 100001 && !visited[x]){
                    graph[x] = graph[nowPos] + 1;
                    q.offer(x);
                    visited[x] = true;
                }
            }
        }

        System.out.println(graph[k]);
    }
}
