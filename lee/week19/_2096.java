package lee.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int maxDp[][] = new int[n][3];
        int minDp[][] = new int[n][3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        maxDp[0][0] = minDp[0][0] = Integer.parseInt(st.nextToken());
        maxDp[0][1] = minDp[0][1] = Integer.parseInt(st.nextToken());
        maxDp[0][2] = minDp[0][2] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int currentNums[] = new int[3];
            for (int j = 0; j < 3; j++) {
                currentNums[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < 3; j++) {
                int max = maxDp[i - 1][j];
                int min = minDp[i - 1][j];
                for (int k = -1; k < 2; k++) {
                    if (0 <= j + k && j + k <= 2) {
                        max = Math.max(maxDp[i - 1][j + k], max);
                        min = Math.min(minDp[i - 1][j + k], min);
                    }
                }
                maxDp[i][j] = max + currentNums[j];
                minDp[i][j] = min + currentNums[j];
            }
        }

        int maxResult = Math.max(Math.max(maxDp[n-1][0],maxDp[n-1][1]),maxDp[n-1][2]);
        int minResult = Math.min(Math.min(minDp[n-1][0],minDp[n-1][1]),minDp[n-1][2]);
        System.out.println(maxResult + " " + minResult);

    }

}
