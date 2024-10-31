#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define MAX 1000001
using namespace std;
int m, n;
int map[1000][1000];
int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};
typedef struct EDGE{
  int d, r, c;
}Edge;
struct compare{
  bool operator()(const Edge &a, const Edge &b){
    return a.d > b.d;
  }
};
// Weighted graph!!!

void input(){
  cin >> m >> n;
  for (int r = 0; r < m; ++r){
    for (int c = 0; c < n; ++c){
      cin >> map[r][c];
    }
  }
}

int getMinCost(){
  if (map[0][0] == -1) return -1;

  priority_queue<Edge, vector<Edge>, compare> pq;
  vector<vector<bool>> isVisited;
  isVisited.assign(m, vector<bool>(n, 0));

  isVisited[0][0] = 1;
  pq.push({map[0][0], 0, 0});

  while (pq.size()){
    auto [cost, r, c] = pq.top();
    pq.pop();

    if (r == m - 1 && c == n - 1) return cost;

    for (int d = 0; d < 4; ++d){ 
      int nr = r + dr[d];
      int nc = c + dc[d];

      if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
      if (map[nr][nc] == -1) continue;
      if (isVisited[nr][nc]) continue;

      isVisited[nr][nc] = 1;
      pq.push({cost + map[nr][nc], nr, nc});
    }
  }

  return -1;
}

void solve(){
  cout << getMinCost();
}

int main(){
  fastio
  input();
  solve();
  return 0;
}