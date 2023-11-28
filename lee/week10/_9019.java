package lee.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 2023.11.28 DSLR
 */
public class _9019 {

    public static int a;
    public static int b;
    public static String[] dslr = {"D", "S", "L", "R"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            Queue<Command> q = new LinkedList<>();
            q.add(new Command(a, ""));
            boolean[] visited = new boolean[10000];
            visited[a] = true;
            boolean flag = false;
            while (!q.isEmpty() && !flag) {
                Command poll = q.poll();
                for (int j = 0; j < 4; j++) {
                    Command command = new Command(poll);
                    command.exec(dslr[j]);
                    if (command.num == b) {
                        flag = true;
                        sb.append(command.command).append("\n");
                        break;
                    }
                    // 이미 거쳐간 숫자가 아니면 queue에 저장
                    if (!visited[command.num]) {
                        q.add(command);
                        visited[command.num] = true;
                    }
                }
            }
        }

        System.out.println(sb);

        br.close();
    }


    static class Command {

        int num;
        String command;

        public Command(int num, String command) {
            this.num = num;
            this.command = command;
        }

        public Command(Command poll) {
            this.num = poll.num;
            this.command = poll.command;
        }

        public void exec(String command) {
            if (command.equals("D")) {
                this.num = num * 2 % 10000;
            } else if (command.equals("S")) {
                this.num = (num + 9999) % 10000;
            } else if (command.equals("L")) {
                String str = String.valueOf(num + 10000); // 1의 경우 10001에서 0001만 추출하기 위함
                str = str.substring(2, 5).concat(str.substring(1, 2));
                this.num = Integer.parseInt(str);
            } else if (command.equals("R")) {
                String str = String.valueOf(num + 10000);
                str = str.substring(4, 5).concat(str.substring(1, 4));
                this.num = Integer.parseInt(str);
            }
            // 입력해온 명령어 저장
            this.command = this.command.concat(command);

        }
    }

}