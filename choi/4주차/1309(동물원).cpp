#include <iostream>
#include <algorithm>

using namespace std;

int n;
int dp[100001][3];

int main(){
    
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int div=10000-100+1;
    cin>>n;
    
    dp[1][0]=dp[1][1]=dp[1][2]=1;

    for(int i=2;i<=n;i++){
        dp[i][0]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%div;
        dp[i][1]=(dp[i-1][0]+dp[i-1][2])%div;
        dp[i][2]=(dp[i-1][0]+dp[i-1][1])%div;
    }

    cout<<(dp[n][0]+dp[n][1]+dp[n][2])%div;

}