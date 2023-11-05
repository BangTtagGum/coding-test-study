package lee.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 2023.09.28
 * 2812 크게 만들기
 *
 * 2023.10.05 재도전
 */
public class _2812 {

    static int n, k;
    static String num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        num = br.readLine();

        Stack<Character> stack = new Stack<>();

        int count = 0;
        for (int i = 0; i < n; i++) {
            //i 번째 인덱스를 stack값과 비교 후 크면 스택에서 하나 빼고 다시 체크
            if (!stack.isEmpty() && num.charAt(i) > stack.peek() && count != k) {
                stack.pop();
                count++;
                i--;
            } else {    // 이전값보다 작으면 스택에 넣기
                stack.push(num.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();

        int len = n - k;
        for (Character c : stack) {
            if (len > 0) {  // 내림차순일 경우 전부 스택에 담기기에 지우고 남은 수만큼만 출력
                sb.append(c);
                len--;
            }
        }
        System.out.println(sb);

    }

}
