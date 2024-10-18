#include <iostream>
#include <algorithm>
#define INF 22222222
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
int edge[16][16];
int dp[16][1 << 16];

void input(){
    fastio
    cin >> n;
    for (int i = 0; i < n; i ++){
        for (int j = 0; j < n; j ++){
            cin >> edge[i][j];
            if (edge[i][j] == 0) edge[i][j] = INF;
        }
    }
}

int dfs(int start, int cur, int visited){
    if (visited == (1 << n) - 1) return edge[cur][start];
    
    if (dp[cur][visited]) return dp[cur][visited];

    dp[cur][visited] = INF;
    for (int nxt = 0; nxt < n; nxt ++){
        if (visited & (1 << nxt)) continue;
        if (edge[cur][nxt] == INF) continue;
        dp[cur][visited] = min(dp[cur][visited], dfs(start, nxt, visited | (1 << nxt)) + edge[cur][nxt]);
    }

    return dp[cur][visited];
}

int main(){
    input();
    cout << dfs(0, 0, 1);
    return 0;
}