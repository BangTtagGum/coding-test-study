#include <iostream>
#include <algorithm>
#define div 1000000000

using namespace std;

int n,k;
long long dp[201][201];
// long long f(int a, int b){

//     if(dp[a][b]) return dp[a][b];
//     if(a==0 || b==1) return dp[a][b]=1;
//     long long sum=0;

//     for(int i=0;i<=a;i++){
//         sum=(sum+f(a-i,b-1))%div;
//     }
//     return dp[a][b]=sum;
// }

int main(){
    cin>>n>>k;

    for(int i=0;i<=n;i++){
        for(int j=1;j<=k;j++){
            if(i==0 || j==1) dp[i][j]=1;
            else dp[i][j]=0;
        }
    }
    
    for(int i=1;i<=n;i++){
        for(int j=2; j<=k; j++){
            dp[i][j]=(dp[i-1][j]+dp[i][j-1])%div;
        }
    }

    //f(n,k);
    cout<<dp[n][k];
    return 0;
}