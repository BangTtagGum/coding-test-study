#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int r, c, k;
typedef pair<int, int> p;
int arr[101][101];
int row_len, col_len;

void input(){
    fastio
    cin >> r >> c >> k;
    int num;
    for (int i = 1; i <= 3; i ++){
        for (int j = 1; j <= 3; j ++){
            cin >> num;
            arr[i][j] = num;
        }
    }
    row_len = col_len = 3;
}

bool cmp(p a, p b){
    if (a.second == b.second) return a.first < b.first;
    return a.second < b.second;
}

void row_check(int row){

    vector<p> new_row;
    int row_cnt[101] = {0,};

    for (int i = 1; i <= col_len; i ++){
        if (arr[row][i]) row_cnt[arr[row][i]] ++; 
    }

    for (int i = 1; i <= 100; i ++){
        if (row_cnt[i]) new_row.push_back({i, row_cnt[i]});
    }

    sort(new_row.begin(), new_row.end(), cmp);

    int check_cnt = min((int)new_row.size(), 50);
    for (int i = 1; i <= 100; i ++) arr[row][i] = 0;
    for (int i = 1; i <= check_cnt; i ++){
        arr[row][2 * i - 1] = new_row[i - 1].first;
        arr[row][2 * i] = new_row[i - 1].second;
    }

    col_len = max(col_len, 2 * check_cnt);
}

void col_check(int col){

    vector<p> new_col;
    int col_cnt[101] = {0,};

    for (int i = 1; i <= row_len; i ++){
        if (arr[i][col]) col_cnt[arr[i][col]] ++; 
    }

    for (int i = 1; i <= 100; i ++){
        if (col_cnt[i]) new_col.push_back({i, col_cnt[i]});
    }

    sort(new_col.begin(), new_col.end(), cmp);
    
    int check_cnt = min((int)new_col.size(), 50);
    for (int i = 1; i <= 100; i ++) arr[i][col] = 0;
    for (int i = 1; i <= check_cnt; i ++){
        arr[2 * i - 1][col] = new_col[i - 1].first;
        arr[2 * i][col] = new_col[i - 1].second;
    }

    row_len = max(row_len, 2 * check_cnt);
}



int main(){
    input();
    int t = 0;
    while (arr[r][c] != k && t <= 100) {
        int len;
        if (row_len >= col_len) {
            len = row_len;
            for (int i = 1; i <= len; i ++) row_check(i);
        }
        else {
            len = col_len;
            for (int i = 1; i <= len; i ++) col_check(i);
        }
        t ++;
    }
    if (t == 101) cout << -1;
    else cout << t;
    return 0;
}