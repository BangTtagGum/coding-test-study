#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>

using namespace std;

typedef pair<int, int> ii;
int n;
int map[26][26], visited[26][26];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};

void input(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    string str;
    for (int i = 1; i <= n; i ++){
        cin >> str;
        for (int j = 1; j <= n; j ++){
            map[i][j] = str[j - 1] - '0';
            visited[i][j] = 0;
        }
    }
}

int bfs(int i, int j){
    queue<ii> q;
    int cnt = 1;

    q.push({i, j});
    visited[i][j] = 1;

    while(q.size()){
        int i = q.front().first;
        int j = q.front().second;
        // cout << i << ' ' << j << '\n';
        q.pop();
        for (int d = 0; d < 4; d ++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 1 || ni > n || nj < 1 || nj > n) continue;
            if (map[ni][nj] == 0 || visited[ni][nj]) continue;
            visited[ni][nj] = 1;
            q.push({ni, nj});
            cnt ++;
        }
    }
    return cnt;
}


int main(){
    input();
    vector<int> v;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            if (map[i][j] == 0 || visited[i][j]) continue;
            // cout << i << ' ' << j << '\n';
            int cnt = bfs(i, j);
            v.push_back(cnt);
        }
    }
    sort(v.begin(), v.end());
    cout << v.size() << '\n';
    for (int tmp : v) cout << tmp << '\n';
    
}