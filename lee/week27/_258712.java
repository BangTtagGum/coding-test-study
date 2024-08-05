package lee.week27;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _258712 {

    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftLog = new HashMap<>();
        Map<String, Integer> giftPoint = new HashMap<>();
        Map<String, Integer> giftCountMap = new HashMap<>();

        for (String a : friends) {
            giftLog.put(a, new HashMap<>());
            giftPoint.put(a, 0);
            giftCountMap.put(a, 0);
        }

        for (String a : friends) {
            for (String b : friends) {
                if (a.equals(b)) {
                    continue;
                }
                giftLog.get(a).put(b, 0);
            }
        }

        for (String log : gifts) {
            StringTokenizer st = new StringTokenizer(log);
            String a = st.nextToken();
            String b = st.nextToken();

            giftLog.get(a).put(b, giftLog.get(a).get(b) + 1);
            giftPoint.put(a, giftPoint.get(a) + 1);
            giftPoint.put(b, giftPoint.get(b) - 1);
        }

        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String a = friends[i];
                String b = friends[j];
                if (a.equals(b)) {
                    continue;
                }

                int aTob = giftLog.get(a).get(b);
                int bToa = giftLog.get(b).get(a);

                // 주고받은 선물이 없거나 선물의 수가 같을 경우
                if (aTob == bToa) {
                    if (giftPoint.get(a) > giftPoint.get(b)) {
                        giftCountMap.put(a, giftCountMap.get(a) + 1);
                    } else if (giftPoint.get(a) < giftPoint.get(b)) {
                        giftCountMap.put(b, giftCountMap.get(b) + 1);
                    }
                } else if (aTob > bToa) {
                    giftCountMap.put(a, giftCountMap.get(a) + 1);
                } else {
                    giftCountMap.put(b, giftCountMap.get(b) + 1);
                }
            }
        }

        return Collections.max(giftCountMap.values());

    }
}

