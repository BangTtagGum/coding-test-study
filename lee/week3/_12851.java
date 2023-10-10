package lee.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.09.07
 * 12851 숨바꼭질 2
 *
 * 2023.09.28 재도전
 */
public class _12851 {

    static int n;
    static int k;
    static int minTime;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new int[200001][2]; // 0은 걸리는 최단 시간 1은 해당 시간의 경우의 수

        bfs();

        System.out.println(visited[k][0]);
        System.out.println(visited[k][1]);

    }

    public static void bfs() {
        minTime = Math.abs(n - k);
        Subin sb;
        Queue<Subin> q = new LinkedList<>();
        q.add(new Subin(n, 0));
        while (!q.isEmpty()) {
            sb = q.poll();
            if (sb.x > 200000 || sb.x < 0 || sb.cnt > minTime) {
                continue;
            }
            if (visited[sb.x][1] > 0) { //이미 방문한 위치여서 경우의 수가 0보다 클 경우
                if (sb.cnt < visited[sb.x][0]) {
                    visited[sb.x][1] = 1;
                    visited[sb.x][0] = sb.cnt;
                } else if (sb.cnt == visited[sb.x][0]) {
                    visited[sb.x][1]++;
                } else {
                    continue;
                }
            } else { //처음 방문하는 위치일 경우
                visited[sb.x][1] = 1;
                visited[sb.x][0] = sb.cnt;
            }
            if (sb.x == k) {
                minTime = visited[k][0];
                continue;
            }
            q.add(new Subin(sb.x * 2, sb.cnt + 1));
            q.add(new Subin(sb.x + 1, sb.cnt + 1));
            q.add(new Subin(sb.x - 1, sb.cnt + 1));
        }

    }

    static class Subin {

        int x;
        int cnt;

        public Subin(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }


}
