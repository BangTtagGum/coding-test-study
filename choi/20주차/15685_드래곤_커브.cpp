#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define showsize 9
using namespace std;

int n;
int di[] = {0, -1, 0, 1}, dj[] = {1, 0, -1, 0};
bool visited[102][102];
vector<int> direction;

void input(){
    fastio
    cin >> n;
}

void calculate_direction(int d, int g){
    direction.push_back(d);
    
    for (int i = 0; i < g; i ++){
        for (int j = direction.size(); j > 0; j --){
            int rd = (direction[j - 1] + 1) % 4;
            direction.push_back(rd);
        }
    }
}

void dfs(int i, int j){
    visited[i][j] = 1;
    int s = direction.size();
    for (int d : direction){
        i += di[d];
        j += dj[d];
        visited[i][j] = 1;
    }
}

int count_rectangle(){
    int ans = 0;
    for (int i = 0; i < 100; i ++){
        for (int j = 0; j < 100; j ++){
            if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) ans ++;
        }
    }
    return ans;
}

void solve(){
    int i, j, d, g;

    while (n --){
        cin >> j >> i >> d >> g;
        calculate_direction(d, g);
        dfs(i, j);
        direction.clear();
    }
    
    cout << count_rectangle();
}

void show(){
    cout << '\n';
    for (int i = 0; i < showsize; i ++){
        for (int j = 0; j < showsize; j ++){
            cout << visited[i][j] << ' ';
        }
        cout << '\n';
    }
}

int main(){
    input();
    solve();
    //show();
    return 0;
}