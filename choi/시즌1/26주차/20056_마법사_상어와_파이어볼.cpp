#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdio>
#include <unordered_map>
#include <unordered_set>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N, M, K;
typedef struct FIREBALL{
  int r, c, m, s, d;
}Fireball;
vector<Fireball> fireballs;
vector<vector<int>> map, numMerged;
vector<pair<int, int>> points;
int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};

void input(){
  cin >> N >> M >> K;
  map.assign(N, vector<int>(N, -1));
  numMerged.assign(N, vector<int>(N, 0));

  int r, c, m, s, d;
  fireballs.resize(M);
  for (int i = 0; i < M; ++i){
    cin >> r >> c >> m >> s >> d;
    fireballs[i] = {r - 1, c - 1, m, s, d};
  }
}

void init(){
  for (auto p : points){
    map[p.first][p.second] = -1;
    numMerged[p.first][p.second] = 0;
  }
  points.clear();
}

void mergeFireballs(int child, int parent){
  fireballs[parent].m += fireballs[child].m;
  fireballs[parent].s += fireballs[child].s;
  if (fireballs[parent].d != -1 && fireballs[parent].d % 2 != fireballs[child].d % 2) 
    fireballs[parent].d = -1;
}

void moveFireballs(){
  for (int i = 0; i < fireballs.size(); ++i){
    int nr = (fireballs[i].r + (fireballs[i].s % N) * dr[fireballs[i].d] + N) % N;
    int nc = (fireballs[i].c + (fireballs[i].s % N) * dc[fireballs[i].d] + N) % N;

    if (map[nr][nc] != -1){
      mergeFireballs(i, map[nr][nc]);
    }
    else {
      fireballs[i].r = nr;
      fireballs[i].c = nc;
      map[nr][nc] = i;
      points.push_back({nr, nc});
    }
    numMerged[nr][nc]++;
  }
}

void updateFireballs(){
  vector<Fireball> tmp;

  for (auto p : points){
    int idx = map[p.first][p.second];
    if (numMerged[p.first][p.second] == 1){
      tmp.push_back(fireballs[idx]);
      continue;
    }

    int m = fireballs[idx].m / 5;
    if (!m) continue;
    int s = fireballs[idx].s / numMerged[p.first][p.second];
    bool isDiagonal = (fireballs[idx].d == -1);
    for (int d = isDiagonal; d < 8; d = d + 2)
      tmp.push_back({fireballs[idx].r, fireballs[idx].c, m, s, d});
  }

  fireballs = tmp;
}

int getTotalMass(){
  int ret = 0;
  for (int i = 0; i < fireballs.size(); ++i)
    ret += fireballs[i].m;
  return ret;
}

void solve(){
  // show();
  while (K--){
    init();
    moveFireballs();
    updateFireballs();
  }
  cout << getTotalMass();
}

int main(){
  fastio
  input();
  solve();
  return 0;
}