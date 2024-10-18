#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n, m;

int parent[500005];

void init(){
    fastio
    cin >> n >> m;
    for (int i = 0; i < n; i ++) parent[i] = -1;
}

int find(int x){
    if (parent[x] < 0) return x;
    return parent[x] = find(parent[x]);
}

void merge(int a, int b){
    a = find(a), b = find(b);

    if (parent[a] < parent[b])
        parent[b] = a;
    else {
        if (parent[a] == parent[b])
            parent[b] --;
        parent[a] = b;
    }
}

void solve(){
    int a, b;
    for (int i = 1; i <= m; i ++){
        cin >> a >> b;
        if (find(a) == find(b)) {
            cout << i;
            return;
        }
        merge(a, b);
    }   
    cout << 0;
}

int main(){
    init();
    solve();
    return 0;
}