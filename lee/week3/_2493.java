package lee.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 2023.09.09 2493 탑
 */
public class _2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Tower[] tower = new Tower[n + 1];
        int[] ans = new int[n + 1];
        Stack<Tower> stack = new Stack<Tower>();

        tower[0] = new Tower(0, 100000001);
        for (int i = 1; i <= n; i++) {
            tower[i] = new Tower(Integer.parseInt(st.nextToken()), i);
        }

        int cnt = 0;

        for (int i = n; i >= 1; i--) {
            stack.push(tower[i]);
            if (tower[i - 1].height > tower[i].height) {
                while (!stack.isEmpty() && tower[i - 1].height > stack.peek().height) {
                    Tower pop = stack.pop();
                    ans[pop.idx] = i - 1;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    public static class Tower {

        int height;
        int idx;

        public Tower(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}
