package lee.week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _20044 {

    static StringTokenizer st;

    static class Team {

        int val = 0;
        int count = 0;

        public void addVal(int val) {
            this.val += val;
            count++;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Integer[] values = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                .toArray(Integer[]::new);

        Arrays.sort(values, Comparator.reverseOrder());

        PriorityQueue<Team> teams = new PriorityQueue<>((t1, t2) -> {
            if (t1.count == t2.count) {
                return t1.val - t2.val;
            }
            return t1.count - t2.count;
        });

        for (int i = 0; i < n; i++) {
            teams.add(new Team());
        }
        for (Integer value : values) {
            Team team = teams.poll();
            team.addVal(value);
            teams.add(team);
        }

        System.out.println(teams.poll().val);

    }
}
