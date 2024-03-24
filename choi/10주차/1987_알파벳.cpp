#include <iostream>
#include <algorithm>

using namespace std;

int r, c, ans;
char map[21][21];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
int alpha[26];

void dfs(int i, int j, int cnt){

    ans = max(ans, cnt);

    for (int d=0; d<4; d++){
        int ni = i + di[d];
        int nj = j + dj[d];
        if (ni<1 || ni>r || nj<1 || nj>c) continue;
        if (alpha[map[ni][nj] - 'A']) continue;

        alpha[map[ni][nj] - 'A'] = 1;
        dfs(ni, nj, cnt+1);
        alpha[map[ni][nj] - 'A'] = 0;
    }
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0);
    
    cin >> r >> c;

    char ch;
    for (int i=1; i<=r; i++){
        for (int j=1; j<=c; j++){
            cin >> ch;
            map[i][j] = ch;
        }
    }

    alpha[map[1][1] - 'A'] = 1;
    dfs(1, 1, 1);
    
    cout << ans;
}