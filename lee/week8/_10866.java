package lee.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2023.11.05
 * 덱
 */
public class _10866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> deck = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            if (input.equals("push_front")) {
                int num = Integer.parseInt(st.nextToken());
                deck.add(0, num);
            } else if (input.equals("push_back")) {
                int num = Integer.parseInt(st.nextToken());
                deck.add(num);
            } else if (input.equals("pop_front")) {
                if (deck.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    int num = deck.get(0);
                    sb.append(num).append("\n");
                    deck.remove(0);
                }
            } else if (input.equals("pop_back")) {
                if (deck.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    int size = deck.size();
                    int num = deck.get(size - 1);
                    sb.append(num).append("\n");
                    deck.remove(size - 1);
                }
            } else if (input.equals("size")) {
                int size = deck.size();
                sb.append(size).append("\n");;
            } else if (input.equals("empty")) {
                if (deck.isEmpty()) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (input.equals("front")) {
                if (deck.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    int num = deck.get(0);
                    sb.append(num).append("\n");
                }
            } else if (input.equals("back")) {
                if (deck.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    int size = deck.size();
                    int num = deck.get(size - 1);
                    sb.append(num).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
