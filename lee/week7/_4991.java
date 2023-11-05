package lee.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.10.18
 * 로봇 청소기
 */
public class _4991 {

    static int n = 1;
    static int m = 1;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (n != 0 && m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {

                }
            }

        }
    }
}
