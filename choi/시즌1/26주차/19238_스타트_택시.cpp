#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N, M, K;
bool map[21][21];
int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};
int i, j;
typedef struct PASSENGER{
  int sr, sc, er, ec;
}Passenger;
vector<Passenger> passengers;

void input(){
  cin >> N >> M >> K;
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cin >> map[r][c];
    }
  }
  cin >> i >> j;
  int a, b, c, d;
  for (int p = 1; p <= M; ++p){
    cin >> a >> b >> c >> d;
    passengers.push_back({a, b, c, d});
  }
}

void calcDistsFromDriver(vector<vector<int>> &dist){
  queue<pair<int, int>> q;
  
  q.push({i, j});
  dist[i][j] = 0;

  while (q.size()){
    auto [r, c] = q.front();
    q.pop();

    for (int d = 0; d < 4; ++d){
      int nr = r + dr[d];
      int nc = c + dc[d];
      if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
      if (map[nr][nc]) continue;
      if (dist[nr][nc] != -1) continue;

      q.push({nr, nc});
      dist[nr][nc] = dist[r][c] + 1;
    }
  }
}

int getOptimalPassenger(vector<vector<int>> &dist){
  calcDistsFromDriver(dist);

  int minDist = 20 * 20;
  int optPsgr = -1;
  for (int p = 0; p < passengers.size(); ++p){
    int r = passengers[p].sr;
    int c = passengers[p].sc;
    if (r == -1) continue;
    if (dist[r][c] == -1 || dist[r][c] > K) continue;
    if (minDist > dist[r][c]){
      minDist = dist[r][c];
      optPsgr = p;
    }
  }

  return optPsgr;
}

void solve(){
  int numPsgr = 0;
  vector<vector<int>> dist;
  sort(passengers.begin(), passengers.end(), [](auto &a, auto &b){
    if (a.sr == b.sr) return a.sc < b.sc;
    return a.sr < b.sr;
  });

  while (numPsgr < M){
    dist.assign(21, vector<int>(21, -1));
    int psgr = getOptimalPassenger(dist);
    if (psgr == -1) break;

    ++numPsgr;
    i = passengers[psgr].sr;
    j = passengers[psgr].sc;
    K -= dist[i][j];

    dist.assign(21, vector<int>(21, -1));
    calcDistsFromDriver(dist);

    i = passengers[psgr].er;
    j = passengers[psgr].ec;
    if (dist[i][j] == -1 || dist[i][j] > K) break;

    K += dist[i][j];
    passengers[psgr].sr = passengers[psgr].sc = -1;
  }

  numPsgr == M ? cout << K : cout << -1;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}