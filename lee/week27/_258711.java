package lee.week27;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _258711 {

    public int[] solution(int[][] edges) {
        Map<Integer, int[]> nodeCount = new HashMap<>();

        int[] answer = {0, 0, 0, 0};

        Arrays.stream(edges).forEach(edge -> {
            int a = edge[0];
            int b = edge[1];
            if (!nodeCount.containsKey(a)) {
                nodeCount.put(a, new int[]{0, 0});
            }
            if (!nodeCount.containsKey(b)) {
                nodeCount.put(b, new int[]{0, 0});
            }
            nodeCount.get(a)[0] += 1;
            nodeCount.get(b)[1] += 1;
        });

        int[] count;
        for (int key : nodeCount.keySet()) {
            count = nodeCount.get(key);
            if (count[0] >= 2 && count[1] == 0) {
                answer[0] = key;
            } else if (count[0] == 0 && count[1] > 0) {
                answer[2]++;
            } else if (count[0] >= 2 && count[1] >= 2) {
                answer[3]++;
            }
        }
        answer[1] = nodeCount.get(answer[0])[0] - answer[2] - answer[3];
        return answer;
    }
}
