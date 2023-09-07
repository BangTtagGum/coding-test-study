package lee.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2023.09.07 1759 암호 만들기
 */
public class _1759 {

    public static int L; // 암호의 길이
    public static int C; // 가지고 있는 문자의 개수
    public static StringBuilder SB = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] cypher = new char[C];
        for (int i = 0; i < C; i++) {
            cypher[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(cypher);

        char[] password = new char[L];

        add(cypher, password, 0, 0);
        System.out.println(SB);
    }

    /**
     *
     * @param cypher 암호에 사용될 문자 모음
     * @param password 만들고 있는 암호
     * @param idx 이번에 추가할 password의 index
     * @param k k-1 번째 cypher 문자를 추가 (k-1 번째부터 마지막까지 for문)
     */
    private static void add(char[] cypher, char[] password, int idx, int k) {
        if (idx >= L) { // 비밀번호 길이 다 채우면 체크하고 유효하면 패스워드에 넣기
            for (char c : password) {
                if (validCheck(password)) {
                    SB.append(password).append("\n");
                }
                return;
            }
        }
        if (k >= C) { // 가진 숫자 개수 밖으로 넘어가면 종료
            return;
        }
        for (int i = k; i < C; i++) { // abcd 하고 나면 d 자리에 다음 문자들 abce abcf abcg 이런식으로 추가 그걸 전체적으로 반복
            password[idx] = cypher[i]; // idx 위치에 cyper[k] 값 넣어주기
            add(cypher, password, idx + 1, i + 1); // idx를 다음으로 넘기고, 중복 안하기 위해 k도 k+1로 다음 문자열 부터 추가해본다.
        }
    }

    static boolean validCheck(char[] password) { // 자음 2개 모음 1개 필요
        int vowel = 0;
        int consonant = 0;
        for (char c : password) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel++;
            } else {
                consonant++;
            }
        }

        return vowel >= 1 && consonant >= 2;
    }
}
