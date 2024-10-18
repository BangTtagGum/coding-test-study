#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m;
bool fix[44];

int dp[44][3];

void input(){
    fastio
    cin >> n >> m;
    int num;
    for (int i = 1; i <= m; i ++){
        cin >> num;
        fix[num] = 1;
    }
}

void solve(){ 

    dp[0][0] = 1, dp[1][0] = 1;

    for (int i = 2; i <= n; i ++){
        if (!fix[i] && !fix[i - 1]) {
            dp[i][1] = dp[i - 1][2] = dp[i - 2][0] + dp[i - 2][1];
        }
        dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
    }

    cout << dp[n][0] + dp[n][1];
}

int main(){
    input();
    solve();
    return 0;
}