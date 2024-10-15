package lee.week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20040 {

    static StringTokenizer st;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        System.out.println(cycleCheck(m, parent));
    }

    private static int cycleCheck(int m, int[] parent) throws IOException {
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int parentA = findParent(a, parent);
            int parentB = findParent(b, parent);

            // 조상이 동일하면 사이클 생성
            if (parentA == parentB) {
                return i;
            }

            parent[parentA] = Math.min(parentA, parentB);
            parent[parentB] = Math.min(parentA, parentB);
        }

        return 0;
    }

    private static int findParent(int num, int[] parent) {
        while (parent[num] != num) {
            num = parent[num];
        }
        return num;
    }

}
