#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define white 0
#define red 1
#define blue 2

using namespace std;

int N, K;
int board[13][13];
int bottom[13][13], top[13][13];
int burden[13], down[13], up[13];
typedef struct piece{
  int r, c, dir;
}PIECE;
PIECE pieces[11];
int dr[] = {0, 0, -1, 1}, dc[] = {1, -1, 0, 0};

void input(){
  cin >> N >> K;
  for (int i = 1; i <= N; ++i){
    for (int j = 1; j <= N; ++j){
      cin >> board[i][j];
    }
  }
  int r, c, d;
  for (int p = 1; p <= K; ++p){
    cin >> r >> c >> d;
    pieces[p] = {r, c, d - 1};
    bottom[r][c] = top[r][c] = p;
  }
}

void updateTowerToTop(int p){
  int r = pieces[p].r;
  int c = pieces[p].c;
  while (up[p]){
    pieces[up[p]].r = r;
    pieces[up[p]].c = c;
    p = up[p];
  }
}

void updateTowerToBottom(int p, bool removed){
  int b = burden[p] + 1;
  if (removed) b *= -1;
  while (down[p]){
    burden[down[p]] += b;
    p = down[p];
  }
}

int getBottom(int p){
  while (down[p]){
    p = down[p];
  }
  return p;
}

int getTop(int p){
  while (up[p]){
    p = up[p];
  }
  return p;
}

void extendTower(int r, int c, int p){
  up[top[r][c]] = p;
  down[p] = top[r][c];
  top[r][c] = getTop(p);

  pieces[p].r = r;
  pieces[p].c = c;
  updateTowerToBottom(p, 0);
  updateTowerToTop(p);
}

void buildTower(int r, int c, int p){
  bottom[r][c] = p;
  top[r][c] = getTop(p);

  pieces[p].r = r;
  pieces[p].c = c;
  updateTowerToTop(p);
}

int reverseTower(int p){
  if (!burden[p]) return p;

  int subTop = p;
  int subBottom = p;

  // Change pointers.
  while (p){
    subBottom = up[p];
    up[p] = down[p];
    down[down[p]] = p;
    swap(p, subBottom);
  }
  down[subBottom] = 0;

  // Update sizes of sub towers.
  int cnt = 0;
  p = subTop;
  while (p){
    burden[p] = cnt++;
    p = down[p];
  }
  return subBottom;
}

bool move(int p, bool turned){
  if (getBottom(p) != p)
    return false;
  auto [r, c, d] = pieces[p];

  int nr = r + dr[d];
  int nc = c + dc[d];

  if (nr < 1 || nr > N || nc < 1 || nc > N || board[nr][nc] == blue){
    if (!turned){
      if (pieces[p].dir % 2) pieces[p].dir--;
      else pieces[p].dir++;
      return move(p, 1);
    }
    return false;
  }

  if (bottom[r][c] == p) 
    bottom[r][c] = top[r][c] = 0;
  else {
    updateTowerToBottom(p, 1);
    top[r][c] = down[p];
    up[down[p]] = 0;
    down[p] = 0;
  }

  if (board[nr][nc] == red)
    p = reverseTower(p);

  bottom[nr][nc] ? extendTower(nr, nc, p) : buildTower(nr, nc, p);
  
  return burden[bottom[nr][nc]] >= 3;
}

void solve(){
  int answer = 0;
  while (answer <= 1000){
    ++answer;
    for (int p = 1; p <= K; ++p){
      if (move(p, 0)){
        cout << answer;
        return;
      }
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