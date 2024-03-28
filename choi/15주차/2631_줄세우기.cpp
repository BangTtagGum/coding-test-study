#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int len;
int arr[202];

int dp[202];

void input(){
    fastio
    cin >> len;
    for (int i = 1; i <= len; i ++) cin >> arr[i];
}

void solve(){

    int lis = 0;
    for (int i = 1; i <= len; i ++) {
        dp[i] = 1;
        for (int j = 1; j < i; j ++) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        lis = max(lis, dp[i]);
    }
    cout << len - lis;
}

int main(){
    input();
    solve();
    return 0;
}