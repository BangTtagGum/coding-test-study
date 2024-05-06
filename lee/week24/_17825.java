package lee.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17825 {

    static int maxVal;
    static Node[] arr = new Node[4];
    static int[] chance = new int[10];
    static Node start = new Node(0, 0, false);
    static Node end = new Node(33, 0, false);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            chance[i] = Integer.parseInt(st.nextToken());
        }
        Node _1 = new Node(1, 2, false);
        Node _2 = new Node(2, 4, false);
        Node _3 = new Node(3, 6, false);
        Node _4 = new Node(4, 8, false);
        Node _5 = new Node(5, 10, true);
        Node _6 = new Node(6, 12, false);
        Node _7 = new Node(7, 14, false);
        Node _8 = new Node(8, 16, false);
        Node _9 = new Node(9, 18, false);
        Node _10 = new Node(10, 20, true);
        Node _11 = new Node(11, 22, false);
        Node _12 = new Node(12, 24, false);
        Node _13 = new Node(13, 26, false);
        Node _14 = new Node(14, 28, false);
        Node _15 = new Node(15, 30, true);
        Node _16 = new Node(16, 32, false);
        Node _17 = new Node(17, 34, false);
        Node _18 = new Node(18, 36, false);
        Node _20 = new Node(20, 38, false);
        Node _21 = new Node(21, 40, false);

        Node _22 = new Node(22, 13, false);
        Node _23 = new Node(23, 16, false);
        Node _24 = new Node(24, 19, false);

        Node _25 = new Node(25, 22, false);
        Node _26 = new Node(26, 24, false);

        Node _27 = new Node(27, 28, false);
        Node _28 = new Node(28, 27, false);
        Node _29 = new Node(29, 26, false);
        Node _30 = new Node(30, 25, false);

        Node _31 = new Node(31, 30, false);
        Node _32 = new Node(32, 35, false);

        start.connect(_1, null);

        _1.connect(_2, null);
        _2.connect(_3, null);
        _3.connect(_4, null);
        _4.connect(_5, null);
        _5.connect(_6, _22);
        _6.connect(_7, null);
        _7.connect(_8, null);
        _8.connect(_9, null);
        _9.connect(_10, null);
        _10.connect(_11, _25);
        _11.connect(_12, null);
        _12.connect(_13, null);
        _13.connect(_14, null);
        _14.connect(_15, null);
        _15.connect(_16, _27);
        _16.connect(_17, null);
        _17.connect(_18, null);
        _18.connect(_20, null);
        _20.connect(_21, null);
        _21.connect(end, null);
        _22.connect(_23, null);
        _23.connect(_24, null);
        _24.connect(_30, null);
        _25.connect(_26, null);
        _26.connect(_30, null);
        _27.connect(_28, null);
        _28.connect(_29, null);
        _29.connect(_30, null);
        _30.connect(_31, null);
        _31.connect(_32, null);
        _32.connect(_21, null);

        for (int i = 0; i < 4; i++) {
            arr[i] = start;
        }

        dfs(0, 0);

        System.out.println(maxVal);

    }

    static void dfs(int idx, int total) {
        if (idx == 10) {
            if (maxVal < total) {
                maxVal = total;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            // 도착 노드
            if (arr[i].num == end.num) {
                continue;
            }

            Node cur = new Node(arr[i]);
            int dis = chance[idx];

            if (arr[i].anotherRoute) {
                arr[i] = arr[i].another;
                dis--;
            }
            while (dis > 0) {
                arr[i] = arr[i].next;
                // 도착점 도착
                if (arr[i].num == end.num) {
                    break;
                }
                dis--;
            }
            int score = arr[i].score;
            for (int j = 0; j < 4; j++) {
                if (arr[i].num == end.num) {
                    break;
                }
                // 이동하려는 위치에 이미 말이 있는 경우 원위치
                if (i != j && arr[i].num == arr[j].num) {
                    score = -1;
                    break;
                }
            }

            if (score == -1) {
                arr[i] = cur;
                continue;
            }
            total += score;
            dfs(idx + 1, total);
            total -= score;
            arr[i] = cur;
        }
    }


    static class Node {

        int num;
        int score;
        boolean anotherRoute;
        Node next = null;
        Node another = null;

        public Node(int num, int score, boolean anotherRoute) {
            this.num = num;
            this.score = score;
            this.anotherRoute = anotherRoute;
        }

        public Node(Node node) {
            num = node.num;
            score = node.score;
            anotherRoute = node.anotherRoute;
            next = node.next;
            another = node.another;
        }

        void connect(Node next, Node another) {
            this.next = next;
            this.another = another;
        }

    }


}


