package lee.week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class _20171 {
    static StringTokenizer st;
    static boolean[] visited;
    static int count = 0;
    static Map<Integer, List<Integer>> graph;
    static Set<Integer> apartComplex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        apartComplex = new HashSet<>();
        st = new StringTokenizer(br.readLine());

        int ac1 = -1;
        for (int i = 0; i < k; i++) {
            Integer complexNum = Integer.valueOf(st.nextToken());
            apartComplex.add(complexNum);

            if (ac1 == -1) {
                ac1 = complexNum;
            }
        }



        visited = new boolean[n + 1];
        dfs(ac1);


        System.out.println(count);
    }

    private static boolean dfs(int i) {
        visited[i] = true;
        boolean flag = false;
        if (apartComplex.contains(i)) {
            flag = true;
        }
        for (int next : graph.get(i)) {
            if (visited[next]) {
                continue;
            }
            if (apartComplex.contains(next)) {
                flag = true;
            }
            if (dfs(next)) {
                flag = true;
            }

        }
        // 현재 노드가 아파트 단지 두개 사이에 존재하는 경우
        if (flag) {
            count++;
        }
        return flag;
    }

}