#include <iostream>
#include <queue>
#include <cstdio>

using namespace std;

typedef pair<int,int> p;
int n, l, r;
int map[51][51], linked[51][51], checked[51][51];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
int sum, cnt, ans;
queue<p> q;

void show(){
    cout << '\n';
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cout << map[i][j] << ' ';
        }
        cout << '\n';
    }
    cout << '\n';
}

bool valid(int i, int j, int ni, int nj){
    
    int d;
    if(ni<1 || ni>n || nj<1 || nj>n) return false;
    if(linked[ni][nj] || checked[ni][nj]) return false;
    
    d = abs(map[ni][nj] - map[i][j]);
    if(d<l || d>r) return false;

    return true;
}

void bfs(int i, int j){

    checked[i][j] = linked[i][j] = -1;
    q.push({i,j});

    while(q.size()){
        p u = q.front();
        int i = u.first, j = u.second;
        q.pop();
        for(int d=0; d<4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if(!valid(i, j, ni, nj)) continue;
            
            linked[ni][nj] = checked[ni][nj] = -1;
            q.push({ni, nj});
            sum += map[ni][nj];
            cnt ++;
        }
    }
}

void move(int i, int j){

    q.push({i,j});
    linked[i][j] = 0;
    checked[i][j] = sum / cnt;

    while(q.size()){
        p u = q.front();
        int i = u.first, j = u.second;
        q.pop();
        for(int d=0; d<4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni<1 || ni>n || nj<1 || nj>n) continue;
            if(linked[ni][nj] == 0) continue;
            q.push({ni, nj});
            linked[ni][nj] = 0;
            checked[ni][nj] = sum / cnt;
        }
    }
}

void update(){
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(checked[i][j] == -1) continue;
            map[i][j] = checked[i][j];
        }
    }
}

int main(){

    ios::sync_with_stdio(false);

    cin >> n >> l >> r;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> map[i][j];
            linked[i][j] = 0;
        }
    }

    while(1){
        int moved = 0;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                checked[i][j] = 0;
            }
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(checked[i][j]) continue;
                cnt = 1;
                sum = map[i][j];
                bfs(i,j);
                if (cnt > 1) {
                    move(i,j);
                    moved = 1;
                }
                else{
                    linked[i][j] = 0;
                }
            }
        }

        if(!moved) break;
        update();
        ans ++;
        //show();
    }
    
    cout << ans;

}