#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
typedef pair<int, int> p;
p arr[2][3];

void input(){
    fastio
    cin >> n;
}

void solve(){
    int a, b, c;

    for (int i = 1; i <= n; i ++) {
        cin >> a >> b >> c;
        arr[i % 2][0] = {max(arr[(i + 1) % 2][0].first, arr[(i + 1) % 2][1].first) + a, min(arr[(i + 1) % 2][0].second, arr[(i + 1) % 2][1].second) + a};
        arr[i % 2][1] = {max(max(arr[(i + 1) % 2][0].first, arr[(i + 1) % 2][1].first), arr[(i + 1) % 2][2].first) + b, min(min(arr[(i + 1) % 2][0].second, arr[(i + 1) % 2][1].second), arr[(i + 1) % 2][2].second) + b};
        arr[i % 2][2] = {max(arr[(i + 1) % 2][1].first, arr[(i + 1) % 2][2].first) + c, min(arr[(i + 1) % 2][1].second, arr[(i + 1) % 2][2].second) + c};
        //slide();
    }
    
    int mx = 0, mn = 999999;
    for (int i = 0; i < 3; i ++){
        mx = max(mx, arr[n % 2][i].first);
        mn = min(mn, arr[n % 2][i].second);
    }

    cout << mx << ' ' << mn;
}

int main(){
    input();
    solve();
    return 0;
}