#include <iostream>
#include <algorithm>
#include <stack>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;

int ans = 0;
stack<pair<int, int>> st;

void input(){
    fastio
    cin >> n;
}

void make_stair(int h, int i){
    int idx = i;
    while (st.size() && st.top().first > h){
        pair<int, int> t = st.top();
        ans = max(ans, (i - t.second) * t.first);
        st.pop();
        idx = t.second;
    }
    if (h == -1) return;
    st.push({h, idx});
}

void solve(){
    int h;
    for (int i = 1; i <= n; i ++){
        cin >> h;
        make_stair(h, i);
    }
    make_stair(-1, n + 1);
    cout << ans;
}

int main(){
    input();
    solve();
    return 0;
}