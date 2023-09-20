package ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미로 탈출하기
 *
 * U인 경우에는 (r-1, c)로 이동해야 한다. -> 위
 * R인 경우에는 (r, c+1)로 이동해야 한다. -> 오른쪽
 * D인 경우에는 (r+1, c)로 이동해야 한다. -> 아래
 * L인 경우에는 (r, c-1)로 이동해야 한다. -> 왼쪽
 *
 * 이중 for문을 돌면서 전체경우의 수 탐색하면 시간초과 -> visited = new boolean[n][m] 생각보다 메모리를 많이 먹음
 * -> 이미 방문한 경우는 제외처리 + visit를 계속 생성이 아닌 재사용
 * + 피드백: 이미 방문 했는데 탈출 실패한 경우의 수도 추가해야함
 *
 */
public class _17090 {

    static int n;
    static int m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] mazes;
    static boolean[][] visited;    //현재 진행중인 방문여부
    static int[][] visited2; //모든 방문 여부

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mazes = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] maze = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                switch (maze[j]){
                    case 'U':
                        mazes[i][j] = 0;
                        break;
                    case 'R':
                        mazes[i][j] = 1;
                        break;
                    case 'D':
                        mazes[i][j] = 2;
                        break;
                    case 'L':
                        mazes[i][j] = 3;
                        break;
                }
            }
        }

        visited = new boolean[n][m];       //현재 노드가 방문중인 배열
        visited2 = new int[n][m];       //방문기록(0: 미방문, 1: 성공, 2: 실패)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j);
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int col, int row) {
        if (visited2[col][row] == 2) {
            return false;
        }

        int newCol = col + dx[mazes[col][row]];
        int newRow = row + dy[mazes[col][row]];

        //미로 탈출한 경우
        if (newCol < 0 || newCol >= n || newRow < 0 || newRow >= m || visited2[col][row] == 1) {
            result++;
            return true;
        }

        //현재 노드가 해당 좌표 첫방문이면
        if (!visited[newCol][newRow]) {
            visited[newCol][newRow] = true;
            //새로운 좌표로 dfs한번 더 타기
            if (dfs(newCol, newRow)) {
                visited2[newCol][newRow] = 1;   //성공 시 방문기록에 1입력
                visited[newCol][newRow] = false;    //나가면서 현재방문 초기화
                return true;
            }
            visited2[newCol][newRow] = 2;   //실패 시 방문기록에 2입력
        }

        visited[newCol][newRow] = false;    //나가면서 현재방문 초기화
        return false;
    }
}
