package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 탑
 *
 * 뒤에서부터 for문 돌면서 전체비교 떄리면 시간초과가 남
 *
 * 새로 입력되는 탑이 이전값보다 크다면 앞에 탑은 존재X
 * -> 입력 순서로 뒤부터 값이 더 크다면 이전 탑 삭제 -> Stack사용
 *
 * 6	9	5	7	4
 *top   (순서, 높이)                송신탑
 * 6 -> (1, 6)	                    0
 * 9 -> (2, 9)	                    0
 * 5 -> (2, 9), (3, 5)              2
 * 7 -> (2,9), (4, 7)               2
 * 4 -> (2,9), (4, 7), (5, 4)       4
 */
public class _2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Top> stack = new Stack<>();
        for (int i = 1; i < n+1; i++) {
            int topVal = Integer.parseInt(st.nextToken());

            //더 큰 높이가 나올때 까지 pop작업
            while (!stack.isEmpty()) {
                Top top = stack.peek();

                if (top.value > topVal) {
                    stack.push(new Top(i, topVal));
                    sb.append(top.seq).append(" ");
                    break;
                } else {
                    stack.pop();
                }
            }

            //더 큰 높이가 없다면 현재 탑을 스택에 저장 후 0으로 출력
            if (stack.isEmpty()) {
                stack.push(new Top(i, topVal));
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    /**
     * 순서와 탑의 높이를 가지는 클래스
     */
    static class Top {
        int seq;
        int value;

        public Top(int seq, int value) {
            this.seq = seq;
            this.value = value;
        }
    }
}

