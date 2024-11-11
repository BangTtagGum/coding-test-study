#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <tuple>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define MAX 3333
using namespace std;

int n, m, ans, wall, virus;
int map[51][51];
typedef pair<int, int> p;
typedef struct NODE{
    int i, j, dist;
}node;
vector<p> pos;
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void input(){
    fastio
    cin >> n >> m;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            cin >> map[i][j];
            if (map[i][j] == 1) wall ++;
            else if (map[i][j] == 2) {
                virus ++;
                pos.push_back({i, j});
            }
        }
    }
    ans = MAX;
}

int bfs(){
    queue<node> q;
    bool visited[n + 1][n + 1] = {0,};
    int extra_virus = 0;

    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            if (map[i][j] == 3) {
                q.push({i, j, 0});
                visited[i][j] = 1;
            }
        }
    }

    while (q.size()) {
        node f = q.front();
        q.pop();

        int i, j, dist;
        i = f.i, j = f.j, dist = f.dist;

        for (int d = 0; d < 4; d ++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 1 || ni > n || nj < 1 || nj > n) continue;
            if (map[ni][nj] == 1 || visited[ni][nj]) continue;
            visited[ni][nj] = 1;
            q.push({ni, nj, dist + 1});
            if (map[ni][nj] == 0) extra_virus ++;
            if (wall + virus + extra_virus == n * n) return dist + 1;
        }
    }

    return MAX;
}

void activate(int cur, int cnt){
    if (cnt == m){
        int res = bfs();
        if (res != MAX) ans = min(ans, res);
        return;
    }
    
    for (int nxt = cur + 1; nxt < pos.size(); nxt ++){
        map[pos[nxt].first][pos[nxt].second] = 3;
        activate(nxt, cnt + 1);
        map[pos[nxt].first][pos[nxt].second] = 2;
    }
}

int main(){
    input();
    if (wall + virus == n * n) {
        cout << 0;
        return 0;
    }
    for (int st = 0; st < pos.size(); st ++){
        map[pos[st].first][pos[st].second] = 3;
        activate(st, 1);
        map[pos[st].first][pos[st].second] = 2;
    }
    ans == MAX ? cout << - 1 : cout << ans;
    return 0;
}