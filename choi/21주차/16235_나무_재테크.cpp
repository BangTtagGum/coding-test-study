#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m, k, x, y, z;
int nut[11][11], map[11][11];
deque<int> tree[11][11];
int di[] = {-1, -1, -1, 0, 0, 1, 1, 1}, dj[] = {-1, 0, 1, -1, 1, -1, 0, 1};

void input(){
    fastio
    cin >> n >> m >> k;
    for (int i = 1; i <= n; ++ i){
        for (int j = 1; j <= n; ++ j){
            cin >> nut[i][j];
            map[i][j] = 5;
        }
    }
    for (int i = 1; i <= m; ++ i){
        cin >> x >> y >> z;
        tree[x][y].push_back(z);
    }
    for (int i = 1; i <= n; ++ i){
        for (int j = 1; j <= n; ++ j){
            if (tree[i][j].empty()) continue;
            sort(tree[i][j].begin(), tree[i][j].end());
        }
    }
}

void spring_summer(){
    int i, j, s, dead = 0;
    for (i = 1; i <= n; ++ i){
        for (j = 1; j <= n; ++ j){
            if ((s = tree[i][j].size()) == 0) continue;

            dead = 0;
            while (s --){
                if(tree[i][j].front() <= map[i][j]){
                    map[i][j] -= tree[i][j].front();
                    tree[i][j].push_back(tree[i][j].front() + 1);   
                }
                else{
                    dead += (tree[i][j].front() / 2);
                }
                tree[i][j].pop_front();
            }
            map[i][j] += dead;
        }
    }
}

void fall_winter(){
    int i, j, d, s;
    for (i = 1; i <= n; ++ i){
        for (j = 1; j <= n; ++ j){
            map[i][j] += nut[i][j];
            if ((s = tree[i][j].size()) == 0) continue;
            while (s --){
                if (tree[i][j][s] % 5) continue;
                for (d = 0; d < 8; ++ d){
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (ni < 1 || ni > n || nj < 1 || nj > n) continue;
                    tree[ni][nj].push_front(1);
                }
            }
        }
    }
}

void solve(){
    int ans = 0;

    for (int year = 0; year < k; year ++){
        spring_summer();
        fall_winter();
    }

    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            ans += tree[i][j].size();
        }
    }

    cout << ans;
}

int main(){
    input();
    solve();
    return 0;
}