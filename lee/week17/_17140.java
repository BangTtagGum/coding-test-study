package lee.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class _17140 {
    public static int[][] array = new int[101][101];
    public static int maxRow = 3;
    public static int maxColumn = 3;
    public static void R() {
        int size = maxColumn;
        int newMaxColumn = 0;
        for (int i = 1; i <= maxRow; i++) {

            Map<Integer, Integer> count = new LinkedHashMap<>();

            for (int j = 1; j <= size; j++) {
                if (array[i][j] != 0) {
                    count.put(array[i][j], count.getOrDefault(array[i][j], 0) + 1);
                }
            }

            // 맵 정렬
            Set<Entry<Integer, Integer>> entries = count.entrySet();
            List<Entry<Integer, Integer>> collect = entries.stream().sorted((m1, m2) -> {
                if (m1.getValue() == m2.getValue()) {
                    return m1.getKey() - m2.getKey();
                }
                return m1.getValue() - m2.getValue();
            }).collect(Collectors.toList());

            // 가장 긴 Column 길이 저장
            newMaxColumn = Math.max(newMaxColumn, collect.size() * 2);

            // 배열 재정렬
            int idx = 1;
            for (Entry<Integer, Integer> entry : collect) {
                if (idx > 100) {
                    break;
                }
                array[i][idx++] = entry.getKey();
                array[i][idx++] = entry.getValue();
            }

            // 배열이 짧아질 경우 뒤의 숫자들 제거
            if (idx <= maxColumn) {
                for (int j = idx; j <= maxColumn ; j++) {
                    array[i][j] = 0;
                }
            }
        }
        maxColumn = Math.min(newMaxColumn, 100);
    }
    public static void C() {
        int size = maxRow;

        for (int i = 1; i <= maxColumn; i++) {
            Map<Integer, Integer> count = new LinkedHashMap<>();

            for (int j = 1; j <= size; j++) {
                if (array[j][i] != 0) {
                    count.put(array[j][i], count.getOrDefault(array[j][i], 0) + 1);
                }
            }

            // 맵 정렬
            Set<Entry<Integer, Integer>> entries = count.entrySet();
            List<Entry<Integer, Integer>> collect = entries.stream().sorted((m1, m2) -> {
                if (m1.getValue() == m2.getValue()) {
                    return m1.getKey() - m2.getKey();
                }
                return m1.getValue() - m2.getValue();
            }).collect(Collectors.toList());

            // 배열 재정렬
            maxRow = Math.max(maxRow, collect.size() * 2);
            if (maxRow > 100) {
                maxRow = 100;
            }
            int idx = 1;
            for (Entry<Integer, Integer> entry : collect) {
                if (idx > 100) {
                    break;
                }
                array[idx++][i] = entry.getKey();
                array[idx++][i] = entry.getValue();
            }
            if (idx <= maxRow) {
                for (int j = idx; j <= maxRow ; j++) {
                    array[j][i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (array[r][c] != k) {
            if (time == 100) {
                System.out.println(-1);
                return;
            }
            time++;
            if (maxRow >= maxColumn) {
                R();
            } else {
                C();
            }
        }
        System.out.println(time);
    }
}
