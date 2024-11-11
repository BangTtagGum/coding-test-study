#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m, num;

typedef pair<int, int> p;
int ans, wall_cnt, virus_cnt;
vector<p> spots;
queue<p> q;
int visited[51][51];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void input(){
    fastio
    cin >> n >> m;
    for (int i = 1; i <= n; i ++ ){
        for (int j = 1; j <= n; j ++){
            cin >> num;
            if (num == 1) {
                visited[i][j] = 1;
                wall_cnt ++;
            }
            else if (num == 2) spots.push_back({i, j});
        }
    }
    ans = 2500;
}

void reset(){

    while (q.size()) q.pop();
    
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            if (visited[i][j] <= 1) continue;
            visited[i][j] = 0;
        }
    }

    virus_cnt = 0;
}

void bfs(){

    int i, j;
    for (auto spot : spots){
        i = spot.first, j = spot.second;
        if (visited[i][j]) {
            q.push({i, j});
            virus_cnt ++;
        }
    }

    if (virus_cnt + wall_cnt == n * n) ans = 0;

    while (q.size()){
        i = q.front().first, j = q.front().second;
        q.pop();

        for (int d = 0; d < 4; d ++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 1 || ni > n || nj < 1 || nj > n) continue;
            if (visited[ni][nj]) continue;
            q.push({ni, nj});
            visited[ni][nj] = visited[i][j] + 1;
            virus_cnt ++;
            if (virus_cnt + wall_cnt == n * n) {
                ans = min(ans, visited[ni][nj] - 1);
                return;
            }
        }
    }
}

void contaminate(int i, int j, int cnt){
    if (cnt == m) {
        bfs();
        reset();
        return;
    }

    for (auto spot : spots) {
        int ni = spot.first, nj = spot.second;
        if (ni * (n - 1) + nj < i * (n - 1) + j) continue;
        if (visited[ni][nj]) continue;
        visited[ni][nj] = 1;
        contaminate(ni, nj, cnt + 1);
        visited[ni][nj] = 0;
    }
}

int main(){
    input();

    for (auto spot : spots) {
        int i = spot.first, j = spot.second;
        visited[i][j] = 1;
        contaminate(i, j, 1);
        visited[i][j] = 0;
    }

    ans == 2500 ? cout << -1 : cout << ans;

    return 0;
}