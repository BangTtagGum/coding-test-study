package ju.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 덱
 *
 * 메모리: 20620KB
 * 시간: 404ms
 */
public class _10866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String commands = br.readLine();

            if (commands.contains("_")) {
                String[] command = commands.split("_");

                switch (command[0]) {
                    case "push":
                        String[] commandNum = command[1].split(" ");
                        int deqNum = Integer.parseInt(commandNum[1]);
                        if (commandNum[0].equals("back")) {
                           deque.addLast(deqNum);
                        }
                        else {
                            deque.addFirst(deqNum);
                        }
                        break;
                    case "pop":
                        if (deque.isEmpty()) {
                            System.out.println(-1);
                            break;
                        }

                        if (command[1].equals("back")) {
                            System.out.println(deque.pollLast());
                        }
                        else {
                            System.out.println(deque.pollFirst());
                        }
                        break;
                }
            } else {
                switch (commands) {
                    case "size":
                        System.out.println(deque.size());
                        break;
                    case "empty":
                        System.out.println(deque.isEmpty() ? 1 : 0);
                        break;
                    case "front":
                        System.out.println(deque.isEmpty() ? -1 : deque.peekFirst());
                        break;
                    case "back":
                        System.out.println(deque.isEmpty() ? -1 : deque.peekLast());
                        break;
                }
            }
        }
    }
}
