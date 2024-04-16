package lee.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16235 {

    static int n;
    static int m;
    static int k;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static PriorityQueue<Tree> pq1 = new PriorityQueue<>((t1, t2) ->
            t1.age - t2.age
    );
    static PriorityQueue<Tree> pq2 = new PriorityQueue<>((t1, t2) ->
            t1.age - t2.age
    );

    static boolean pqToggle = true;

    static class Tree {

        int r, c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }


        public void copy() {
            for (int k = 0; k < 8; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (isValid(nr, nc)) {
                    if (pqToggle) {
                        pq1.add(new Tree(nr, nc, 1));
                    } else {
                        pq2.add(new Tree(nr, nc, 1));
                    }
                }
            }
        }

        public boolean isValid(int r, int c) {
            if (0 <= r && r < n && 0 <= c && c < n) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] A = new int[n][n];
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 5;
            }
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            pq1.add(new Tree(x, y, z));
        }

        while (k > 0) {
            Queue<Tree> q = new LinkedList<>();
            Queue<Tree> deadTrees = new LinkedList<>();
            k--;
            int size;

            // 봄
            if (pqToggle) {
                size = pq1.size();
                for (int i = 0; i < size; i++) {
                    Tree tree = pq1.poll();
                    // 양분 먹기
                    if (map[tree.r][tree.c] >= tree.age) {
                        map[tree.r][tree.c] -= tree.age;
                        tree.age++;
                        pq2.add(tree);
                        // 가을에 복제될 나무 저장
                        if (tree.age % 5 == 0) {
                            q.add(tree);
                        }
                    } else { // 양분 못먹은 나무 죽기
                        deadTrees.add(tree);
                    }
                }
            } else {
                size = pq2.size();
                for (int i = 0; i < size; i++) {
                    Tree tree = pq2.poll();
                    // 양분 먹기
                    if (map[tree.r][tree.c] >= tree.age) {
                        map[tree.r][tree.c] -= tree.age;
                        tree.age++;
                        pq1.add(tree);
                        // 가을에 복제될 나무 저장
                        if (tree.age % 5 == 0) {
                            q.add(tree);
                        }
                    } else { // 양분 못먹은 나무 죽기
                        deadTrees.add(tree);
                    }
                }
            }
            pqToggle = !pqToggle;

            // 여름
            while (!deadTrees.isEmpty()) {
                Tree tree = deadTrees.poll();
                map[tree.r][tree.c] += tree.age / 2;
            }

            // 가을
            while (!q.isEmpty()) {
                Tree tree = q.poll();
                tree.copy();
            }

            // 겨울
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] += A[i][j];
                }
            }
        }

        if (pqToggle) {
            System.out.println(pq1.size());
        } else {
            System.out.println(pq2.size());
        }
    }
}
