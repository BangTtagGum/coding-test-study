package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1 -> 1 -> 1 -> 2 -> 2 -> 3 -> 4 -> 5 -> 7 -> 9 -> 12 -> 16 -> 21
 *
 * triangle[i] = triangle[i-3] + triangle[i-2]
 */
public class _9461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] triangle = new long[101];
        triangle[1] = 1L;
        triangle[2] = 1L;
        triangle[3] = 1L;

        for (int i = 4; i < 101; i++) {
            triangle[i] = triangle[i-3] + triangle[i-2];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(triangle[n]);
        }
    }
}
