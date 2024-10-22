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
int burden[13], prv[13], nxt[13];
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
  while (nxt[p]){
    pieces[nxt[p]].r = r;
    pieces[nxt[p]].c = c;
    p = nxt[p];
  }
}

void updateTowerToBottom(int p, bool removed){
  int b = burden[p] + 1;
  if (removed) b *= -1;
  while (prv[p]){
    burden[prv[p]] += b;
    p = prv[p];
  }
}

int getTowerBottom(int p){
  while (prv[p]){
    p = prv[p];
  }
  return p;
}

int getTowerTop(int p){
  while (nxt[p]){
    p = nxt[p];
  }
  return p;
}

void extendTower(int r, int c, int p){
  nxt[top[r][c]] = p;
  prv[p] = top[r][c];
  top[r][c] = getTowerTop(p);

  pieces[p].r = r;
  pieces[p].c = c;
  updateTowerToBottom(p, 0);
  updateTowerToTop(p);
}

void buildTower(int r, int c, int p){
  bottom[r][c] = p;
  top[r][c] = getTowerTop(p);

  pieces[p].r = r;
  pieces[p].c = c;
  updateTowerToTop(p);
}

int reverseTower(int p){
  int top_ = p;
  int bottom_ = p;
  if (!nxt[p]) return p;
  while (p){
    bottom_ = nxt[p];
    nxt[p] = prv[p];
    if (prv[p])
      prv[prv[p]] = p;
    swap(p, bottom_);
  }
  prv[bottom_] = 0;
  p = top_;
  int cnt = 0;
  while (p){
    burden[p] = cnt++;
    p = prv[p];
  }
  return bottom_;
}

bool move(int p, bool turned){
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
    top[r][c] = prv[p];
    nxt[prv[p]] = 0;
    prv[p] = 0;
  }

  if (board[nr][nc] == red)
    p = reverseTower(p);

  if (bottom[nr][nc])
    extendTower(nr, nc, p);
  else
    buildTower(nr, nc, p);
  
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