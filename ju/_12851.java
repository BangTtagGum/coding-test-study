package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숨바꼭질2
 *
 * 숨바꼭질1과 동일하게 bfs,dfs 활용해서 풀이
 * 5 17
 * 5 10 9 18 17 -> 4
 * 5 4 8 17 17 -> 4
 *
 * 방법은 총 2가지
 *
 * 1 3
 * 1+1+1
 * 1*2+1
 *
 * 반례
 * 2가지가 나와야하는데 이전 그래프의 값이 같으면 Queue에 안들어감  -> 해당 그래프에 위치한 값이 같을 경우 중복허용
 */
public class _12851 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n != k) bfs(n, k);
        else {
            System.out.println(0);   //시간
            System.out.println(1);        //방법
        }
    }

    private static void bfs(int n, int k) {
        int[] graph = new int[100001];
        graph[n] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        int num = 0;
        int[] dx = {1, -1, 2};
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {

            int nowPos = q.poll();

            //그래프 값이 최소값을 넘겼을 때
            if (graph[nowPos] > min) break;

            for (int i = 0; i < 3; i++) {

                int x;
                if (i == 2) x = nowPos * dx[i];
                else x = nowPos + dx[i];

                //x범위 초과
                if (x < 0 || x >= 100001) continue;

                //방법
                if (x == k) {
                    num++;
                    min = graph[nowPos];
                }

                //graph[x]==0 은 첫 방문 / 첫 방문이 아니라면 현재 들어있는 값과 같을 경우에만
                if (graph[x] == 0 || graph[nowPos] + 1 == graph[x]) {
                    graph[x] = graph[nowPos] + 1;
                    q.offer(x);
                }
            }
        }

        System.out.println(graph[k]);   //시간
        System.out.println(num);        //방법
    }
}
