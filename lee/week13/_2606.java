package lee.week13;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 2023.12.15
 * 바이러스
 */
public class _2606 {

    static boolean [][] graph;
    static boolean[] visited;
    static int totalComputerCnt;
    static int answer;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        totalComputerCnt = sc.nextInt();

        graph = new boolean[totalComputerCnt+1][totalComputerCnt+1];
        visited = new boolean[totalComputerCnt+1];

        int edgeCnt = sc.nextInt();

        for (int i = 0; i < edgeCnt; i++) {
            int nodeA = sc.nextInt();
            int nodeB = sc.nextInt();

            graph[nodeA][nodeB] = true;
            graph[nodeB][nodeA] = true;

        }
        visited[1] = true;
        dfs(1);
        System.out.println(answer);



        sc.close();


    }

    private static void dfs(int computerNum) {
        for (int i = 1; i <= totalComputerCnt ; i++) {
            if (graph[computerNum][i] && !visited[i]) {
                visited[i] = true;
                dfs(i);
                answer++;
            }
        }
    }

    static class Node{
        int data;
        ArrayList<Integer> nodeList = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }
    }
}

