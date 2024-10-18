#include <iostream>
#include <queue>
#include <tuple>

using namespace std;

typedef tuple<int, int, int> p;
int m,n,map[501][501];
int di[]={1,-1,0,0}, dj[]={0,0,1,-1}, dp[501][501];

 
void bfs(int i, int j){

    priority_queue<p> pq;
    pq.push({map[i][j], i, j});
    dp[1][1]=1;

    while(!pq.empty()){
        p tmp = pq.top();
        pq.pop();
        int i=get<1>(tmp), j=get<2>(tmp);
        for(int d=0; d<4; d++){

        int ni=i+di[d], nj=j+dj[d];

        if(ni>m || nj>n || ni<1 || nj<1) continue;
        if(map[ni][nj]>=map[i][j]) continue;
        if(dp[ni][nj] == 0) pq.push({map[ni][nj], ni, nj});
            dp[ni][nj] += dp[i][j];
        }
    }
    
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin>>m>>n;
    for(int i=1;i<=m;i++){
        for(int j=1;j<=n;j++){
            cin>>map[i][j];
            dp[i][j]=0;
        }
    }
    
    bfs(1,1);
    // for(int i=1;i<=m;i++){
    //     for(int j=1;j<=n;j++){
    //         cout<<dp[i][j]<<' ';
    //     }
    //     cout<<endl;
    // }  
    
    cout<<dp[m][n];
    return 0;

}