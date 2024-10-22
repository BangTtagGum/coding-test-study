#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
vector<int> w;

void input(){
  cin >> n;
  w.resize(2 * n);
  for (int i = 0; i < 2 * n; ++i)
    cin >> w[i];
}

void solve(){
  int s = 0, e = 2 * n - 1;
  priority_queue<int, vector<int>, greater<int>> pq;

  sort(w.begin(), w.end());
  while (s < e)
    pq.push(w[s++] + w[e--]);
  cout << pq.top();
}

int main(){
  fastio
  input();
  solve();
  return 0;
}