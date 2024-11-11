#include <iostream>
#include <string>
#include <queue>
#include <stack>

using namespace std;

typedef pair<int, int> p;
char map[13][7];
int visited[13][7], di[]={1,-1,0,0}, dj[]={0,0,1,-1};
int cnt,ans,bomb;

void bfs(int i, int j){
    queue<p> q;
    q.push({i,j});
    visited[i][j]=1;
    while(q.size()){
        int i=q.front().first, j=q.front().second;
        q.pop();
        for(int d=0; d<4; d++){
            int ni=i+di[d], nj=j+dj[d];
            if(ni<1 || ni>12 || nj<1 || nj>6) continue;
            if(visited[ni][nj]) continue;
            if(map[ni][nj]=='.') continue;
            if(map[ni][nj]!=map[i][j]) continue;
            q.push({ni,nj});
            visited[ni][nj]=1;
            cnt++;
        }
    }
}

void clear(int i, int j){
    queue<p> q;
    q.push({i,j});
    visited[i][j]=0;
    while(q.size()){
        int i=q.front().first, j=q.front().second;
        q.pop();
        for(int d=0; d<4; d++){
            int ni=i+di[d], nj=j+dj[d];
            if(ni<1 || ni>12 || nj<1 || nj>6) continue;
            if(visited[ni][nj]==0) continue;
            if(map[ni][nj]=='.') continue;
            if(map[ni][nj]!=map[i][j]) continue;
            q.push({ni,nj});
            visited[ni][nj]=0;
        }
    }
}

void move(){

    for(int j=1;j<=6;j++){
        stack<char> st;

        for(int i=12; i>=1; i--){
            if(visited[i][j]==0)
                st.push(map[i][j]);    
            else {
                visited[i][j]=0;
            }
        }
        while(st.size()!=12) st.push('.');
        for(int i=1; i<=12; i++){
            map[i][j]=st.top();
            st.pop();
        }
    }
}


int main() { 
    
    for(int i=1; i<=12; i++){
        string str;
        cin>>str;
        for(int j=1; j<=6; j++){
            map[i][j]=str[j-1];
            visited[i][j]=0;
        }
    }
    while(1){
        bomb=0;
        for(int i=1; i<=12; i++){
            for(int j=1; j<=6; j++){
                if(map[i][j]=='.') continue;
                if(visited[i][j]) continue;
                cnt=1;
                bfs(i,j);
                if(cnt>3) bomb++;
                else clear(i,j); 
            }
        }
        move();
        if(bomb==0) break;
        else ans++;
    }
    
    cout<<ans;
    return 0;
}
