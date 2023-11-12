#include <iostream>

using namespace std;

int n,m,r,c,d;
int map[51][51];
int di[] = {-1,0,1,0}, dj[] = {0,1,0,-1};

int cnt;

bool is_valid(int ni, int nj){
    return (map[ni][nj]!=1);
}

bool is_dirty(int i, int j){
    for(int s=0; s<4; s++){
        int ni = i+di[s];
        int nj = j+dj[s];
        if(!is_valid(ni,nj)) continue;
        if(map[ni][nj]==0) return 1;
    }
    return 0;
}

int ninety_dir(int s){
    return (s+3)%4;
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m >> r >> c >> d;
    r++;
    c++;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> map[i][j];
        }
    }

    while(1){
        if(map[r][c]==0) {
            map[r][c]=-1;
            cnt++;
        }
        if(is_dirty(r,c)){
            d = ninety_dir(d);
            int nr = r + di[d];
            int nc = c + dj[d];
            if(is_valid(nr, nc) && map[nr][nc]==0){
                r = nr;
                c = nc;
                continue;
            }
        }
        else{
            int nr = r - di[d];
            int nc = c - dj[d];
            if(is_valid(nr, nc)){
                r = nr;
                c = nc;
                continue;
            }
            break;
        }
    }

    cout << cnt;

}