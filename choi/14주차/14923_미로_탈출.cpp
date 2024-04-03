#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m, hx, hy, ex, ey;
bool map[1001][1001];

typedef struct NODE{
    int i, j;
    int d;
    bool flag;
}node;
queue<node> q;
int visited[1001][1001];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void input(){
    fastio
    cin >> n >> m >> hx >> hy >> ex >> ey;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= m; j ++){
            cin >> map[i][j];
            visited[i][j] = -1;
        }
    }
}

int bfs(){
    int i, j, d, flag;

    q.push({hx, hy, 0, 0});
    visited[hx][hy] = 0;
    
    while (q.size()) {
        node f = q.front();
        i = f.i, j = f.j, d = f.d, flag = f.flag;
        q.pop();

        // cout << i << ' ' << j << ' ' << d << '\n';
        if (i == ex && j == ey) return d;

        for (int dir = 0; dir < 4; dir ++){
            int ni = i + di[dir];
            int nj = j + dj[dir];
            if (ni < 1 || ni > n || nj < 1 || nj > m) continue;
            if (map[ni][nj]) {
                if (flag) continue;
                q.push({ni, nj, d + 1, 1});
                visited[ni][nj] = 1;               
            }
            else if (visited[ni][nj] == -1 || (visited[ni][nj] == 1 && flag == 0)) {
                q.push({ni, nj, d + 1, flag});
                visited[ni][nj] = flag;
            }
        }  
    }
    return -1;
}

int main(){
    input();
    cout << bfs();
    return 0;
}