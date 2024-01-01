package ju.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 단지번호붙이기
 *
 * 메모리: 14532KB
 * 시간: 144ms
 */
public class _2667 {

    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static int n, house;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        graph = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        for(int i=1; i<n+1; i++){
            String width = br.readLine();
            for(int j=1; j<n+1; j++){
                graph[i][j] = width.charAt(j-1) - '0';
            }
        }

        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(graph[i][j] ==1 && !visited[i][j]){
                    house = 1;
                    visited[i][j] = true;
                    count++;
                    bfs(i,j);
                    // dfs(i, j);
                    list.add(house);
                }
            }
        }

        System.out.println(count);
        list.sort(Comparator.naturalOrder());
        for(int i=0; i<count; i++){
            System.out.println(list.get(i));
        }
    }

    private static void bfs(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a,b});

        while(!q.isEmpty()){
            int[] val = q.poll();
            int x = val[0];
            int y = val[1];
            for(int i=0; i<4; i++){
                int x2 = x + dx[i];
                int y2 = y + dy[i];

                if(x2>0 && y2>0 && x2<n+1 && y2<n+1 && !visited[x2][y2] && graph[x2][y2] == 1){
                    visited[x2][y2] = true;
                    q.offer(new int[]{x2,y2});
                    house++;
                }
            }
        }

    }

    // private static void dfs(int a, int b) {

    //   for(int i=0; i<4; i++){
    //     int x = a + dx[i];
    //     int y = b + dy[i];

    //     if(x>0 && y > 0 && x<n+1 && y<n+1 && !visited[x][y] && graph[x][y] == 1){
    //       visited[x][y] = true;
    //       dfs(x, y);
    //       house++;
    //     }
    //   }
    // }
}
