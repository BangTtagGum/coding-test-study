#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_set>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define INF 111111111

using namespace std;
using p = pair<int, int>;

int n, k;
vector<int> graph[100001];
bool isComplex[100001], isVisited[100001];
int answer;

void init(){
  cin >> n >> k;
  int i, j, w;
  for (int e = 1; e < n; ++e){
    cin >> i >> j >> w;
    graph[i].push_back(j);
    graph[j].push_back(i);
  }
  int c;
  while (k--){
    cin >> c;
    isComplex[c] = 1;
  }
}

bool dfs(int cur){
  bool ret = 0;
  isVisited[cur] = 1;

  for (auto &nxt : graph[cur]){
    if (isVisited[nxt]) continue;
    ret += dfs(nxt);
  }

  if (ret || isComplex[cur]) {
    ++answer;
    return 1;
  }
  return 0;
}

void solve(){
  for (int i = 1; i <= n; ++i){
    if (isComplex[i] && !isVisited[i])
      dfs(i);
  }
  cout << answer;
}

int main(){
  fastio
  init();
  solve();
  return 0;
}