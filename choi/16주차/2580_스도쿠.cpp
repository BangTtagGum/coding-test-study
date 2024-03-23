#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int map[9][9];

int n;
vector<pair<int, int>> blank;
bool row[9][9], col[9][9], box[9][9];

void input(){
    fastio
    int num;
    for (int i = 0; i < 9; i ++){
        for (int j = 0; j < 9; j ++){
            cin >> num;
            map[i][j] = num;
            if (!num) blank.push_back({i, j});
            else {
                row[i][num - 1] = 1;
                col[j][num - 1] = 1;
                box[(i / 3) * 3 + (j / 3)][num - 1] = 1;
            }
        }
    }
    n = blank.size();
}

bool check_entry(int i, int j, int num){
    if (row[i][num - 1] || col[j][num - 1] || box[(i / 3) * 3 + (j / 3)][num - 1]) return false;
    return true;
}

void set_or_reset(int i, int j, int num, bool s){
    if (s) {
        map[i][j] = num;
        row[i][num - 1] = 1;
        col[j][num - 1] = 1;
        box[(i / 3) * 3 + (j / 3)][num - 1] = 1;
    }
    else {
        map[i][j] = 0;
        row[i][num - 1] = 0;
        col[j][num - 1] = 0;
        box[(i / 3) * 3 + (j / 3)][num - 1] = 0;
    }
}

void show_map(){
    for (int i = 0; i < 9; i ++){
        for (int j = 0; j < 9; j ++){
            cout << map[i][j] << ' ';
        }
        cout << '\n';
    }
}

void dfs(int cnt){
    int i, j;

    if (cnt == n) {
        show_map();
        exit(0);
    }
    
    i = blank[cnt].first, j = blank[cnt].second;
    for (int num = 1; num <= 9; num ++){
        if (check_entry(i, j, num)) {
            set_or_reset(i, j, num, 1);
            dfs(cnt + 1);
            set_or_reset(i, j, num, 0);
        }
    }
}


int main(){
    input();

    if (n) dfs(0);
    else show_map();

    return 0;
}