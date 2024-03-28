#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define DIV (1000000000+1+1+1+1+1+1+1)
using namespace std;

int n, l, r;
typedef long long ll;
ll dp[101][101][101];

void input(){
    fastio
    cin >> n >> l >> r;
    for (int i = 0; i <= n; i ++){
        for (int j = 0; j <= l; j ++){
            for (int k = 0; k <= r; k ++){
                dp[i][j][k] = -1;
            }
        }
    }
}

ll solve(int num, int left, int right){
    ll sum = 0;

    if (left + right > num + 1 || left < 1 || right < 1 || num < 1) return 0;
    if (dp[num][left][right] != -1) return dp[num][left][right];
    

    sum = (solve(num - 1, left - 1, right) + solve(num - 1, left, right - 1) + solve(num - 1, left, right) * (num - 2)) % DIV;
    return dp[num][left][right] = sum;
}

int main(){
    input();
    dp[1][1][1] = 1;
    cout << solve(n, l, r);
    return 0;
}