package lee.week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _20170 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] diceA = new int[6];
        int[] diceB = new int[6];

        // 입력 받기
        String[] inputs = reader.readLine().split(" ");
        for (int i = 0; i < 6; i++) {
            diceA[i] = Integer.parseInt(inputs[i]);
        }

        inputs = reader.readLine().split(" ");
        for (int i = 0; i < 6; i++) {
            diceB[i] = Integer.parseInt(inputs[i]);
        }

        int numerator = 0;
        int denominator = 36;

        // 유리한 경우 계산
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (diceA[i] > diceB[j]) {
                    numerator++;
                }
            }
        }

        // 분모와 분자의 최대 공약수 구하기
        int gcdValue = gcd(denominator, numerator);

        // 결과 출력
        System.out.println((numerator / gcdValue) + "/" + (denominator / gcdValue));
    }

    // 최대 공약수 계산 함수
    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
