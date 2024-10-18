#include <iostream>
#include <algorithm>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, k;

int cnt[100001], prv[100001];
queue<int> q;


void input(){
    fastio
    cin >> n >> k;
}

int pos(int x, int dir){
    if (dir == 0) return x + 1;
    if (dir == 1) return x - 1;
    if (dir == 2) return 2 * x;
}

void bfs(){
    q.push(n);
    prv[n] = -1;
    cnt[n] = 1;

    while (q.size()){
        int cur = q.front();
        q.pop();
        if (cur == k) {
            cout << cnt[cur] - 1 << '\n';
            return;
        }
        for (int d = 0; d < 3; d ++){
            int nxt = pos(cur, d);
            if (nxt < 0 || nxt > 100000) continue;
            if (cnt[nxt]) continue;
            prv[nxt] = cur;
            cnt[nxt] = cnt[cur] + 1;
            q.push(nxt);
            
        }
    }
}

void back_track(int cur){
    if (cur == -1) return;
    back_track(prv[cur]);
    cout << cur << ' ';
}

int main(){
    input();
    bfs();
    back_track(k);
    return 0;
}