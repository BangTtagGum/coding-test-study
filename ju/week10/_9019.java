package ju.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * DSLR
 *
 * 수식대로 bfs 탐색
 * D: (number * 2) % 10000
 * S: number == 0 ? 9999 : number - 1
 * L: (number % 1000) * 10 + number / 1000
 * R: (number % 10) * 1000 + number / 10
 *
 * 메모리: 304952KB
 * 시간: 3912ms
 */
public class _9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10000];
            Queue<Register> q = new LinkedList<>();
            q.offer(new Register(a, ""));
            visited[a] = true;
            while (!q.isEmpty()) {
                Register register = q.poll();
                int number = register.number;
                String cal = register.cal;

                if (number == b) {
                    System.out.println(cal);
                    break;
                }

                int d = (number * 2) % 10000;
                if (!visited[d]) {
                    q.offer(new Register(d, cal + "D"));
                    visited[d] = true;
                }

                int s = number == 0 ? 9999 : number - 1;
                if (!visited[s]) {
                    q.offer(new Register(s, cal + "S"));
                    visited[s] = true;
                }

                int l = (number % 1000) * 10 + number / 1000;
                if (!visited[l]) {
                    q.offer(new Register(l, cal + "L"));
                    visited[l] = true;
                }

                int r = (number % 10) * 1000 + number / 10;
                if (!visited[r]) {
                    q.offer(new Register(r, cal + "R"));
                    visited[r] = true;
                }
            }
        }
    }

    static class Register {
        int number;
        String cal;

        public Register(int number, String cal) {
            this.number = number;
            this.cal = cal;
        }
    }
}
