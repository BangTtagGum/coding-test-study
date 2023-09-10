#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int n,m;
int visited[100001],d[100001];
//d 배열은 해당 노드까지 최단거리 경우의 수이다
queue<int> q;

bool check(int x){
    if(x<0 || x>100000) return false;
    return true;
}

void visit(int u, int v) {
    
    if(!visited[v]){
        q.push(v);
        visited[v]=visited[u]+1;
    }

    //처음 방문 시 OR 재방문이지만 최단거리 시
    //이전 노드의 방법만큼 추가된다
    if(visited[v]==visited[u]+1){
        d[v]+=d[u];
    }
}

void bfs(int u){
    
    d[u]=1;
    visited[u]=1;
    q.push(u);

    while(!q.empty()){
        int u=q.front();
        q.pop();
        if(u==m) break;
        if(check(u-1)) visit(u,u-1);
        if(check(u+1)) visit(u,u+1);
        if(check(2*u)) visit(u,2*u);
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin>>n>>m;
    bfs(n);
    cout<<visited[m]-1<<'\n'<<d[m];
}