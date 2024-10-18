#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>

using namespace std;

typedef tuple<int, int, int> p;
int m,n,map[101][101];
int si,sj,sd,ei,ej,ed;

int visited[101][101][4], di[]={0,0,1,-1}, dj[]={1,-1,0,0};
queue<p> q;

void bfs(){
    q.push({si,sj,sd});
    visited[si][sj][sd-1]=1;

    while(!q.empty()){ 
        p u=q.front();
        int i=get<0>(u), j=get<1>(u), d=get<2>(u);
        q.pop();
        for(int s=1;s<=4;s++){
            for(int k=1;k<=3;k++){
                int ni=i+k*di[s-1], nj=j+k*dj[s-1];

                if(s!=d) break;
                if(ni>m || ni<1 || nj>n || nj<1) break;
                if(map[ni][nj]) break;

                if(visited[ni][nj][s-1]) continue;
            
                q.push({ni,nj,s});
                visited[ni][nj][s-1]=visited[i][j][d-1]+1;
                }
            }
            
        for(int s=1;s<=4;s++){
            if(s+d==3 || s+d==7) continue;
            if(visited[i][j][s-1]) continue;

            q.push({i,j,s});
            visited[i][j][s-1]=visited[i][j][d-1]+1;
        }
        
    }
}

int main(){
    cin>>m>>n;
    for(int i=1;i<=m;i++){
        for(int j=1;j<=n;j++){
            cin>>map[i][j];
            for(int k=0;k<4;k++){
                visited[i][j][k]=0;
            }
        }
    }
    cin>>si>>sj>>sd>>ei>>ej>>ed;
    bfs();

    // for(int i=1;i<=m;i++){
    //     for(int j=1;j<=n;j++){
    //         for(int k=0;k<4;k++){
    //             cout<<i<<" "<<j<<" "<<k+1<<": "<<visited[i][j][k]-1<<'\n';
    //         }
    //         cout<<endl;
    //     }
    // }
    
    cout<<visited[ei][ej][ed-1]-1;

    return 0;
}