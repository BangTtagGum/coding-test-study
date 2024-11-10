#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m;
char map[10][10];
int visited[10][10][10][10];
int ox, oy, rx, ry, bx, by;

int di[] = {1, 0, -1, 0}, dj[] = {0, 1, 0, -1};
queue<tuple<int, int, int, int>> q;

void input(){
    fastio
    cin >> n >> m;
    string str;
    for (int i = 0; i < n; i ++){
        cin >> str;
        for (int j = 0; j < m; j ++){
            if (str[j] == 'O') ox = i, oy = j;
            else if (str[j] == 'R') rx = i, ry = j;
            else if (str[j] == 'B') bx = i, by = j;
            map[i][j] = str[j]; 
        }
    }
}

void check_and_move(int sri, int srj, int sbi, int sbj, int d){
    
    int ri = sri, rj = srj, bi = sbi, bj = sbj;
    int nri, nrj, nbi, nbj;
    bool stuck = 0, rstuck = 0, bstuck = 0;

    while (1){
        nri = ri + di[d], nrj = rj + dj[d];
        nbi = bi + di[d], nbj = bj + dj[d];

        if (map[nri][nrj] == '#') rstuck = 1;
        if (map[nbi][nbj] == '#') bstuck = 1;

        if (ri == ox && rj == oy) rstuck = 1;
        if (bi == ox && bj == oy) return; 

        if (rstuck && bstuck) stuck = 1;  
        else if (bstuck && ri == bi - di[d] && rj == bj - dj[d]) 
            stuck = 1;
        else if (rstuck && bi == ri - di[d] && bj == rj - dj[d]) {
            if (ri == ox && rj == oy) return;
            stuck = 1;
        }
        
        if (stuck){
            if (visited[ri][rj][bi][bj]) return;
            q.push({ri, rj, bi, bj});
            visited[ri][rj][bi][bj] = visited[sri][srj][sbi][sbj] + 1;
            return;
        }
        
        if (!rstuck) ri = nri, rj = nrj;
        if (!bstuck) bi = nbi, bj = nbj;
    }
}

bool bfs(){

    q.push({rx, ry, bx, by});
    visited[rx][ry][bx][by] = 1;

    while (q.size()){
        int ri, rj, bi, bj;
        tie(ri, rj, bi, bj) = q.front();
        q.pop();

        if (ri == ox && rj == oy && visited[ri][rj][bi][bj] <= 11) return 1;

        for (int d = 0; d < 4; d ++){
            check_and_move(ri, rj, bi, bj, d);
        }
    }
    return 0;
    
}


int main(){
    input();
    cout << bfs();
    return 0;
}