#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
int boxes[1001];

int dp[1001];

void input(){
    fastio
    cin >> n;
    for (int i = 1; i <= n; i ++){
        cin >> boxes[i];
        dp[i] = 1;
    }
}

void solve(){
    int ans = 0;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j < i; j ++){
            if (boxes[i] > boxes[j]){
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
    }
    for (int i = 1; i <= n; i ++){
        ans = max(ans, dp[i]);
    }
    cout << ans;
}

int main(){
    input();
    solve();

    return 0;
}