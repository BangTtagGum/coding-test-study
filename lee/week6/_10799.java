package lee.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2023.10.06
 * 10799 쇠막대기
 */
public class _10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (i - 1 == stack.peek()) { //레이저
                    stack.pop();
                    answer += stack.size();
                } else { //철판
                    stack.pop();
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }

    public static class Bracket {

        int idx;

    }
}
