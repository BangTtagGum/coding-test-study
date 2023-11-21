#include <iostream>
#include <algorithm>

using namespace std;

int n,m,map[9][9];
int di[]={1,-1,0,0}, dj[]={0,0,1,-1}, visited[9][9];
int ans;

void dfs(int i, int j){

    visited[i][j]=1;
    for(int d=0;d<4;d++){
        int ni=i+di[d], nj=j+dj[d];
        if(ni<1 || ni>n || nj<1 || nj>m) continue;
        if(visited[ni][nj] || map[ni][nj]) continue;
        dfs(ni,nj);
    }
}

void build(int cnt, int idx){
    if(cnt==3) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                visited[i][j]=0;
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(map[i][j]==2) dfs(i,j);
            }
        }
        int cnt=0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(map[i][j]==0 && visited[i][j]==0)
                cnt++;
            }     
        }
        ans=max(ans,cnt);
        //show();
        return;
    }
   
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(i*m+j<idx) continue;     //이전 벽보다 일련번호 크면
            if(map[i][j]!=0) continue;  //중복된 경우 없다
            map[i][j]=1;
            build(cnt+1, i*m+j);
            map[i][j]=0;
        }     
    }
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0);

    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1; j<=m; j++){
            cin>>map[i][j];
        }
    }

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(map[i][j]!=0) continue;
            map[i][j]=1;
            build(1, i*m+j);  //벽에 일련번호 부여
            map[i][j]=0;
        }     
    }

    cout<<ans;
}