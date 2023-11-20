package ju.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열 폭발
 *
 * 메모리: 61400KB
 * 시간: 528ms
 *
 * 이전에 풀었던 문제 EZ
 */
public class _9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        String bomb = br.readLine();
        int bombSize = bomb.length();
        boolean check = true;
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<input.length(); i++){
            stack.push(input.charAt(i));

            if(stack.size() >= bombSize){
                check = true;
                for(int j=0; j<bombSize; j++){
                    //폭탄길이만큼 뒤에서 문자 같은지 체크
                    if(stack.get(stack.size()-bombSize+j) != bomb.charAt(j)){
                        check = false;
                        break;
                    }
                }
                if(check){
                    for(int j=0; j<bombSize; j++){
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else{
            for(int i=0; i<stack.size(); i++){
                sb.append(stack.get(i));
            }
            System.out.println(sb);
        }
    }
}

