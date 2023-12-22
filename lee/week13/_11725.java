package lee.week13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2023.12.14
 * 트리의 부모 찾기
 */
public class _11725 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();


        Pair[] pairList = new Pair[n - 1];

        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (nodeMap.get(a) == null) {
                Node node = new Node(a);
                node.nodeList.add(b);
                nodeMap.put(a, node);
            }else{
                Node node = nodeMap.get(a);
                node.nodeList.add(b);
                nodeMap.put(a, node);
            }
            if (nodeMap.get(b) == null) {
                Node node = new Node(b);
                node.nodeList.add(a);
                nodeMap.put(b, node);
            }else{
                Node node = nodeMap.get(b);
                node.nodeList.add(a);
                nodeMap.put(b, node);
            }
            pairList[i] = new Pair(a, b);
        }

        int[] parent = new int[n+1];

        Queue<Node> q = new LinkedList<>();
        q.add(nodeMap.get(1));
        parent[1] = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Integer i : node.nodeList) {
                if (parent[i] == 0) {
                    parent[i] = node.data;
                    q.add(nodeMap.get(i));
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }












    }

    static class Pair implements Comparable<Pair>{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.a > this.b) {
                int tmp = this.a;
                this.a = this.b;
                this.b = tmp;
            }
            return this.a - o.a;
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
