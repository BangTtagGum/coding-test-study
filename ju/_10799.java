package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 쇠막대기
 *
 * Stack으로 입력받을때 마다 확인
 * ) 닫는 태그 일경우 레이저인지 아닌지 여부체크 후 레이저일 경우 스택 사이즈만큼 더하고 아니면 +1
 *
 */
public class _10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split("");

        int result = 0;
        Stack<String> stack = new Stack<>();
        stack.push(inputs[0]);
        for (int i = 1; i < inputs.length; i++) {
            String nowTag = inputs[i];  //현재태그
            String prevTag = inputs[i-1];   //이전태그
            //현재태그가 닫는 태그일 때
            if (")".equals(nowTag)){
                stack.pop();
                //레이저일 경우
                if ("(".equals(prevTag)) {
                    result += stack.size();
                } else {
                    result += 1;
                }
            } else {
                stack.push(nowTag);
            }
        }

        System.out.println(result);
    }
}
