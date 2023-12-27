package ju.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 고층 빌딩
 *
 * n = 1
 * 1
 * (1, 1) -> 1개
 *
 * n = 2
 * 1 2
 * 2 1
 * (l, r)
 * (2, 1) -> 1개
 * (1, 2) -> 1개
 *
 * n = 3
 * 1 2 3 -> l = 3, r = 1
 * 1 3 2 -> l = 2, r = 2
 * 2 1 3 -> l = 2, r = 1
 * 2 3 1 -> l = 2, r = 2
 * 3 1 2 -> l = 1, r = 2
 * 3 2 1 -> l = 1, r = 3
 * (l, r)
 * (3, 1) -> 1개
 * (2, 2) -> 2개
 * (2, 1) -> 1개
 * (1, 2) -> 1개
 * (1, 3) -> 1개
 *
 * n = 4
 * 1 2 3 4 -> l = 4, r = 1
 * 1 2 4 3 -> l = 3, r = 2
 * 1 3 2 4 -> l = 2, r = 1
 * 1 3 4 2 -> l = 3, r = 2
 * 1 4 2 3 -> l = 2, r = 2
 * 1 4 3 2 -> l = 2, r = 3
 * 2 1 3 4 -> l = 3, r = 1
 * 2 1 4 3 -> l = 2, r = 2
 * 2 3 1 4 -> l = 3, r = 1
 * 2 3 4 1 -> l = 3, r = 2
 * 2 4 1 3 -> l = 2, r = 2
 * 2 4 3 1 -> l = 2, r = 3
 * 3 1 2 4 -> l = 2, r = 1
 * 3 1 4 2 -> l = 2, r = 2
 * 3 2 1 4 -> l = 2, r = 1
 * 3 2 4 1 -> l = 2, r = 2
 * 3 4 1 2 -> l = 2, r = 2
 * 3 4 2 1 -> l = 2, r = 3
 * 4 1 2 3 -> l = 1, r = 2
 * 4 1 3 2 -> l = 1, r = 3
 * 4 2 1 3 -> l = 1, r = 2
 * 4 2 3 1 -> l = 1, r = 3
 * 4 3 1 2 -> l = 1, r = 3
 * 4 3 2 1 -> l = 1, r = 4
 * (l, r)
 * (4, 1) -> 1개
 * (3, 2) -> 3개
 * (3, 1) -> 2개
 * (2, 3) -> 3개
 * (2, 2) -> 6개
 * (2, 1) -> 3개
 * (1, 2) -> 2개
 * (1, 3) -> 3개
 * (1, 4) -> 1개
 *
 * -> ???점화식이 뭐임 대체
 */
public class _1328_not {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());


    }
}
