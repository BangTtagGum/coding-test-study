#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define MAX 20
using namespace std;

int n;
int map[MAX + 1][MAX + 1];
int di[] = {-1, 0, 0, 1}, dj[] = {0, -1, 1, 0};
int x, y, time, lev, cnt;
typedef struct NODE{
    int dist, i, j;
}node;
typedef pair<int, int> p;

void input(){
    fastio
    cin >> n;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            cin >> map[i][j];
            if (map[i][j] == 9) {
                x = i;
                y = j;
                map[i][j] = 0;
            }
        }
    }
}

bool hunt(){

    int i, j, ni, nj, dist;
    int s = 0;

    int visited[n + 1][n + 1] = {0,};
    queue<node> q;
    vector<pair<int, int>> fishes;

    visited[x][y] = 1;
    q.push({0, x, y});

    while (q.size()){
        node f = q.front();
        q.pop();
        i = f.i, j = f.j, dist = f.dist;

        if (s && dist == s) break;

        for (int dir = 0; dir < 4; dir ++){
            ni = i + di[dir];
            nj = j + dj[dir];
            if (ni < 1 || ni > n || nj < 1 || nj > n) continue;
            if (visited[ni][nj]) continue;
            if (map[ni][nj] > lev) continue;

            visited[ni][nj] = 1;
            if (map[ni][nj] > 0 && map[ni][nj] < lev){
                s = dist + 1;
                fishes.push_back({ni, nj});
            }
            else {
                q.push({dist + 1, ni, nj});
            }
        }
    }

    if (fishes.empty()) return false;

    sort(fishes.begin(), fishes.end());

    p point = fishes.front();
    x = point.first, y = point.second;
    map[x][y] = 0;

    time += s;
    if (++ cnt == lev){
        lev ++;
        cnt = 0;
    }
    
    return true;
}

void solve(){
    lev = 2;
    while (hunt());
    cout << time;
}

int main(){
    input();
    solve();
    return 0;
}