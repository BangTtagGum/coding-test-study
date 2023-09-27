#include <iostream>
#include <queue>

using namespace std;

int n,m,ans;
char map[501][501];
int di[]={1,-1,0,0}, dj[]={0,0,1,-1};

void check(int i, int j, char c){
    if(i==1 && c=='U') map[i][j]='O';
    if(i==n && c=='D') map[i][j]='O';
    if(j==1 && c=='L') map[i][j]='O';
    if(j==m && c=='R') map[i][j]='O';
    if(map[i][j]=='O') ans++;
}

void bfs(int i, int j){
    queue<pair<int,int>> q;
    q.push({i,j});

    while(!q.empty()){
        i = q.front().first;
        j = q.front().second;
        q.pop();
        int ni,nj;
        for(int d=0;d<4;d++){
            ni=i+di[d];
            nj=j+dj[d];
            if(ni<1 || ni>n || nj<1 || nj>m) continue;
            if(map[ni][nj]=='O') continue;
            if(d==0 && map[ni][nj]!='U') continue;
            if(d==1 && map[ni][nj]!='D') continue;
            if(d==2 && map[ni][nj]!='L') continue;
            if(d==3 && map[ni][nj]!='R') continue;
            
            q.push({ni,nj}); 
            ans++;
        }
    }

    
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>map[i][j];
            check(i,j,map[i][j]);
        }
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(map[i][j]=='O') {
                bfs(i,j);
            }
        }
    }
    cout<<ans;

}