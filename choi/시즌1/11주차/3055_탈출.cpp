#include <iostream>
#include <queue>

using namespace std;

int r, c, si, sj, ei, ej;
char map[51][51];
int visited[51][51]; 
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
queue<pair<int, int>> q;

void bfs(){

    q.push({si, sj});
    visited[si][sj] = 1;

    while(q.size()){
        auto u = q.front();
        int i = u.first, j = u.second;
        q.pop();

        for(int d=0; d<4; d++){
            int ni = i + di[d], nj = j + dj[d];

            if (ni<1 || ni>r || nj<1 || nj>c) continue;
            if (visited[ni][nj]) continue;
            if (map[ni][nj] == 'X') continue;

            if (map[i][j] == '*'){
                if (map[ni][nj] == '.') map[ni][nj] = '*';
                else continue;
            }
            
            visited[ni][nj] = visited[i][j] + 1;
            q.push({ni, nj});
        }
    }

}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> r >> c;

    for (int i=1; i<=r; i++){
        for (int j=1; j<=c; j++){
            cin >> map[i][j];
            visited[i][j] = 0;

            if (map[i][j] == '*'){
                visited[i][j] = 1;
                q.push({i, j});
            }
            else if (map[i][j] == 'D') {
                ei = i, ej = j;
            }
            else if (map[i][j] == 'S'){
                si = i, sj = j;
            }

        }
    }

    bfs();

    visited[ei][ej] ? cout << visited[ei][ej] - 1 : cout << "KAKTUS";

    return 0;
}