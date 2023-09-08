package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 암호 만들기
 *
 *  * 최소 1개의 모음과 2개의 자음으로 구성됨
 *  * 암호는 알파벳 순서로 구성
 *
 * a t c i s w -> a c i s t w
 * acis
 * acit
 * aciw
 * acst
 * acsw
 * actw
 * aist
 * aisw
 * aitw
 * astw
 * cist
 * cisw
 * citw
 * istw
 * 14가지 경우의 수가 나옴
 *
 * 빽트래킹을 활용해 모든 경우의 수를 찾고 조건에 맞을 경우만 출력
 * 정렬 + 알파벳 조합 후 모음 여부 판단 + 자음 2개 이상 여부 판단
 *
 */
public class _1759 {

    static int l;   //코드 자리수
    static int c;   //알파벳 개수

    static StringBuilder sb = new StringBuilder();

    static char[] codes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        codes = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            codes[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(codes);

        dfs(0);
    }

    private static void dfs(int idx) {

        //암호완성
        if (sb.length() == l) {

            char[] code = sb.toString().toCharArray();
            int vowel = 0;
            int cons = 0;

            //모음 1개 이상 자음 2개 이상 체크
            for (int i = 0; i < l; i++) {
                if (code[i] == 'a' || code[i] == 'e' || code[i] == 'i' || code[i] == 'o' || code[i] == 'u') {
                    vowel++;
                } else {
                    cons++;
                }
            }

            if (vowel >= 1 && cons >= 2) System.out.println(sb);

            sb.deleteCharAt(l - 1);
            return;
        }

        for (int i = idx; i < c; i++) {
            sb.append(codes[i]);
            dfs(i+1);
        }

        if (!sb.toString().isEmpty()) sb.deleteCharAt(sb.length() - 1);
    }
}
