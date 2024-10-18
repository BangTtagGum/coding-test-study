#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int l, r, c;
char map[31][31][31];

queue<tuple<int, int, int, int>> q;
int si, sj, sk, ei, ej, ek;
bool visited[31][31][31];
int di[] = {0, 0, 1, -1, 0, 0};
int dj[] = {1, -1, 0, 0, 0, 0};
int dk[] = {0, 0, 0, 0, -1, 1};

void input(){
    fastio
}

void init() {

    char ch;

    for (int k = 1; k <= l; k ++){
        for (int i = 1; i <= r; i ++){
            for (int j = 1; j <= c; j ++){
                while ((ch = cin.get()) == '\n');
                map[i][j][k] = ch;
                visited[i][j][k] = 0;
                if (ch == 'S') si = i, sj = j, sk = k;
                if (ch == 'E') ei = i, ej = j, ek = k;
            }
        }
    }

    while (q.size()) q.pop();
}

void bfs(){

    q.push({si, sj, sk, 0});
    visited[si][sj][sk] = 1;

    while (q.size()){
        int i, j, k, dist;
        tie(i, j, k, dist) = q.front();
        q.pop();
        if (i == ei && j == ej && k == ek) {
            cout << "Escaped in " << dist << " minute(s).\n";
            return;
        }
        for (int d = 0; d < 6; d ++){
            int ni = i + di[d];
            int nj = j + dj[d];
            int nk = k + dk[d];
            if (ni < 1 || ni > r || nj < 1 || nj > c || nk < 1 || nk > l) continue;
            if (visited[ni][nj][nk]) continue;
            if (map[ni][nj][nk] == '#') continue;
            q.push({ni, nj, nk, dist + 1});
            visited[ni][nj][nk] = 1;
        }
    }

    cout << "Trapped!\n";
}

void solve(){
    while (1) {
        cin >> l >> r >> c;
        if (!l && !r && !c) return;
        init();
        bfs();
    }
}

int main(){
    input();
    solve();
    return 0;
}