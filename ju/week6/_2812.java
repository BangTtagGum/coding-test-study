package ju.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 크게 만들기
 *
 * 단순히 숫자가 작은 값을 지우면 안됨 -> 앞숫자가 가장 클 수 있는 경우의 수를 따져야함
 * Stack 사용해서 값이 새로 들어올때마다 앞에 값과 비교해서 작으면 앞에값 삭제 -> k만큼 반복
 */
public class _2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String numbers = br.readLine();

        Deque<Character> q = new ArrayDeque<>();

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char number = numbers.charAt(i);

            //뒤에 오는 값이 더 클 경우 앞에 값들 삭제
            while (!q.isEmpty()) {

                //k만큼 삭제할 경우 break
                if (cnt == k) {
                    break;
                }

                Character temp = q.peekLast();
                if (temp < number) {
                    q.removeLast();
                    cnt++;
                } else {
                    break;
                }
            }
            q.addLast(number);
        }

        //큐에 cnt값 제외하고 다들어 있으니 뒤에서부터 제거
        if (cnt != k) {
            for (int i = 0; i < k - cnt; i++) {
                q.removeLast();
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()){
            sb.append(q.poll());
        }

        System.out.println(sb);
    }
}
