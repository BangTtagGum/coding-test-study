#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
int num[5005];
long long dp[5005];

void input(){
    fastio
    char ch;
    while ((ch = cin.get()) != '\n') {
        num[++n] = ch - '0';
    }
}

bool solve(){
    
    if (num[1] == 0) return false;

    dp[0] = dp[1] = 1;

    for (int i = 2; i <= n; i ++){
        if (num[i] == 0){
            if (num[i - 1] != 1 && num[i - 1] != 2) return false;
            dp[i] = (dp[i] + dp[i - 2]) % 1000000;
        }
        else {
            dp[i] = (dp[i] + dp[i - 1]) % 1000000;
            if ((num[i] <= 6 && num[i - 1] == 2) || (num[i - 1] == 1)) {
                dp[i] = (dp[i] + dp[i - 2]) % 1000000;
            }
        }
    }

    return true;
}

int main(){
    input();
    if (solve()) cout << dp[n];
    else cout << 0;
    return 0;
}