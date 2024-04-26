package lee.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17779 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int total = 0;

        int map[][] = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        int minResult = total;

        // x
        for (int x = 1; x < n - 1; x++) {
            // y
            for (int y = 2; y < n; y++) {
                // d1
                for (int d1 = 1; d1 < n - x && d1 <= y - 1; d1++) {
                    // d2
                    for (int d2 = 1; d2 <= n - x - d1 && d2 <= n - y; d2++) {

                        int maxPopulation = 0;
                        int minPopulation = total;

                        // 1 선거구
                        int Population1 = 0;
                        for (int i = 1; i < x + d1; i++) {
                            if (i >= x) {
                                for (int j = 1; j < y - (i - x); j++) {
                                    Population1 += map[i][j];
                                }
                            } else {
                                for (int j = 1; j <= y; j++) {
                                    Population1 += map[i][j];
                                }
                            }
                        }

                        maxPopulation = Math.max(maxPopulation, Population1);
                        minPopulation = Math.min(minPopulation, Population1);

                        int Population2 = 0;
                        for (int i = 1; i <= x + d2; i++) {
                            if (i - x > 0) {
                                for (int j = y + 1 + i - x; j <= n; j++) {
                                    Population2 += map[i][j];
                                }
                            } else {
                                for (int j = y + 1; j <= n; j++) {
                                    Population2 += map[i][j];
                                }
                            }
                        }

                        maxPopulation = Math.max(maxPopulation, Population2);
                        minPopulation = Math.min(minPopulation, Population2);

                        int Population3 = 0;
                        for (int i = x + d1; i <= n; i++) {
                            if (i >= x + d1 + d2) {
                                for (int j = 1; j < y - d1 + d2; j++) {
                                    Population3 += map[i][j];
                                }
                            } else {
                                for (int j = 1; j < y - d1 + i - (x + d1); j++) {
                                    Population3 += map[i][j];
                                }
                            }
                        }
                        maxPopulation = Math.max(maxPopulation, Population3);
                        minPopulation = Math.min(minPopulation, Population3);

                        int Population4 = 0;
                        for (int i = x + d2 + 1; i <= n; i++) {
                            if (i - (x + d2 + 1) < d1) {
                                for (int j = y + d2 - (i - (x + d2 + 1)); j <= n; j++) {
                                    Population4 += map[i][j];
                                }
                            } else {
                                for (int j = y - d1 + d2; j <= n; j++) {
                                    Population4 += map[i][j];
                                }
                            }
                        }
                        maxPopulation = Math.max(maxPopulation, Population4);
                        minPopulation = Math.min(minPopulation, Population4);

                        int Population5 =
                                total - (Population1 + Population2 + Population3 + Population4);

                        maxPopulation = Math.max(maxPopulation, Population5);
                        minPopulation = Math.min(minPopulation, Population5);

                        minResult = Math.min(minResult, maxPopulation - minPopulation);
                    }
                }
            }
        }

        System.out.println(minResult);

    }
}
