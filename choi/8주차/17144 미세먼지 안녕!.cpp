#include <iostream>
#include <cstdio>

using namespace std;

typedef pair<int, int> ii;
int r, c, t;
int map[51][51], reserved[51][51];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void show(){
    cout<<'\n';
    for(int i=1; i<=r; i++){
        for(int j=1; j<=c; j++){
            printf("%3d", map[i][j]);
        }
        cout<<'\n';
    }
    cout<<'\n';
}

bool is_valid(int ni, int nj){
    if(map[ni][nj]==-1) return false;
    if(ni<1 || ni>r || nj<1 || nj>c) return false;
    return true;
}

void contamiate(int i, int j){
    int cnt = 0;

    for(int d=0; d<4; d++){
        int ni = i+di[d];
        int nj = j+dj[d];
        if(!is_valid(ni,nj)) continue;
        
        reserved[ni][nj] += (map[i][j]/5);
        cnt++;
    }
    
    map[i][j] -= (map[i][j]/5)*cnt;
}

void clean(int num, int pos){

    int i,j;
    map[pos][1] = 0;
    if(num == 1){
        i = pos-1, j = 1;
        for(; i > 1; --i) map[i][j] = map[i-1][j];
        for(; j < c; ++j) map[i][j] = map[i][j+1];
        for(; i < pos; ++i) map[i][j] = map[i+1][j];
        for(; j > 1; --j) map[i][j] = map[i][j-1];
        
    }
    else{
        i = pos+1, j = 1;
        for(; i < r; ++i) map[i][j] = map[i+1][j];
        for(; j < c; ++j) map[i][j] = map[i][j+1];
        for(; i > pos; --i) map[i][j] = map[i-1][j];
        for(; j > 1; --j) map[i][j] = map[i][j-1];
    }
    map[pos][1] = -1;
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> r >> c >> t;

    for(int i=1; i<=r; i++){
        for(int j=1; j<=c; j++){
            cin >> map[i][j];
            reserved[i][j]=0;
        }
    }

    while(t--){
        
        for(int i=1; i<=r; i++){
            for(int j=1; j<=c; j++){
                if(map[i][j]>0) contamiate(i,j);
            }
        }

        for(int i=1; i<=r; i++){
            for(int j=1; j<=c; j++){
                map[i][j] += reserved[i][j];
                reserved[i][j]=0;
            }
        }

        //show();

        int num = 1;
        for(int i=1; i<=r; i++){
            if(map[i][1]<0){
                clean(num, i);
                num++;
            }
        }

        //show();
    }

    int ans = 0;
    for(int i=1; i<=r; i++){
        for(int j=1; j<=c; j++){
            if(map[i][j]==-1) continue;
            ans += map[i][j];
        }
    }

    cout<<ans;
    
}