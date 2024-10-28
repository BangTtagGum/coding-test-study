#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N, M, K;
int map[21][21], recentVisitor[21][21], recentTime[21][21];
int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
typedef struct SHARK{
  int r, c, dir;
}Shark;
vector<Shark> sharks;
vector<vector<vector<int>>> dirs;

void input(){
  cin >> N >> M >> K;

  sharks.resize(M + 1);
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cin >> map[r][c];
      if (map[r][c]) {
        sharks[map[r][c]] = {r, c, -1};
      }
    }
  }

  int d, d1, d2, d3, d4;
  for (int s = 1; s <= M; ++s){
    cin >> d;
    sharks[s].dir = d - 1;
  }

  dirs.assign(M + 1, vector<vector<int>>(4));
  for (int s = 1; s <= M; ++s){
    for (int d = 0; d < 4; ++d){
      cin >> d1 >> d2 >> d3 >> d4;
      dirs[s][d] = {d1 - 1, d2 - 1, d3 - 1, d4 - 1};
    }
  }
}

void init(){
  for (int s = 1; s <= M; ++s){
    recentVisitor[sharks[s].r][sharks[s].c] = s;
    recentTime[sharks[s].r][sharks[s].c] = 0;
  }
}

void showMap(){
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cout << map[r][c] << ' ';
    }
    cout << endl;
  }
  cout << endl;
}

void showPaths(){
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cout << recentVisitor[r][c] << ' ';
    }
    cout << endl;
  }
  cout << endl;
}

int getDir(int s, int turn){
  auto [r, c, dir] = sharks[s];
  int ret = -1;
  for (auto d : dirs[s][dir]){
    int nr = r + dr[d];
    int nc = c + dc[d];
    if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
    // Recent visit info updated only when new shark arrives here.
    if (recentVisitor[nr][nc] == s && turn - recentTime[nr][nc] <= K && ret == -1) 
      ret = d;
    if (recentVisitor[nr][nc] && turn - recentTime[nr][nc] <= K) continue;
    return d;
  }
  return ret;
}

void move(int s){
  auto [r, c, dir] = sharks[s];
  int nr = r + dr[dir];
  int nc = c + dc[dir];
  
  if (map[nr][nc] > s)
    sharks[map[nr][nc]].dir = -1;
  sharks[s].r = nr;
  sharks[s].c = nc;
  map[nr][nc] = s;
}

int simulate(int turn){
  int numShark = 0;
  // cout << turn << endl;

  for (int s = 1; s <= M; ++s){
    if (sharks[s].dir == -1) continue;
    map[sharks[s].r][sharks[s].c] = 0;
  }

  for (int s = M; s >= 1; --s){
    if (sharks[s].dir == -1) continue;
    sharks[s].dir = getDir(s, turn);
    move(s);
  }

  // showMap();

  for (int s = 1; s <= M; ++s){
    if (sharks[s].dir == -1) continue;
    ++numShark;
    recentVisitor[sharks[s].r][sharks[s].c] = s;
    recentTime[sharks[s].r][sharks[s].c] = turn;
  }

  // showPaths();

  return numShark;
}

void solve(){
  init();
  int turn = 0;
  while (turn < 1000){
    ++turn;
    if (simulate(turn) == 1){
      cout << turn;
      return;
    }
  }
  cout << -1;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}