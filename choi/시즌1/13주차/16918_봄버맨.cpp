#include <iostream>
#include <algorithm>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int r, c, n;
int map[201][201];

int t;
queue<pair<int, int>> q;
bool visited[201][201];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void input(){
    fastio 
    cin >> r >> c >> n;
    char ch;
    for (int i = 1; i <= r; i ++){
        for (int j = 1; j <= c; j ++){
            cin >> ch;
            if (ch == 'O') map[i][j] = 0;
            else map[i][j] = -1;
        }
    }
}

void bfs(int i, int j){

    for (int i = 1; i <= r; i ++){
        for (int j = 1; j <= c; j ++){
            visited[i][j] = 0;
        }
    }

    q.push({i, j});
    visited[i][j] = 1;
    map[i][j] = -1;

    while (q.size()){
        i = q.front().first;
        j = q.front().second;
        q.pop();

        for (int d = 0; d < 4; d ++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 1 || ni > r || nj < 1 || nj > c) continue;
            if (visited[ni][nj]) continue;
            if (map[ni][nj] == -1) continue;
            if (map[ni][nj] <= t - 3){
                q.push({ni, nj});
                visited[ni][nj] = 1;
            }
            map[ni][nj] = -1;
        }
    }
}

void print(){
    for (int i = 1; i <= r; i ++){
        for (int j = 1; j <= c; j ++){
            if (map[i][j] == -1) cout << '.';
            else cout << 'O';
        }
        cout << '\n';
    }
}

void solve(){

    t ++;

    while (t < n) {
        
        t ++;

        for (int i = 1; i <= r; i ++){
            for (int j = 1; j <= c; j ++){
                if (map[i][j] == -1){
                    map[i][j] = t;
                }
            }
        }

        if (t == n) break;

        t ++;

        for (int i = 1; i <= r; i ++){
            for (int j = 1; j <= c; j ++){
                if (map[i][j] == -1) continue;
                if (map[i][j] <= t - 3) map[i][j] = 
            }
        }

        if (t == n) break;
    }

    print();
}

int main(){
    input();
    solve();
    return 0;
}