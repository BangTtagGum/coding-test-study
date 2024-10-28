#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

vector<vector<int>> map(4, vector<int>(4, 0));
int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dc[] = {0, -1, -1, -1, 0, 1, 1, 1};
typedef struct FISH{
  int r, c, dir;
}Fish;
vector<Fish> fishes;
int answer;

void input(){
  int a, b;
  fishes.resize(17);
  for (int r = 0; r < 4; ++r){
    for (int c = 0; c < 4; ++c){
      cin >> a >> b;
      map[r][c] = a;
      fishes[a] = {r, c, b - 1};
    }
  }
}

void simulate(int r, int c, int dir, int score){
  
  for (int f = 1; f <= 16; ++f){
    if (fishes[f].dir == -1) continue;
    auto [i, j, dir] = fishes[f];

    for (int turn = 0; turn < 8; ++turn){
      int ni = i + dr[(dir + turn) % 8];
      int nj = j + dc[(dir + turn) % 8];
      if (ni < 0 || ni > 3 || nj < 0 || nj > 3) continue;
      if (ni == r && nj == c) continue;
      
      fishes[f].dir = (dir + turn) % 8;
      fishes[f].r = ni;
      fishes[f].c = nj;
      fishes[map[ni][nj]].r = i;
      fishes[map[ni][nj]].c = j;
      swap(map[ni][nj], map[i][j]);
      break;
    }
  }

  bool found = 0;
  vector<Fish> f = fishes;
  vector<vector<int>> m = map;

  for (int jump = 1; jump <= 3; ++jump){
    int nr = r + jump * dr[dir];
    int nc = c + jump * dc[dir];
    if (nr < 0 || nr > 3 || nc < 0 || nc > 3) break;
    if (map[nr][nc] == 0) continue;
    
    found = 1;
    int newDir = fishes[map[nr][nc]].dir;
    int newScore = score + map[nr][nc];
    fishes[map[nr][nc]].dir = -1;
    map[nr][nc] = 0;

    simulate(nr, nc, newDir, newScore);
    map = m;
    fishes = f;
  }

  if (!found)
    answer = max(answer, score);
}

void solve(){
  int dir = fishes[map[0][0]].dir;
  int score = map[0][0];

  fishes[map[0][0]].dir = -1;
  map[0][0] = 0;
  simulate(0, 0, dir, score);

  cout << answer;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}