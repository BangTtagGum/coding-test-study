package lee.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2011 {

    public static void main(String[] args) throws IOException {
        /**
         * 1, 2 -> 뒤에 숫자 가능
         * 3-9 -> 뒤에 추가 숫자 불가능
         * 0 -> 열린 구간에만 가능.
         *
         * ==== 1, 2의 경우 ====
         * "111"
         * close[i] = 1
         * open[i] = 2
         *
         * "1111"
         * close[i] = 2 -> 열려있는 개수              => close[i] = open[i - 1]
         * open[i] = 3 -> 닫혀있던 개수 + 열려있는 개수 => open[i] = close[i - 1] + open[i - 1]
         *
         * "11111"
         * close[i] = 3 -> 열려있는 개수                         => close[i] = open[i - 1]
         * open[i] = 5 -> 닫혀있던 개수 + 열려있는 개수            => open[i] = close[i - 1] + open[i - 1]
         *
         *
         * ==== 0, 3-6의 경우 ====
         * "1113"
         *
         * close[i] = 5 -> 닫혀있던 개수 + 열려있는 개수 * 2       => close[i] = close[i - 1] + open[i -1] * 2
         * open[i] = 0 -> 열려있는 경우가 나올 수 없음
         *
         *
         * ==== 7-9의 경우 ====
         * 앞의 수가 1일 경우
         * close[i] = close[i - 1] + open[i - 1]
         *
         * 앞의 수가 2일 경우
         * close[i] = close[i - 1] + open[i - 1] * 2
         *
         * 그 외
         * close[i] = close[i - 1]
         *
         * ==== 0의 경우 ====
         * 앞이 0일 경우 -> 암호가 성립되지 않음
         * close[i] = 0
         *
         * 그 외
         * close[i] = open[i - 1]
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        // close 뒤에 더이상 숫자를 붙히지 못하는 경우
        // open 뒤에 숫자가 붙히거나 붙히지 않을 수 있는 경우
        int[] close = new int[input.length()];
        int[] open = new int[input.length()];

        char[] charArray = input.toCharArray();

        if (charArray[0] == '1' || charArray[0] == '2') {
            open[0] = 1;
        } else if (charArray[0] == '0') {
            close[0] = 0;
        } else {
            close[0] = 1;
        }

        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                close[i] = open[i - 1] % 1000000;
                open[i] = (close[i - 1] + open[i - 1]) % 1000000;
            } else if (charArray[i] == '2') {
                close[i] = open[i - 1] % 1000000;
                open[i] = (close[i - 1] + open[i - 1]) % 1000000;
            } else if (charArray[i] == '0') {
                if (charArray[i - 1] == '0') {
                    continue;
                }
                close[i] = open[i - 1] % 1000000;
            } else if (3 <= charArray[i] - 48 && charArray[i] - 48 <= 6) {
                close[i] = (close[i - 1] + open[i - 1] * 2) % 1000000;
            } else {
                if (charArray[i - 1] == '1') {
                    close[i] = (close[i - 1] + open[i - 1] * 2) % 1000000;
                } else if (charArray[i - 1] == '2') {
                    close[i] = (close[i - 1] + open[i - 1]) % 1000000;
                } else {
                    close[i] = close[i - 1];
                }
            }
        }

        int answer = (open[charArray.length - 1] + close[charArray.length - 1]) % 1000000;
        System.out.println(answer);
    }
}
